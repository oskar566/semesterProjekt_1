package worldofzuul;

public class Game {
    private Parser parser;
    private Room currentRoom;


    //Test Aksel
    //test oskar
    // test Lasse

    public Game() {
        createRooms();
        parser = new Parser();
    }

    //test2
    private void createRooms() {

        Room outside, theatre, pub, lab, office;
      
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;

        Room entry, tutorial, currencyRoom,
                currencyObtainRoom1, currencyObtainRoom, DesertBaseRoom, Desert1,
                Desert2, Desert3, EndRoom;

        entry = new Room("at the entry room. Here you can find information on desertification.");
        tutorial = new Room(" in the tutorial room. Here you can learn how to play the game.");
        currencyRoom = new Room("in the currency room. Here you can exchange your trash for saplings.");
        currencyObtainRoom = new Room("in the currency obtain room. Here you can harvest trash.");
        currencyObtainRoom1 = new Room("currency obtain room. Here you can harvest trash.");
        DesertBaseRoom = new Room("in the desert base room. Choose a direction to go to a desert");
        Desert1 = new Room("in the first desert. Stop the desertification");
        Desert2 = new Room("in the second desert. Stop the desertification");
        Desert3 = new Room("in the third desert. Stop the desertification");
        EndRoom = new Room("in the end room. Here is a little test to end the game");

        entry.setExit("north", tutorial);

        tutorial.setExit("north", currencyRoom);
        tutorial.setExit("south", tutorial);

        currencyRoom.setExit("north", DesertBaseRoom);
        currencyRoom.setExit("west", currencyObtainRoom);
        currencyRoom.setExit("east", currencyObtainRoom1);
        currencyRoom.setExit("south", tutorial);

        currencyObtainRoom1.setExit("west", currencyRoom);

        currencyObtainRoom.setExit("east", currencyRoom);

        DesertBaseRoom.setExit("north", Desert3);
        DesertBaseRoom.setExit("west", Desert1);
        DesertBaseRoom.setExit("east", Desert2);
        DesertBaseRoom.setExit("south", currencyRoom);

        Desert1.setExit("east", DesertBaseRoom);

        Desert2.setExit("west", DesertBaseRoom);

        Desert3.setExit("north", EndRoom);
        Desert3.setExit("south", DesertBaseRoom);

    }

    public void play() {
        printWelcome();


        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("This is a game...");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}
