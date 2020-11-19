package Controllers;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: abstract main account page for other controllers to inherit from
// Date Created: 01/11/2020
// Date Modified: 16/11/2020

import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;

import java.util.Scanner;

abstract public class PersonController {
    Scanner input = new Scanner(System.in);
    public String currentUserID;
    private PersonManager manager;
    protected RoomManager roomManager;
    protected MessageManager messageManager;
    protected ChatManager chatManager;
    private LoginController loginController;
    public boolean loggedIn =  false;


    public PersonController(PersonManager manager, RoomManager roomManager, MessageManager messageManager,
                            ChatManager chatManager) {
        this.manager = manager;
        this.roomManager = roomManager;
        this.messageManager = messageManager;
        this.chatManager = chatManager;
    }

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */
    public void run() {
        loginController = new LoginController(manager);
        loginController.menuChoice();
        if (loginController.loggedIn) {
            loggedIn = true;
            currentUserID = manager.getCurrentUserID(loginController.username);
        }
    }
}
