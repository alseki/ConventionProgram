package Presenter;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to a user's contact information
// Date Created: 11/11/2020
// Date Modified: 11/11/2020

import java.util.ArrayList;

public class ContactMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     */
    @Override
    public void printMenuOptions() {
        System.out.println("----- Contact Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To view Contact List, Enter '1'");
        System.out.println("To add a Contact, Enter '2'.");
    }

    /**
     * Prints a list of the user's contacts
     */
    public void printContactList(ArrayList<String> contactList) {
        System.out.println('\n' + "-CONTACTS-");
        System.out.println("---");
        for(String c: contactList) {
            System.out.println(c);
        }
        System.out.println("---" + '\n');
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
