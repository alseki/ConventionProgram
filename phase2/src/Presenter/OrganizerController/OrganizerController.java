package Presenter.OrganizerController;

// Programmers: Cara McNeil, Eytan Weinstein, Sarah Kronenfeld
// Description: Main account page for OrganizerController users.
// Date Created: 01/11/2020
// Date Modified: 29/11/2020

import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.AttendeeManager;
import Person.EmployeeManager;
import Person.OrganizerManager;
import Person.SpeakerManager;
import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttMessageController;
import Presenter.Central.SubMenu;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.PersonController;
import Request.RequestManager;

public class OrganizerController extends PersonController {
    private String currentUserID;
    private OrganizerManager manager;
    private SpeakerManager speakerManager;
    private AttendeeManager attendeeManager;
    private EmployeeManager employeemanager;

    /**
     * constructor for organizer controller
     * @param manager OrganizerManager
     * @param sm SpeakerManager
     * @param rooms roommanager
     * @param events eventmanager
     * @param messages messagemanager
     * @param chats chatmanager
     * @param am attendeemanager
     * @param rm requestmanager
     */
    public OrganizerController(OrganizerManager manager, SpeakerManager sm, RoomManager rooms,
                               EventManager events, MessageManager messages, ChatManager chats, AttendeeManager am,
                               EmployeeManager em, RequestManager rm) {
        super(manager, rooms, events, messages, chats, rm, 2);
        this.manager = manager;
        this.speakerManager = sm;
        this.attendeeManager = am;
        this.employeemanager = em;
    }

    /**
     * contructor for organizer controller
     * @param subMenu submenu
     * @param sm    speaker manager
     * @param am attendee manager
     * @param om organizermanager
     */
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
    public SubMenu createController(String choice) {
        String[] options = getMenuOptions();
        if (super.loggedIn) {
            if (choice.equals(options[0])) {
                return new ContactController(this, currentUserID);
            }
            else if (choice.equals(options[1])) {
                return new AttMessageController(this, currentUserID, attendeeManager);
            }
            else if (choice.equals(options[2])) {
                return new AttEventController(this, currentUserID, attendeeManager);
            }
            else if (choice.equals(options[3])){
                return new OrgReqController(this, currentUserID);
            }
//            else if (choice.equals(options[4])) {
//                return new OrgReqController(this, currentUserID);
//            }
            else if (choice.equals(options[4])) {
                return new OrgEventController(this, currentUserID, speakerManager);
            }
        }
        return null;
    }

    /**
     * getter for the menu options for the Organizr contorller
     * @return list of strings representing the menu opetions
     */
    @Override
    public String[] getMenuOptions() {
        String[] orgOptions  = {"View your event information", "View attendees' requests",
                "View & edit conference event list"};
        String[] options = new String[5];
        System.arraycopy(super.getMenuOptions(), 0, options, 0, 2);
        System.arraycopy(orgOptions, 0, options, 2, 3);
        return options;
    }

    
}
