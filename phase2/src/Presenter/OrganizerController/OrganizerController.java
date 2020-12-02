package Presenter.OrganizerController;

// Programmers: Cara McNeil, Eytan Weinstein, Sarah Kronenfeld
// Description: Main account page for OrganizerController users.
// Date Created: 01/11/2020
// Date Modified: 29/11/2020

import Person.*;
import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttMessageController;
import Presenter.Central.SubMenu;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.PersonController;
import Event.EventManager;
import Message.ChatManager;
import Message.MessageManager;
import Event.RoomManager;
import Request.RequestManager;

public class OrganizerController extends PersonController {
    private String currentUserID;
    private OrganizerManager manager;
    private SpeakerManager speakerManager;
    private AttendeeManager attendeeManager;
    String[] arrMenuOptions;


    public OrganizerController(OrganizerManager manager, SpeakerManager sm, RoomManager rooms,
                               EventManager events, MessageManager messages, ChatManager chats, AttendeeManager am,
                               RequestManager rm) {
        super(manager, rooms, events, messages, chats, rm, 2);
        this.manager = manager;
        this.speakerManager = sm;
        this.attendeeManager = am;

        // TODO make sure "Message Menu" option calls an AttMessageController
        menuOptions.add("Attendee Event Menu"); // AttEventController
        menuOptions.add("Organizer Event Menu"); // OrgEventController
        menuOptions.add("Request Menu"); // OrgReqController

        arrMenuOptions = new String[menuOptions.size()];
        for (int i = 0; i < menuOptions.size(); i++) {
            arrMenuOptions[i] = menuOptions.get(i);
        }
    }


    public OrganizerController(SubMenu subMenu, SpeakerManager sm, AttendeeManager am, OrganizerManager om) {
        super(subMenu, 2);
        this.manager = om;
        this.speakerManager = sm;
        this.attendeeManager = am;
    }

    public void OrgEventMenu() {

    }

    public void run() {
        this.currentUserID = super.currentUserID;
        int currentRequest = 1;
        if (super.loggedIn) {
            do {
                // TODO replace this line with GUI equivalent: mainMenu.printOrganizerMM();
                //currentRequest = SubMenu.readInteger(input);

                switch (currentRequest) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(this, currentUserID);
                        contactController.menuChoice();
                        break;
                    case 2:
                        AttMessageController messageController = new AttMessageController(currentUserID,
                                attendeeManager, this);
                        messageController.menuChoice();
                        break;
                    case 3:
                        AttEventController attEventController = new AttEventController(currentUserID,
                                attendeeManager, this);
                        attEventController.menuChoice();
                        break;
                    case 4:
                        OrgEventController orgEventController = new OrgEventController(currentUserID, speakerManager,
                                this);
                        orgEventController.menuChoice();
                        break;
                    case 5: //TODO add OrgReqController stuff.
                }
            }
            while (currentRequest != 0);
        }
    }
    
}
