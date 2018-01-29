package shoedatabase;

import java.sql.*;
import java.io.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private Properties p = new Properties();
    List<Customer> customerList = new ArrayList<>();

    public DatabaseConnection() {
        try {
            p.load(new FileInputStream("src/ShoeDatabase/settings.properties"));

            Class.forName("com.mysql.jdbc.Driver");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadAllCustomers() {
        try (Connection con = DriverManager.getConnection(
                p.getProperty("ConnectionString"),
                p.getProperty("name"),
                p.getProperty("password")
        );) {

            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("Select customers.firstname, customers.id, "
                    + " customers.surname, sum(model.price*quantity) from customers "
                    + "left join orders on orders.customerid=customers.id "
                    + "left join orderinformation on orderinformation.ordersid=orders.id "
                    + "left join shoes on shoes.id=orderinformation.shoeid "
                    + "left join model on model.id=shoes.modelid "
                    + "group by customers.id");

            while (r.next()) {
                Customer temp = new Customer(r.getString("customers.firstname"),
                        r.getString("customers.surname"),
                        r.getInt("customers.id"),
                        r.getDouble("sum(model.price*quantity)"));
                customerList.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void printCustomer(String in) {
        int id = Integer.parseInt(in);
        customerList.forEach(cus -> {
            if (cus.getId() == id) {
                System.out.println("Namn: " + cus.getFirstName() + " " + cus.getLastName()
                        + ", Total summa: " + 
                        (cus.getOrderList().stream().mapToDouble(Order::getTotalSum).sum()));
    }

    });

    }

    public void printAllCustomers() {
        customerList.forEach(cus -> {
            System.out.println("Namn: " + cus.getFirstName() + " " + cus.getLastName()
                    + ", Total summa: " + (cus.getOrderList().stream().mapToDouble(Order::getTotalSum).sum()));

        });
    }

    public boolean isCustomerId(String in) {
        try {
            int idTest = Integer.parseInt(in);
            if (customerList.stream().anyMatch((c) -> (c.getId() == idTest))) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
}
