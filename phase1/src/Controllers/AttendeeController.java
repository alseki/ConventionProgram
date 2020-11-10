package Controllers;

// Programmer: Cara McNeil
// Description: Controllers.Main menu for Entities.Person.Person.Attendee users.
// Date Created: 01/11/2020
// Date Modified: 09/11/2020

import Person.AttendeeManager;
import Person.PersonManager;

import java.util.Scanner;

public class AttendeeController extends PersonController {
    // private EventManager eManager = new EventManager(); ??
    // private Message.Message.ChatManager cManager = new Message.Message.ChatManager();
    // private Message.MessageManager mManager = new Message.MessageManager();
    Scanner input = new Scanner(System.in);
    int currentRequest;
    private String username;
    private String password;
    private String currentUserID;
    private AttendeeManager manager;

    public AttendeeController(PersonManager manager) {
        super(manager);
        this.manager = (AttendeeManager) manager;
    }

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */
    @Override
    void run() {
        // will use currentRequest to determine what methods to call
        // if any classes return false, needs to update the presenter accordingly
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
     * Get's the list of Events the Person.Person.Attendee user is signed up for
     * @return true iff the presenter has been updated woith a list of events
     */
    public boolean getUserEventList() {
        // List Eventlist = aManager.getSignedUpForEvents(currentUserID);
        // update the presenter with the list of events signed up for (if empty, say so)
        return false;
    }

    /**
     * Try to sign user up for an Event
     * @param eventName The name of the Event the current user requested to sign up for
     * @return true iff user was signed up for the Event
     */
    public boolean signupForEvent(String eventName) {
        // if eManager.signup(currentUerID, eventName)
        // aManager.addEvent(currentUserID, eventName)
        // update the presenter to say the Person.Person.Attendee user's been added to the event
        return false;
    }

    /**
     * Remove this user from Event
     * @param eventName The name of the Event the current user requested to cancel
     * @return true iff user was removed from the Event
     */
    public boolean cancelEvent(String eventName) {
        // if eManager.remove(currentUserID, eventName)
        // aManager.cancelEvent(currentUserID, eventName)
        // update the presenter to say the Person.Person.Attendee user's been added to the event
        return false;
    }





}
