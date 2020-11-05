package Controllers;

// Programmer: Cara McNeil
// Description: abstract main menu for other controllers to inherit from
// Date Created: 01/11/2020
// Date Modified: 04/11/2020

import java.util.Scanner;

abstract public class PersonController {
    private String username;
    private String password;
    private String currentUserID;

    abstract void run();

    // This should be moved to a Presenter class
    /**
     * Prints the options that are available to the current user.
     */
    /*public int mainMenu() {
        System.out.println("~Controllers.Main Menu~" + '\n');
        System.out.println("Please select from the following options:");
        System.out.println("To logout, Enter '1';");
        System.out.println("To view Contact List, Enter '2';");
        System.out.println("To add to Contact List, Enter '3';");
        System.out.println("To view Chat list, Enter '4';");
        return 0;
    }*/

    abstract boolean login();

    abstract boolean createAccount();

    abstract boolean getContactList();

    abstract boolean addContact(String contactUsername);

    abstract boolean getChats();

    abstract boolean createChat(String contactUsername);

    abstract boolean addMessage(String contactUsername, String messageContent);

    abstract boolean getMessages(String chatID);
}
