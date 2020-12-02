package Presenter.PersonController;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to a user's contact information
// Date Created: 11/11/2020
// Date Modified: 11/11/2020

import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.InvalidChoiceException;

import java.util.ArrayList;

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
    public void printContactList(ArrayList<String> contactList) throws InvalidChoiceException {
        System.out.println('\n' + "-CONTACTS-");
        String[] clist = {};
        clist = contactList.toArray(clist);
        printList(clist, "contact");
    }

    /**
     * Prompts user to enter contact information
     */
    public void printAddContactPrompt() {
        System.out.println("Enter the username of the person you would like to add as a contact: ");
    }

    /**
     * Prints that a contact was successfully added
     */
    public void printContactAdded() {
        System.out.println("Contact has been successfully added.");
    }
}
