package shoedatabase;

import java.sql.*;
import javax.sql.*;
import java.io.*;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private Properties p = new Properties();

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

    public void getCustomerAmounts() {
        try (Connection con = DriverManager.getConnection(
           p.getProperty("ConnectionString"), 
                p.getProperty("name"), 
                p.getProperty("password")
        );
                Scanner sc = new Scanner(System.in)) {
            PreparedStatement prepStatement = con.prepareStatement
        ("Select customers.firstname, sum(model.price*quantity) from customers "
                    + "left join orders on orders.customerid=customers.id "
                    + "inner join orderinformation on orderinformation.ordersid=orders.id "
                    + "inner join shoes on shoes.id=orderinformation.shoeid "
                    + "inner join model on model.id=shoes.modelid "
                    + "where customers.firstname=? group by customers.firstname;");
            System.out.println("Ange kundens namn: ");
            String namn = sc.next();
            prepStatement.setString(1, namn);
            ResultSet rs = prepStatement.executeQuery();

            while (rs.next()) {
                String customer = rs.getString("customers.firstname");
                Customer shoeCustomer = new Customer(customer);
                double sum = rs.getDouble("sum(model.price*quantity)");

                System.out.println("Kund: " + shoeCustomer.getFirstName()
                        + "\nSumma: " + sum);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
