package Controllers;

// Programmer: Cara McNeil
// Description: Main account page for Attendee users.
// Date Created: 01/11/2020
// Date Modified: 16/11/2020

import Events.EventManager;
import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.AttendeeManager;
import Person.PersonManager;
import View.Account.iViewGUI;
import View.Login.iViewPane;

import java.util.Scanner;

public class AttendeeController extends PersonController {
    private AttendeeManager manager;
    // TODO remove this: private MainMenu mainMenu = new MainMenu();
    private String currentUserID;
    private int currentRequest;
    private int typePerson;
    Scanner input = new Scanner(System.in);

    public AttendeeController(PersonManager manager, RoomManager rooms, EventManager events, MessageManager messages,
                              ChatManager chats, iViewPane viewPane, iViewGUI viewGUI) {
        super(manager, rooms, events, messages, chats, 1, viewPane, viewGUI);
        this.manager = (AttendeeManager) manager;
        //this.typePerson = 1;

    }

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */
    @Override
    public void run() {
        super.run();
        this.currentUserID = super.currentUserID;
        if (super.loggedIn) {
            do {
                // TODO replace this line with GUI equivalent: mainMenu.printAttendeeMM();
                currentRequest = SubMenu.readInteger(input);

                switch (currentRequest) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(currentUserID, manager);
                        contactController.menuChoice(); // TODO replace the break structure with a method that asks the view what choice has been made
                        break;
                    case 2:
                        AttMessageController messageController = new AttMessageController(currentUserID, manager,
                                messageManager, chatManager, eventManager);
                        messageController.menuChoice();
                        break;
                    case 3:
                        AttEventController attEventController = new AttEventController(currentUserID, manager,
                                roomManager, eventManager);
                        attEventController.menuChoice();
                        break;
                }
            }
        while (currentRequest != 0);
        }
    }
}
