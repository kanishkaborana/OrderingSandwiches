package sample;

import java.util.ArrayList;

public class Order implements Customizable{
    public static int lineNumber; //reset for each new order;
    private ArrayList<OrderLine> orderlines;

    public Order()
    {
        orderlines= new ArrayList<OrderLine>();
    }

    public int size()
    {
        return orderlines.size();
    }

    @Override
    public boolean add(Object obj) {
        if(obj == null)
            return false;

        OrderLine temp= (OrderLine) obj;
        orderlines.add(temp);
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        for(int i=0; i<orderlines.size(); i++)
        {
            if(orderlines.get(i).equals(obj)) {
                orderlines.remove(i);
                return true;
            }
        }
        return false;
    }

    public double get_total_price() {
        double price = 0;
        for(int i = 0; i < orderlines.size(); i ++) {
            price += orderlines.get(i).getPrice();
        }
        return price;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < orderlines.size(); i++) {
            result = result.concat(orderlines.get(i).toString()).concat("\n");
        }
        return result;
    }

    public OrderLine get(int position) {
        return orderlines.get(position);
    }

    public void delete() {
        orderlines.clear();
    }
}
