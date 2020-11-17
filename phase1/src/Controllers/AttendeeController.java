package Controllers;

// Programmer: Cara McNeil
// Description: Main account page for Attendee users.
// Date Created: 01/11/2020
// Date Modified: 16/11/2020

import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.AttendeeManager;
import Person.PersonManager;
import Presenter.MainMenu;

import java.util.Scanner;

public class AttendeeController extends PersonController {
    private ChatManager chatManager = new ChatManager();
    private AttendeeManager manager;
    private MainMenu mainMenu = new MainMenu();
    private String currentUserID;
    private int currentRequest;
    Scanner input = new Scanner(System.in);

    public AttendeeController(PersonManager manager, RoomManager rooms, MessageManager messages) {
        super(manager, rooms, messages);
        this.manager = (AttendeeManager) manager;
        roomManager = rooms;
        messageManager = messages;
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
                mainMenu.printAttendeeMM();
                currentRequest = input.nextInt();

                switch (currentRequest) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(currentUserID, manager);
                        contactController.menuChoice();
                        break;
                    case 2:
                        MessageController messageController = new MessageController(currentUserID, manager,
                                messageManager, chatManager);
                        messageController.menuChoice();
                        break;
                    case 3:
                        AttEventController attEventController = new AttEventController(currentUserID, manager, roomManager);
                        attEventController.menuChoice();
                        break;
                }
            }
        while (currentRequest != 0);
        }
    }
}
