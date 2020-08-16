package Entity;

import java.sql.Timestamp;

public class Order {
    private int ID;
    private int CustomerID;
    private Timestamp Date;

    public Timestamp getDate() {
        return this.Date;
    }

    public void setDate(Timestamp Date) {
        this.Date = Date;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCustomerID() {
        return this.CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }
}