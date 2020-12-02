package Presenter.OrganizerController;

// Programmers: Cara McNeil, Eytan Weinstein, Sarah Kronenfeld
// Description: Main account page for OrganizerController users.
// Date Created: 01/11/2020
// Date Modified: 29/11/2020

import Person.*;
import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttMessageController;
import Presenter.AttendeeController.AttReqController;
import Presenter.Central.SubMenu;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.MessageController;
import Presenter.PersonController.PersonController;
import Event.EventManager;
import Message.ChatManager;
import Message.MessageManager;
import Event.RoomManager;
import Presenter.SpeakerController.SpeEventController;
import Request.RequestManager;

public class OrganizerController extends PersonController {
    private String currentUserID;
    private OrganizerManager manager;
    private SpeakerManager speakerManager;
    private AttendeeManager attendeeManager;


    public OrganizerController(OrganizerManager manager, SpeakerManager sm, RoomManager rooms,
                               EventManager events, MessageManager messages, ChatManager chats, AttendeeManager am,
                               RequestManager rm) {
        super(manager, rooms, events, messages, chats, rm, 2);
        this.manager = manager;
        this.speakerManager = sm;
        this.attendeeManager = am;
    }


    public OrganizerController(SubMenu subMenu, SpeakerManager sm, AttendeeManager am, OrganizerManager om) {
        super(subMenu, 2);
        this.manager = om;
        this.speakerManager = sm;
        this.attendeeManager = am;
    }

    /**
     * Creates the next controller according to the user's menu choice
     */
    @Override
    public SubMenu createController(int choice) {
        if (super.loggedIn) {
            switch (choice) {
                case 1:
                    return new ContactController(this, currentUserID);
                case 2:
                    return new AttMessageController(this, currentUserID, attendeeManager);
                case 3:
                    return new AttEventController(this, currentUserID, attendeeManager);
                case 4:
                    return new OrgReqController(this, currentUserID);
                case 5:
                    return new OrgEventController(this, currentUserID, speakerManager);
            }
        }
        return null;
    }

    @Override
    public String[] getMenuOptions() {
        String[] options  = new String[6];
        System.arraycopy(super.getMenuOptions(), 0, options, 0, 3);
        options[3] = "View your Event information";
        options[4] = "View and manage Attendee Requests";
        options[5] = "View and manage the conference's Event list";
        return options;
    }
    
}
