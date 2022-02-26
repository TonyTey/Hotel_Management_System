package admin;

public class Admin {
    private String name, password, email;

    public Admin(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //get and set name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //get and set password
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //get and set email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
