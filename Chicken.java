package sample;

import java.util.ArrayList;

public class Chicken extends Sandwich {

    public Chicken(ArrayList<Extra> extras)
    {
        super("Chicken", extras);
        ArrayList<String> toppings = new ArrayList<String>();
        toppings.add("Fried Chicken");
        toppings.add("Spicy Sauce");
        toppings.add("Pickles");
        super.toppings=toppings;
    }

    @Override
    public double price()
    {
        return 8.99 + (extras.size()*PER_EXTRA);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

    @Override
    public boolean add(Object obj)
    {
        Extra temp= (Extra) obj;

        if(extras.size() >= 6) {
            return false;
        }
        else {
            extras.add(temp);
            return true;
        }
    }

    @Override
    public boolean remove(Object obj)
    {
        for(int i=0; i<extras.size(); i++)
        {
            if(extras.get(i).equals(obj)) {
                extras.remove(i);
                return true;
            }
        }
        return false;
    }
}
