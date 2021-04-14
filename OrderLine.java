package sample;

public class OrderLine {
    private int lineNumber; //a serial number created when a sandwich is added to the order
    private Sandwich sandwich;
    private double price;

    public OrderLine(int lineNumber, Sandwich sandwich, double price)
    {
        this.sandwich=sandwich;
        this.price=price;
        this.lineNumber=lineNumber;
    }

    public boolean equals(OrderLine compare) {
        if(this.sandwich == compare.sandwich && this.price == compare.price && this.lineNumber == compare.lineNumber) {
            return true;
        }
        else {
            return false;

        }
    }

    public String toString() {
        return lineNumber+ ":"+ sandwich.toString() +" Price: $"+price;
    }

    public double getPrice()
    {
        return price;
    }

    public void changeLineNumber(int x)
    {
        this.lineNumber=x;
    }
    public Sandwich getSandwich()
    {
        return sandwich;
    }

}
