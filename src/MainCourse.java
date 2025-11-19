public class MainCourse  /*implements Dishterface*/ {


    // variables
    private Display display;

    private String[] MainCourse = {"Pizza", "Burger", "Steak"};
    private double[] prices = {19.49, 15.99, 33.49};
    public MainCourse(Display display) {
        this.display = display;  // Store reference to GUI
    }
}