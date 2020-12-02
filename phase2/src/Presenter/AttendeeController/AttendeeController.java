package Presenter.AttendeeController;

// Programmer: Cara McNeil
// Description: Main account page for AttendeeController users.
// Date Created: 01/11/2020
// Date Modified: 16/11/2020

import Presenter.PersonController.ContactController;
import Presenter.PersonController.PersonController;
import Presenter.Central.SubMenu;
import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.AttendeeManager;
import Request.RequestManager;

import java.util.Scanner;

public class AttendeeController extends PersonController {
    private AttendeeManager manager;
    private String currentUserID;
    Scanner input = new Scanner(System.in);
    String[] arrMenuOptions;

    public AttendeeController(AttendeeManager manager, RoomManager rooms, EventManager events, MessageManager messages,
                              ChatManager chats, RequestManager requests) {
        super(manager, rooms, events, messages, chats, requests, 1);
        this.manager = manager;

        // TODO make sure "Message Menu" option calls an AttMessageController
        menuOptions.add("Event Menu"); // AttEventController
        menuOptions.add("Request Menu"); // AttReqController

        arrMenuOptions = new String[menuOptions.size()];
        for (int i = 0; i < menuOptions.size(); i++) {
            arrMenuOptions[i] = menuOptions.get(i);
        }
    }

    public AttendeeController(AttendeeManager manager, SubMenu subMenu) {
        super(subMenu, 1);
        this.manager = manager;
    }

    @Override
    public void MessageMenu() {

    }

    public void AttEventMenu() {

    }

    public void AttMessageMenu() {

    }

    public void EventMenu() {

    }

    public void RequestMenu() {

    }

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */
    public void run() {
        this.currentUserID = super.currentUserID;
        if (super.loggedIn) {
            do {
                switch (1) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(this, currentUserID);
                        contactController.menuChoice(); // TODO replace the break structure with a method that asks the view what choice has been made
                        break;
                    case 2:
                        AttMessageController messageController = new AttMessageController(currentUserID, manager,
                               this);
                        messageController.menuChoice();
                        break;
                    case 3:
                        AttEventController attEventController = new AttEventController(currentUserID, manager,
                                this);
                        attEventController.menuChoice();
                        break;
                    case 4: //TODO allow for access to ATTReqController
                }
            }
        while (true);
        }
    }
}
