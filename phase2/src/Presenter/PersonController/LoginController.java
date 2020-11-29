package Presenter.PersonController;

// Programmers: Cara McNeil, Sarah Kronenfeld
// Description: All the methods that take user input in the Login Menu
// Date Created: 01/11/2020
// Date Modified: 29/11/2020

import Presenter.Central.SubMenu;
import Presenter.InvalidChoiceException;
import Presenter.InvalidFormatException;
import Presenter.NoDataException;
import Presenter.OverwritingException;
import Person.PersonManager;

public class LoginController extends SubMenu {

    private int accountChoice;
    private PersonManager manager;
    private LoginMenu presenter;
    public String username;

    public LoginController(SubMenu subMenu, int accountChoice) {
        super(subMenu);
        this.manager = super.personManager;
        this.accountChoice = accountChoice;
        presenter = new LoginMenu(accountChoice);
    }

    /**
     * Returns this controller's associated presenter
     * @return The LoginMenu associated with this controller
     */
    public LoginMenu getPresenter() {
        return this.presenter;
    }

    /**
     * Attempts to log the user in with a given username and password
     * @param username The username
     * @param password The password
     * @return The user's ID, if successful
     */
    public String login (String username, String password) throws InvalidChoiceException {
        if(this.accountChoice != manager.typePerson(username)) {
            throw new InvalidChoiceException("username");
        }
        if (manager.getCurrentUserID(username) != null)  {
            if(manager.confirmPassword(username, password)) {
                return manager.getCurrentUserID(username);
            }
        }
        throw new NoDataException("account");
    }

    /**
     * Attempts to create a new user account with a given set of login information
     * @param username The username for the new account
     * @param password The password for the new account
     * @param name The name associated with the new account
     * @param email The email associated with the new account
     */
    public void createAccount(String username, String password, String name, String email) throws InvalidChoiceException {
        if(username.contains(",")) {
            throw new InvalidFormatException("username", "no commas");
        }
        if (manager.getCurrentUserID(username) != null) {
            throw new OverwritingException("username");
        }
        manager.createAccount(name, username, password, email);
    }
}
