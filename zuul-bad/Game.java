
import java.util.*;
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Player player;
    Stack <Room> roomStack = new Stack();
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        player = new Player();
        parser = new Parser();
        play();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room lab, hall, bathroom, research_room, suspicoius_room, outside;
        Item stick, nothing, wood;
        //items in rooms
        stick = new Item("a ordinary stick", 1, "stick");
        nothing = new Item("nothing", 0, "nothing");
        wood = new Item("some wood", 2, "wood");

        // create the rooms
        lab = new Room("###### the lab ######");
        hall = new Room("###### hall ######");
        bathroom = new Room("###### bathroom ######");
        research_room = new Room("###### research room ######");
        suspicoius_room = new Room("###### suspicoius room ######");
        outside = new Room("###### not allowed here yet... ######");
        //add items to room
        lab.addItem(stick);
        lab.addItem(wood);
        hall.addItem(nothing);

        // initialise room exits
        lab.setExit("north", bathroom);
        lab.setExit("east", hall);
        lab.setExit("south", research_room);
        lab.setExit("west", suspicoius_room);
        suspicoius_room.setExit("east", lab);
        research_room.setExit("north", lab);
        hall.setExit("east", outside);
        hall.setExit("west", lab);
        outside.setExit("west", hall);
        bathroom.setExit("south", lab);

        currentRoom = lab;  // start game in the lab
        roomStack.push(currentRoom);
    }

    private void printLocationInfo()
    {
        System.out.println(currentRoom.getDescription());
        currentRoom.getExitString();
        currentRoom.getItemsString();
        System.out.println();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is my way to kill time, so enjoy :)");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) 
        {
            printHelp();
        }
        else if (commandWord.equals("back")) 
        {
            back(command);
        }
        else if (commandWord.equals("go")) 
        {
            goRoom(command);
        }
        else if (commandWord.equals("eat"))
        {
            eat();
        }        
        else if (commandWord.equals("quit")) 
        {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("take")) 
        {
            takeItem(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:    
    /**
     * Print out some help information.
     * Here we print some smart, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {

        System.out.println("Your command words are: " + parser.getCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            enterRoom(nextRoom);
            roomStack.push(currentRoom);
        }
    }

    private void enterRoom(Room nextRoom)
    {
        previousRoom = currentRoom;
        currentRoom = nextRoom;
        printLocationInfo();
    }

    private void eat()
    {
        System.out.println(currentRoom.getEatDescription());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Just quit is enough...Try again");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void back(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Just back is enough...Try again");
            return;
        }
        if (roomStack.empty() == true) {
            System.out.println("You are at the start");
            roomStack.push(currentRoom);
        }
        else{
            roomStack.pop();
            if (roomStack.empty() == true) {
            System.out.println("You are at the start");
            roomStack.push(currentRoom);
            } 
            else{
                enterRoom(roomStack.peek());
            }
        }
    }
    private void takeItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("take what?");
        }
        String item = command.getSecondWord();
        
        if(true)
        {
            
        }

    }
}
