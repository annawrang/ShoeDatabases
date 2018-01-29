package shoedatabase;

import java.util.Scanner;

public class ShoeDatabase {

    DatabaseConnection dbc = new DatabaseConnection();
    Scanner sc = new Scanner(System.in);
    int id;

    public void getUsersChoice() {
        dbc.loadAllCustomers();
        System.out.println("Ange kundens id:");
        String in = sc.next();

        boolean isC = dbc.isCustomerId(in);

        if (isC) {
            dbc.printCustomer(in);
        } else {
            dbc.printAllCustomers();
        }

    }

    public static void main(String[] args) {
        ShoeDatabase sd = new ShoeDatabase();
        sd.getUsersChoice();
    }

}
