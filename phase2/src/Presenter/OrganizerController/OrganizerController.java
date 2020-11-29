package Controllers.OrganizerController;

// Programmers: Cara McNeil, Eytan Weinstein
// Description: Main account page for OrganizerController users.
// Date Created: 01/11/2020
// Date Modified: 19/11/2020

import Controllers.AttendeeController.AttEventController;
import Controllers.AttendeeController.AttMessageController;
import Controllers.PersonController.ContactController;
import Controllers.PersonController.PersonController;
import Controllers.SubMenu;
import Events.EventManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.AttendeeManager;
import Person.OrganizerManager;
import Person.PersonManager;
import Person.SpeakerManager;
import Events.RoomManager;
import View.Account;
import View.Account.iViewGUI;
import View.Login.iViewPane;

import java.util.Scanner;

public class OrganizerController extends PersonController {
    private Account mainMenu = new Account();
    private String currentUserID;
    private OrganizerManager manager;
    private SpeakerManager speakerManager;
    private AttendeeManager attendeeManager;
    private int currentRequest;
    private int typePerson;
    Scanner input = new Scanner(System.in);


    public OrganizerController(PersonManager manager, SpeakerManager speakerManager, RoomManager rooms,
                               EventManager events, MessageManager messages, ChatManager chats, AttendeeManager am,
                               iViewPane viewPane, iViewGUI viewGUI) {
        super(manager, rooms, events, messages, chats, 2, viewPane, viewGUI);
        this.manager = (OrganizerManager) manager;
        this.speakerManager = speakerManager;
        this.attendeeManager = am;
        //this.typePerson = 2;
    }

    @Override
    public void run() {
        super.run();
        this.currentUserID = super.currentUserID;
        if (super.loggedIn) {
            do {
                // TODO replace this line with GUI equivalent: mainMenu.printOrganizerMM();
                currentRequest = SubMenu.readInteger(input);

                switch (currentRequest) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(currentUserID, manager);
                        contactController.menuChoice();
                        break;
                    case 2:
                        AttMessageController messageController = new AttMessageController(currentUserID,
                                attendeeManager, messageManager, chatManager, eventManager);
                        messageController.menuChoice();
                        break;
                    case 3:
                        AttEventController attEventController = new AttEventController(currentUserID, attendeeManager,
                                roomManager, eventManager);
                        attEventController.menuChoice();
                        break;
                    case 4:
                        OrgEventController orgEventController = new OrgEventController(currentUserID, manager,
                                speakerManager, roomManager, eventManager, messageManager, chatManager);
                        orgEventController.menuChoice();
                        break;
                }
            }
            while (currentRequest != 0);
        }
    }
    
}
