public class MainCourse  /*implements Dishterface*/ {


    // variables
    public static int numMains = 0;


    public MainCourse(String name, double cost, String size) {
        super(cost,name,size);
        numMains++;
    }


}

