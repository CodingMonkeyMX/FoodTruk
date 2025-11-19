public class Drinks  /*implements Dishterface*/ {


    // variables
    public static int numDrinks = 0;


    public Drinks(String name, double cost, String size) {
        super(cost,name,size);
        numDrinks++;
    }


}
