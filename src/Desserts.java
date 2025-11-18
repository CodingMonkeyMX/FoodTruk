public class Desserts extends TestFood /*implements Dishterface*/ {


    // variables
    public static int numDesserts = 0;


    public Desserts(String name, double cost, String size) {
        super(cost,name,size);
        numDesserts++;
    }


}


