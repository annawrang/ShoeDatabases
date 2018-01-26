package shoedatabase;


public class ShoeDatabase {

    public static void main(String[] args) {
        DatabaseConnection dbc= new DatabaseConnection();
        dbc.getCustomerAmounts();
    }
    
}
