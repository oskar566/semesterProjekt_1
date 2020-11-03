public class Player {

    private static int coins;
    private static String name;
    private static Inventory inventory;
    private static Coin coin;
    //private static Sapling plant;


    public Player(String name){

        inventory = new Inventory();
        coin = new Coin();
        this.name = name;

    }


    public void waterSapling(){

    }

    public boolean hasTrash(){
        return inventory.hasTrash();
    }
    public void plantTree(){
       inventory.removeSapling();

    }

    public void sellTrash(){
        inventory.removeTrash();
        coin.addCoin();
    }

    public void addTrash(){
        inventory.addTrash();
    }

    public void addSapling(){
        inventory.addSapling();
        coin.removeCoin();
    }
    public boolean plantSapling(){
        if(plantSapling() == true) {
            inventory.removeSapling();
            System.out.println("You have planted a sapling");
        }
        else if (plantSapling() == false)
            System.out.println("You have no sapling to plant");
    return
    }
    public boolean hasSapling(){
        return inventory.hasSapling();

    }

    public int getCoins(){
        return coin.getAmount();
    }

    public String getName(){
        return this.name;
    }
}
