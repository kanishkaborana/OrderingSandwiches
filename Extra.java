package sample;

public class Extra
{
    /*

    final static protected int LETTUCE_INDEX = 0;
    final static protected int TOMATO_INDEX = 1;
    final static protected int BACON_INDEX = 2;
    final static protected int ONION_INDEX = 3;
    final static protected int SPINACH_INDEX = 4;
    final static protected int MUSHROOM_INDEX = 5;
    final static protected int PICKLES_INDEX = 6;
    final static protected int AMERICAN_INDEX = 7;
    final static protected int SWISS_INDEX = 8;
    final static protected int PROVOLONE_INDEX = 9;

    final static protected String LETTUCE = "lettuce";
    final static protected String TOMATO = "tomato";
    final static protected String BACON = "bacon";
    final static protected String ONION= "onion";
    final static protected String SPINACH= "spinach";
    final static protected String MUSHROOM= "mushroom";
    final static protected String PICKLES= "pickles";
    final static protected String AMERICAN= "american";
    final static protected String SWISS= "swiss";
    final static protected String PROVOLONE= "provolone";

     */

    private String name;

    public Extra(String topping) {
        name = topping;
    }


    public boolean equals(Extra compare)
    {
        if(name.equals(compare.name) == true)
            return true;
        else
            return false;
    }

    public String toString()
    {
        return name;
    }

}
