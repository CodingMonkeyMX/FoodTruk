
public class Entrees /*implements Dishterface*/ {

    private Display display;
    
    private String[] entrees = {"hot dog", "elote", "Wonton soup"};
    private double[] prices = {3.99, 4.99, 7.99};

    public Entrees(Display display) {
        this.display = display;  // Store reference to GUI
    }

}