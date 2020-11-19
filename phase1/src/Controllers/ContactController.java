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
        currentRequest = SubMenu.readInteger(input);
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
            switch (currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1:
                    getContactList();
                    break;
                case 2:
                    presenter.printAddContactPrompt();
                    input.nextLine();
                    try {
                        addContact(input.nextLine());
                    } catch (NoDataException e) {
                        e.printErrorMessage();
                    }
                    break;
            }
        }
        while (currentRequest != 0);
        return true;
    }

    /**
     * Get's the current user's contactList
     */
    public void getContactList() {
        ArrayList<String> listOfContacts = pManager.getContactList(currentUserID);
        presenter.printContactList(listOfContacts);
    }

    /**
     * Add a contact to the current user's contactList
     * @param contactUsername the username of the contact the current user wants to add to their contactList
     */
    public void addContact(String contactUsername) throws NoDataException{
        String contactID = pManager.getCurrentUserID(contactUsername);
        if (contactID == null) {
            throw new NoDataException("contact username");
        }
        boolean a = pManager.addContactToPerson(currentUserID, contactID);
        boolean b = pManager.addContactToPerson(contactID, currentUserID);

        if(a && b) {
            presenter.printContactAdded();
        }
    }

}
