import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room 
{
    private Inventory inventory;
    private String description;
    private HashMap<String, Room> exits;


    // 1 = Entry, 2 = Tutorial, 3 = CurrencyRoom, 4 = currencyObtain, 5 = desertBase, 6 = desert, 7 = endRoom
    private int type;


    public Room(String description, int type)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        inventory = new Inventory();
        this.type = type;
    }

    public void addTrash(int amountOfTrash){
        for (int i = 0; i < amountOfTrash; i++) {
            inventory.addTrash();
        }
    }
    public void removeTrash(){
        inventory.removeTrash();
    }
    public int getType(){
        return this.type;
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public void printRoomInventory(){
        System.out.println("Room contains " + inventory.countTrash() + " pieces of trash.");
    }

    public boolean containsTrash(){
        return inventory.hasTrash();
    }

}

