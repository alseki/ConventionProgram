package Person;

import java.util.Map;

public class AdminManager {

    protected Map<String, Admin> usernameToAdmin;

    public AdminManager(Map<String, Admin> admins) {
        usernameToAdmin = admins;
    }

    public boolean createAccount(String username, String password) {
        if(!usernameToAdmin.containsKey(username)) {
            Admin newAdm = new Admin(username, password);
            usernameToAdmin.put(newAdm.getID(), newAdm);
            return true;
        }
        return false;
    }
}
