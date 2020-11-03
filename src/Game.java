import java.util.Scanner;

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Player player;
    Scanner input = new Scanner(System.in);

    public Game() {
        createRooms();
        parser = new Parser();
    }


    private void createRooms() {

        Room entry, tutorial, currencyRoom,
                currencyObtainRoom1, currencyObtainRoom, DesertBaseRoom, Desert1,
                Desert2, Desert3, EndRoom;

        entry = new Room("at the entry room. Here you can find information on desertification.", 1);
        tutorial = new Room(" in the tutorial room. Here you can learn how to play the game.", 2);
        currencyRoom = new Room("in the currency room. Here you can exchange your trash for saplings.", 3);

        currencyObtainRoom = new Room("in the currency obtain room. Here you can harvest trash.", 4);
        currencyObtainRoom.addTrash();

        currencyObtainRoom1 = new Room("currency obtain room. Here you can harvest trash.", 4);
        currencyObtainRoom1.addTrash();

        DesertBaseRoom = new Room("in the desert base room. Choose a direction to go to a desert", 5);
        Desert1 = new Room("in the first desert. Stop the desertification", 6);
        Desert2 = new Room("in the second desert. Stop the desertification", 6);
        Desert3 = new Room("in the third desert. Stop the desertification", 6);
        EndRoom = new Room("in the end room. Here is a little test to end the game", 7);

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
        EndRoom.setExit("south", Desert3);

        currentRoom = entry;
    }

    public void play() {
        if (currentRoom.getType() == 1) {
            System.out.println("Enter your name: ");

            player = new Player(input.nextLine());

            System.out.println("Welcome " + player.getName());

            printWelcome();
        }

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
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
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
        } else if (commandWord == CommandWord.PICKUP) {
            if (currentRoom.getType() == 4) {
                player.addTrash();
                currentRoom.removeTrash();
            }
        } else if (commandWord == CommandWord.SELL && currentRoom.getType() == 3) {
            if (player.hasTrash()) {
                player.sellTrash();
            }
        } else if (commandWord == CommandWord.BUY && currentRoom.getType() == 3) {
            if (player.getCoins() > 0) {
                player.addSapling();
            }
        }

        endRoom();
        return wantToQuit;
    }

    private void endRoom(){
        boolean isAnswered = false;

        if (currentRoom.getType() == 7) {
            boolean question1 = false, question2 = false, question3 = false;

            System.out.println("You are almost finished. You need to answer the following questions correctly");
            System.out.println("Question 1: What is 1+1");

            while (!question1) {
                if (input.nextLine().equals("2")) {
                    System.out.println("Correct");
                    question1 = true;
                } else {
                    System.out.println("Try again, Here are some hints");
                    System.out.println("3    4    2");
                    //question1 = false;
                }
            }
            System.out.println("Here is another question");
            System.out.println("Question 2: What is 2 + 2?");
            while (!question2) {
                if (input.nextLine().equals("4")) {
                    System.out.println("Correct");
                    question2 = true;
                } else {
                    System.out.println("Try again, Here are some hints");
                    System.out.println("5    4    9");
                    //question2 = false;
                }
            }
            System.out.println("Here is another question");
            System.out.println("Question 2: What is 2 * 6?");
            while (!question3) {
                if (input.nextLine().equals("12")) {
                    System.out.println("Correct. Thanks for playing");
                    question3 = true;
                    isAnswered = true;
                } else {
                    System.out.println("Try again, Here are some hints");
                    System.out.println("15    14    12");
                    //question2 = false;
                }
            }
        }
        if(isAnswered){
            System.exit(0);
        }
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
