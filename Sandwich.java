package sample;
import java.util.ArrayList;

public abstract class Sandwich implements Customizable{

    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;

    protected String type;
    protected ArrayList<String> toppings;
    protected ArrayList<Extra> extras;


    public Sandwich(String type, ArrayList<Extra> extras)
    {
        this.type=type;
        this.extras=extras;
    }

    public abstract double price();

    public String toString() {
        return type+" Sandwich;"+toppings.toString()+" Extras: "+extras.toString();
    }
}
