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
        return true;
    }

    /**
     * Prompts user to enter contact information
     * @return true iff add contact prompt was printed
     */
    public boolean printAddContactPrompt() {
        return true;
    }

    /**
     * Prints that a contact was successfully added
     * @return true iff successful contact addition statement was printed
     */
    public boolean printContactAdded() {
        return true;
    }

    // error message for when contact isn't added?
}
