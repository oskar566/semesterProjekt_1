package Aksel;


public class Player {


    private static String name;
    private static Inventory inventory;
    private static Coin coin;




    public Player(String name){

        inventory = new Inventory();
        coin = new Coin();
        this.name = name;

    }


    public void waterSapling(){

    }

    public void plantTree(){
       inventory.removeSapling();

    }

    public void sellTrash(){
        inventory.removeTrash();
        coin.addCoin();
    }

    public String getName(){
        return this.name;
    }
}
