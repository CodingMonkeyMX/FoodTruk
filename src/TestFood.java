public class TestFood {


    private double cost;
    private String nameOfDish;
    private String size;
    private int orders;


    public TestFood(){
        cost=0;
        nameOfDish="";
        size = null;
    }


    public TestFood(double c, String n, String s){
        this();
        cost=c;
        nameOfDish=n;
        size=s;
    }


    public void order() {
        orders++;
    }




    public String toString() {
        return "size: " + size +" Name: "+ nameOfDish +" Cost: " +cost;
    }


}









