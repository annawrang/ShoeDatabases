package shoedatabase;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String borough;
    private String postalCode;
    private List<Order> orderList = new ArrayList<>();
    private double totalSum;

    Customer(String firstname) {
        this.firstName = firstname;
    }

    Customer(String name, String surname, int id, double totalSum) {
        this.firstName = name;
        this.lastName=surname;
        this.id = id;
        this.orderList.add(new Order(totalSum));

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

}
