import java.util.ArrayList;

public class Inventory {

    private static ArrayList<Item> inventory;

    public Inventory() {
        inventory = new ArrayList<Item>();
    }


    public void addSapling(){
        inventory.add(new Sapling());
    }

    public void addTrash(){
        inventory.add(new Trash());
    }
    public int getSize(){
        return inventory.size();
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
    public boolean hasTrash(){
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).getName().equals("Trash")){
                return true;
            }
        }
        return false;
    }
    public String printInventory(){
        int saplingCount=0;
        int trashCount=0;

        for(int i=0;i< inventory.size();i++){
            if(inventory.get(i).getName().equals("Sapling")){
                saplingCount++;
            }
            if(inventory.get(i).getName().equals("Trash")){
                trashCount++;
            }

        }
        return "Inventory: "+trashCount+" Trash " +saplingCount+" Saplings";
    }
}
