package customer;

public class Customer {
    private String name, password, email;

    public Customer(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    //get and set the name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //get and set the password
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //get and set the email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
