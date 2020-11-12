package Controllers;// Programmer: Cara McNeil
// Description: Controllers.Main menu for Person.Person.Organizer users.
// Date Created: 01/11/2020
// Date Modified: 09/11/2020

import Person.OrganizerManager;
import Person.PersonManager;
import Person.SpeakerManager;

import java.util.Scanner;

public class OrganizerController extends AttendeeController {
    // private EventManager eManager = new EventManager(); ??
    // private Message.Message.ChatManager cManager = new Message.Message.ChatManager();
    // private Message.MessageManager mManager = new Message.MessageManager();
    Scanner input = new Scanner(System.in);
    int currentRequest;
    private String username;
    private String password;
    private String currentUserID;
    private OrganizerManager manager;
    private SpeakerManager sManager;

    public OrganizerController(PersonManager manager, SpeakerManager sManager) {
        super(manager);
        this.manager = (OrganizerManager) manager;
        this.sManager = sManager;
    }

    @Override
    void run() {

    }

    // login()
    // createAccount()
    // getContactList()
    // addContact(String contactUsername)
    // getChats()
    // createChat(String contactUsername)
    // addMessage(String chatID, String messageContent)
    // getMessages(String contactUsername)

    // getConventionEventList()
    // getUserEventList()
    // signupForEvent()
    // cancelEvent()

    // NOTE: make all classes private except run?


    /**
     * Adds a room to the list of rooms in this Convention.
     * @param room The name/number of a room in the convention
     * @return true iff list was added to system.
     */
    public boolean addRoom(int room) {

        // eManager.addRoom(room);
        return false;
    }

    /**
     * Creates a new Person.Speaker account and adds it to the system.
     * @param name The name of the Speaker
     * @param username The username of the Speaker
     * @param password The password of the Speaker
     * @param email The email of the Speaker
     * @return true iff a new Person.Speaker object was created.
     */
    public boolean createSpeaker(String name, String username, String password, String email) {
        // check if person is already existing, if not creat the person.
        return false;
    }


    /**
     * Adds Speaker to an Event being held in a single room. At most one speaker speaks in each room at a given time
     * @param speakerUsername The username of the Speaker the current user wishes to schedule
     * @param room The name/number of a room in the convention
     * @return true iff the Speaker was added to the Event
     */
    private boolean scheduleSpeaker(String speakerUsername, String room) {
        return false;
    }


    /**
     * Creates a new Event for the convention
     * @param eventName The name of the Event the user has requested to create
     * @return true iff the Event was created
     */
    public boolean createEvent(String eventName, String speakerUsername, String room, int duration) {
        return false;
    }

    /**
     * Sends a Message to every user signed up for an event
     * @param eventName The name of the Event
     * @return true iff the Message was sent to every user on the event list
     */
    public boolean eventMessage(String eventName) {
        return false;
    }

    /**
     * Sends a Message to one Attendee signed up for an event
     * @param eventName The name of the Event
     * @param attendeeUsername Th username of the Attendee the user wishes to message
     * @return true iff the message was sent to the corresponding Attendee
     */
    public boolean eventMessage(String eventName, String attendeeUsername) {
        return false;
    }


}
