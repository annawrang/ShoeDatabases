package shoedatabase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private List<Shoe> shoeList = new ArrayList<>();
    private Timestamp date;
    private double totalSum;

    Order(double totalSum) {
        this.totalSum=totalSum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Shoe> getShoeList() {
        return shoeList;
    }

    public void setShoeList(List<Shoe> shoeList) {
        this.shoeList = shoeList;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }
    
}
