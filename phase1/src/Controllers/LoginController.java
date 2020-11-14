package Controllers;

// Programmer: Cara McNeil
// Description: All the methods that take user input in the Login Menu
// Date Created: 01/11/2020
// Date Modified: 13/11/2020

import Person.PersonManager;

public class LoginController implements SubMenu {
    private PersonManager manager;

    public LoginController(PersonManager manager) {
        this.manager = manager;
    }

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     * @return true iff choice was inputted
     */
    @Override
    public boolean menuOptions() {
        return false;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        return false;
    }

    /**
     * Prompts user to input username and password.
     *
     * @return true iff login info corresponds with an existing Person.Person account.
     */
    public boolean login() {
        //if (manager.findPerson(username, password)) {
        //   currentUserID = PersonManager.getPerson(username, password).id;
        //}
        return false;
    }

    /**
     * Prompts user for relevant information and uses it to create a new Person.Person account.
     *
     * @return true iff new account has been created
     */
    public boolean createAccount() {
        //manager.createAccount(name, username, password, email);
        return false;
    }
}
