// Programmer: Cara McNeil
// Description: Main menu for Organizer users.
// Date Created: 01/11/2020
// Date Modified: 04/11/2020

import java.util.Scanner;

public class OrganizerController extends PersonController {
    // private EventManager eManager = new EventManager(); ??
    // private ChatManager cManager = new ChatManager();
    // private MessageManager mManager = new MessageManager();
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
     * Get's the list of Events the Organizer user is signed up for
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
        // update the presenter to say the Organizer user's been added to the event
        return false;
    }

    /**
     * Remove this user from Event
     * @return true iff user was removed from the Event
     */
    public boolean cancelEvent(String eventID) {
        // if eManager.remove(currentUserID, eventID)
        // oManager.cancelEvent(currentUserID, eventID)
        // update the presenter to say the Organizer user's been added to the event
        return false;
    }

    


}
