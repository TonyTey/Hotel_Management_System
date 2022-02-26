package customer;

import Database.Database;

public class CustomerManager {
    private String name;
    private String password;
    private String email;
    Database db = new Database();

    public CustomerManager() {}

    //register customer
    public boolean register(Customer customer) {
        boolean result = false;

        result = db.register(customer) > 0;

        return result;
    }

    //select customer from database
    public boolean selectCustomer(Customer customer) {
        String[] details = db.checkUser(customer.getEmail());

        if(details[0] != null) {
            if(customer.getPassword().equals(details[1]))
                return true;
            else
                return false;
        }else {
            return false;
        }
    }

}
