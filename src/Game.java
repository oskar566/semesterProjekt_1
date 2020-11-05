import java.util.Scanner;



public class Game {

    private Parser parser;
    private Room currentRoom;
    private Player player;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {

        Room entry, tutorial, currencyRoom,
                currencyObtainRoom1, currencyObtainRoom, DesertBaseRoom, Desert1,
                Desert2, Desert3, EndRoom;

        entry = new Room("At the entry room. Here you can find information on desertification.", 1);
        tutorial = new Room(" In the tutorial room. Here you can learn how to play the game. " +
                "                        \n Here are some basics about the game:  \\n \" +\n" +
                "                        \"           Go between rooms to pick up trash to sell for coins. Coins are used to buy saplings to plant \\n\" +\n" +
                "                        \"           use commandword: help & roominfo for specific info on the current room\"", 2);
        currencyRoom = new Room("in the currency room. Here you can exchange your trash for saplings.", 3);

        currencyObtainRoom = new Room("in the currency obtain room. Here you can harvest trash.", 4);
        currencyObtainRoom.addTrash();

        currencyObtainRoom1 = new Room("currency obtain room. Here you can harvest trash.", 4);
        currencyObtainRoom1.addTrash();

        DesertBaseRoom = new Room("in the desert base room. Choose a direction to go to a desert", 5);
        Desert1 = new Room("in the first desert. Stop the desertification", 6);
        Desert2 = new Room("in the second desert. Stop the desertification",6);
        Desert3 = new Room("in the third desert. Stop the desertification",6);
        EndRoom = new Room("in the end room. Here is a little test to end the game",7);

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

        currentRoom = entry;
    }

    public void play() 
    {            

        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name: ");

        player = new Player(input.nextLine());

        printWelcome();


        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing " + player.getName() +   ". Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome " + player.getName() + " to the World of Zuul - Desertification edition!");
        System.out.println("In this game, you will learn about desertificaiton, how to slow it down and even try it out yourself");
        System.out.println("Type '" + CommandWord.HELP + "' if you need assistance along the way!");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("That's not a valid command " + "Type" + CommandWord.HELP);
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.INFO)
        {
            printInfo();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.PICKUP) {
            if(currentRoom.getType() == 4){
                player.addTrash();
                currentRoom.removeTrash();
            }
        }
        else if (commandWord == CommandWord.ROOMINFO)
        {
            printRoomInfo();
        }
        else if(commandWord == CommandWord.SELL && currentRoom.getType() == 3){
            if(player.hasTrash()){
                player.sellTrash();
            }
        }
        else if(commandWord == CommandWord.BUY && currentRoom.getType()==3){
            if(player.getCoins()>0){
                player.addSapling();
            }
        }
        return wantToQuit;
    }

    private void printInfo()
    {
        System.out.println("You need to help stop the desertification");
        System.out.println("to help you need to plant saplings in the desert");
        System.out.println("To get saplings you need to pick up trash to sell in the CurrencyObtainRoom");
    }

    private void printRoomInfo()
    {
        switch(currentRoom.getType())
        {
            case 1:
            {
                System.out.println("This is the entry room \n " +
                                    " here you can information about desertification... and not much else :)  \b ");
                break;
            }
            case 2:
            {
                System.out.println("This is the tutorial room! Here are some basics about the game:  \n " +
                        "           Go between rooms to pick up trash to sell for coins. Coins are used to buy saplings to plant \n" +
                        "           use commandword help & roominfo for specific info on the current room");
                break;
            }
            case 3:
            {
                System.out.println("This is the CurrencyRoom, here you can sell your collected trash for coins \b " +
                        "           and buy saplings for planting, commandwords are: buy & sell");
                break;
            }
            case 4:
            {
                System.out.println("This is the room where you collect trash. \b" +
                        "           Collected trash can be sold for coins in the CurrencyRoom, commandwords are: pickup");
                break;
            }
            case 5:
            {
                System.out.println("This is the desertbase, this room will guide you to the other rooms");
                break;
            }
            case 6:
            {
                System.out.println("This is the desert! Here your job is to plant your saplings to stop desertification, commandword is: plant");
                break;
            }
            case 7:
            {
                System.out.println("This is the endRoom. You have planted all the saplings required. " +
                        "        \b You will now be quizzed about desertification");
                break;
            }

        }
    }
    private void printHelp()
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
