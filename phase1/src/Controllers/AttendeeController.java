package Controllers;// Programmer: Cara McNeil
// Description: Controllers.Main menu for Entities.Attendee users.
// Date Created: 01/11/2020
// Date Modified: 05/11/2020

import java.util.Scanner;

public class AttendeeController extends PersonController {
    // private EventManager eManager = new EventManager(); ??
    // private ChatManager cManager = new ChatManager();
    // private MessageManager mManager = new MessageManager();
    Scanner input = new Scanner(System.in);
    int currentRequest;
    private String username;
    private String password;
    private String currentUserID;
    private AttendeeManager manager;

    public AttendeeController(AttendeeManager manager) {
        super(manager);
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
     * Get's the list of Events the Attendee user is signed up for
     * @return true iff the presenter has been updated woith a list of events
     */
    public boolean getUserEventList() {
        // List Eventlist = aManager.getSignedUpForEvents(currentUserID);
        // update the presenter with the list of events signed up for (if empty, say so)
        return false;
    }

    /**
     * Try to sign user up for an Event
     * @return true iff user was signed up for the Event
     */
    public boolean signupForEvent(String eventID) {
        // if eManager.signup(currentUerID, eventID)
        // aManager.addEvent(currentUserID, eventID)
        // update the presenter to say the Attendee user's been added to the event
        return false;
    }

    /**
     * Remove this user from Event
     * @return true iff user was removed from the Event
     */
    public boolean cancelEvent(String eventID) {
        // if eManager.remove(currentUserID, eventID)
        // aManager.cancelEvent(currentUserID, eventID)
        // update the presenter to say the Attendee user's been added to the event
        return false;
    }





}
