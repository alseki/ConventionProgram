package Presenter.PersonController;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to a user's contact information
// Date Created: 11/11/2020
// Date Modified: 11/11/2020

import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.InvalidChoiceException;

import java.util.ArrayList;

// Programmers:
// Description:
// Date Created:
// Date Modified: 02/12/2020

public class ContactMenu implements SubMenuPrinter {

    @Override
    public String getMenuTitle() {
        return "----- Contact Menu -----";
    }

    @Override
    public String[] getMenuOptions() {
        String[] options = {"Return to Main Menu", "View contact list", "Add a contact"};
        return options;
    }

    /**
     * Prints a list of the user's contacts
     */
    public String[] printContactList(ArrayList<String> contactList) {
        String[] clist = {};
        clist = contactList.toArray(clist);
        return clist;
        // TODO exception if list is empty
    }

    /**
     * Prompts user to enter contact information
     */
    public String printAddContactPrompt() {
        return "Enter the username of the person you would like to add as a contact: ";
    }

    /**
     * Prints that a contact was successfully added
     */
    public String printContactAdded() {
        return "Contact has been successfully added.";
    }
}
