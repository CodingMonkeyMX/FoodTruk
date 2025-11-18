
public class Entrees extends TestFood /*implements Dishterface*/ {


    // variables
    public static int numEntrees = 0;


    public Entrees(String name, double cost, String size) {
        super(cost,name,size);
        numEntrees++;
    }


}





