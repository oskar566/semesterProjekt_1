package Aksel;

import java.util.ArrayList;

public class Inventory {

    private static ArrayList<Item> inventory;



    public Inventory() {
        new ArrayList<Item>();
    }

    public void addSapling(){
        inventory.add(new Sapling());
    }

    public void addTrash(){
        inventory.add(new Sapling());
    }

    public void removeSapling(){
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).getName().equals("Sapling")){
                inventory.remove(i);
                break;
            }
        }
    }
    public void removeTrash(){
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).getName().equals("Trash")){
                inventory.remove(i);
                break;
            }
        }
    }
}
