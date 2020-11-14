package Controllers;

// Programmer: Cara McNeil
// Description: abstract main account page for other controllers to inherit from
// Date Created: 01/11/2020
// Date Modified: 13/11/2020

import Person.PersonManager;

import java.util.Scanner;

abstract public class PersonController {
    Scanner input = new Scanner(System.in);
    private String currentUserID;
    private PersonManager manager;
    private LoginController loginController;


    public PersonController(PersonManager manager) {
        this.manager = manager;
    }

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */
    public void run() {
        loginController = new LoginController(manager);
        loginController.menuChoice();
        // currentUserID = ?
    }

}
