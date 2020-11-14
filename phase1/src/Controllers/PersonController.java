package Controllers;

// Programmer: Cara McNeil
// Description: abstract main menu for other controllers to inherit from
// Date Created: 01/11/2020
// Date Modified: 13/11/2020

import Person.PersonManager;

import java.util.Scanner;

abstract public class PersonController {
    Scanner input = new Scanner(System.in);
    private String currentUserID;
    private PersonManager manager;


    public PersonController(PersonManager manager) {
        this.manager = manager;
    }

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */
    abstract void run();
    // will use currentRequest to determine what methods to call
    // if any classes return false, needs to update the presenter accordingly


}
