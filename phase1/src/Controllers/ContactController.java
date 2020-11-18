package Controllers;

// Programmer: Cara McNeil
// Description: All the methods that take user input in the Contact Menu
// Date Created: 01/11/2020
// Date Modified: 14/11/2020


import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Person.SpeakerManager;
import Presenter.ContactMenu;

import java.util.Scanner;
import java.util.ArrayList;

public class ContactController implements SubMenu {

    private String currentUserID;
    private int currentRequest;
    private PersonManager pManager;
    private ContactMenu presenter = new ContactMenu();
    Scanner input = new Scanner(System.in);

    public ContactController(String currentUserID, PersonManager personManager) {
        this.currentUserID = currentUserID;
        this.pManager = personManager;
    }

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     * @return true iff choice was inputted
     */
    @Override
    public boolean menuOptions() {
        presenter.printMenuOptions();
        // TODO update presenter class with a print statement for each option
        currentRequest = input.nextInt();
        return true;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        do {
            menuOptions();
            // TODO add switch statement to call the methods that correspond with currentRequest
        }
        while (currentRequest != 0);
        return true;
    }

    // TODO change, delete and/or add to the methods below

    /**
     * Get's the current user's contactList
     * @return true iff the presenter printed a formatted contactList
     */
    public boolean getContactList() {
        ArrayList<String> listOfContacts = pManager.getContactList(currentUserID);
        presenter.printContactList(listOfContacts);
        return true;
    }

    /**
     * Add a contact to the current user's contactList
     * @param contactUsername the username of the contact the current user wants to add to their contactList
     * @return true iff the presenter printed a formatted contactList
     */
    public boolean addContact(String contactUsername) {
        String contactID = pManager.getCurrentUserID(contactUsername);
        boolean a = pManager.addContactToPerson(currentUserID, contactID);
        boolean b = pManager.addContactToPerson(contactID, currentUserID);

        if(a && b) {
            presenter.printContactAdded();
            return true;
        }
        return false;
    }

}
