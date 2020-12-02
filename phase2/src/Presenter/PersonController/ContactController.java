package Presenter.PersonController;

// Programmer: Cara McNeil
// Description: All the methods that take user input in the Contact Menu
// Date Created: 01/11/2020
// Date Modified: 14/11/2020



import Presenter.Central.SubMenu;
import Presenter.InvalidChoiceException;
import Person.PersonManager;

import java.util.Scanner;
import java.util.ArrayList;

public class ContactController extends SubMenu {

    private String currentUserID;
    private ContactMenu presenter = new ContactMenu();

    public ContactController(SubMenu subMenu, String currentUserID) {
        super(subMenu);
        this.currentUserID = currentUserID;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     */
    public void menuChoice() {
        do {
            switch (1) {
                case 0:
                    // return to main menu
                    break;
                case 1:
                    try {
                        getContactList();
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                    }
                    break;
                case 2:
                    presenter.printAddContactPrompt();
                    try {
                        addContact("");//SubMenu.readInput(input));
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                    }
                    break;
            }
        }
        while (true);
    }

    /**
     * Get's the current user's contactList
     */
    private void getContactList() throws InvalidChoiceException {
        ArrayList<String> listOfContacts = personManager.getContactList(currentUserID);
        presenter.printContactList(listOfContacts);
    }

    /**
     * Add a contact to the current user's contactList
     * @param contactUsername the username of the contact the current user wants to add to their contactList
     */
    private void addContact(String contactUsername) throws InvalidChoiceException{
        String contactID = personManager.getCurrentUserID(contactUsername);
        if (contactID == null) {
            throw new InvalidChoiceException("user");
        }
        boolean a = personManager.addContactToPerson(currentUserID, contactID);
        boolean b = personManager.addContactToPerson(contactID, currentUserID);

        if(a && b) {
            presenter.printContactAdded();
        }
    }

}
