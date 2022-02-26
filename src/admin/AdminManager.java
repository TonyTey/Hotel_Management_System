package admin;

import Database.Database;

public class AdminManager {
    Database db = new Database();

    public AdminManager() {}

    public boolean selectAdmin(Admin admin) {
        String[] details = db.checkUser(admin.getEmail());

        if(details[0] != null) {
            if(admin.getPassword().equals(details[1]))
                return true;
            else
                return false;
        }else {
            return false;
        }
    }
}
