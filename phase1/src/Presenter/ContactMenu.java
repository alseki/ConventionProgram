package Presenter;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to a user's contact information
// Date Created: 11/11/2020
// Date Modified: 11/11/2020

import java.util.ArrayList;
import java.util.Collection;

public class ContactMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        System.out.println("----- Contact Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        // TODO add print statements for all the other menu options
        return true;
    }

    /**
     * Prints a list of options
     * @return
     */
    public boolean options() {
        return true;
    }

    /**
     * Prints a list of the user's contacts
     * @return true iff all contacts were printed
     */
    public boolean printContactList(ArrayList<String> contactList) {
        System.out.println("-CONTACTS-");
        for(String c: contactList) {
            System.out.println(c);
        }
        System.out.println("---");
        return true;
    }

    /**
     * Prompts user to enter contact information
     * @return true iff add contact prompt was printed
     */
    public boolean printAddContactPrompt() {
        System.out.println("Enter the username of the person you would like to add as a contact: ");
        return true;
    }

    /**
     * Prints that a contact was successfully added
     * @return true iff successful contact addition statement was printed
     */
    public boolean printContactAdded() {
        System.out.println("Contact has been successfully added.");
        return true;
    }

    // error message for when contact isn't added?
}
