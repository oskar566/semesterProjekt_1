import java.util.ArrayList;

public class Player {

    private static int coins;
    private static String name;
    private static Inventory inventory;
    private static Coin coin;


    public Player(String name){

        inventory = new Inventory();
        coin = new Coin();
        this.name = name;

    }




    public boolean hasTrash(){
        return inventory.hasTrash();
    }
    public void plantTree(){
       inventory.removeSapling();

    }

    public void sellTrash(){
        for(int i=0;i< inventory.getSize();i++) {
            inventory.removeTrash();
            coin.addCoin();
        }
    }

    public void addTrash(){
        inventory.addTrash();
    }

    public void addSapling(){
        inventory.addSapling();
        coin.removeCoin();
    }

    public int getCoins(){
        return coin.getAmount();
    }

    public String getName(){
        return this.name;
    }

    public void printPlayerInventory(){
        System.out.println(inventory.printInventory());
    }
}
