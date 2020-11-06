package Controllers;// Programmer: Cara McNeil
// Description: Controllers.Main menu for Person.Person.Organizer users.
// Date Created: 01/11/2020
// Date Modified: 04/11/2020

import Person.OrganizerManager;
import Person.SpeakerManager;

import java.util.Scanner;

public class OrganizerController extends PersonController {
    // private EventManager eManager = new EventManager(); ??
    // private Message.Message.ChatManager cManager = new Message.Message.ChatManager();
    // private Message.MessageManager mManager = new Message.MessageManager();
    Scanner input = new Scanner(System.in);
    int currentRequest;
    private String username;
    private String password;
    private String currentUserID;
    private SpeakerManager sManager;

    public OrganizerController(OrganizerManager oManager, SpeakerManager sManager) {
        super(oManager);
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

    /**
     * Get's the list of Events happening at the convention
     * @return true iff a formatted list of Events was displayed
     */
    public boolean getConventionEventList() {
        // eManager.getEventList();
        // update the presenter to show the list of Events
        return false;
    }

    /**
     * Get's the list of Events the Person.Person.Organizer user is signed up for
     * @return true iff the presenter has been updated woith a list of events
     */
    public boolean getUserEventList() {
        // List Eventlist = oManager.getSignedUpForEvents();
        // update the presenter with the list of events signed up for (if empty, say so)
        return false;
    }

    /**
     * Try to sign user up for an Event
     * @return true iff user was signed up for the Event
     */
    public boolean signupForEvent(String eventID) {
        // if eManager.signup(currentUerID, eventID)
        // oManager.addEvent(currentUserID, eventID)
        // update the presenter to say the Person.Person.Organizer user's been added to the event
        return false;
    }

    /**
     * Remove this user from Event
     * @return true iff user was removed from the Event
     */
    public boolean cancelEvent(String eventID) {
        // if eManager.remove(currentUserID, eventID)
        // oManager.cancelEvent(currentUserID, eventID)
        // update the presenter to say the Person.Person.Organizer user's been added to the event
        return false;
    }

    /**
     * Adds a room to the list of rooms in this Convention.
     * @param room
     * @return true iff list was added to system.
     */
    public boolean addRoom(String room) {
        return false;
    }

    /**
     * Creates a new Person.Speaker account and adds it to the system.
     * @param name
     * @param username
     * @param password
     * @param email
     * @return true iff a new Person.Speaker object was created.
     */
    public boolean createSpeaker(String name, String username, String password, String email) {
        return false;
    }


    /**
     * 
     * @param speakerUsername
     * @param room
     * @return
     */
    public boolean scheduleSpeaker(String speakerUsername, String room) {
        return false;
    }
    


}
