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

public class ContactController implements SubMenu {

    private String currentUserID;
    private int currentRequest;
    private PersonManager personManager;
    private ContactMenu presenter = new ContactMenu();
    Scanner input = new Scanner(System.in);

    public ContactController(String currentUserID, PersonManager personManager) {
        this.currentUserID = currentUserID;
        this.personManager = personManager;
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
        //manager.getPerson(currentUserID).getContactList();
        // format list ? to what end ?
        // send the Presenter the formatted contactList to print (if empty, say so)
        return true;

    }

    /**
     * Add a contact to the current user's contactList
     * @param contactUsername the username of the contact the current user wants to add to their contactList
     * @return true iff the presenter printed a formatted contactList
     */
    public boolean addContact(String contactUsername) {
        //String contactID = manager.getCurrentUserID(contactUsername);
        //if ((manager.addContact(currentUserID, contactID) && manager.addContact(contactID, currentUserID))) {
        // update presenter to say contact was added
        //  return true;
        //}
        return true;
    }

}
