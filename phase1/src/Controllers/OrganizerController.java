package Controllers;

// Programmer: Cara McNeil
// Description: Controllers.Main menu for Organizer users.
// Date Created: 01/11/2020
// Date Modified: 04/11/2020

import java.util.Scanner;

public class OrganizerController extends PersonController{
    // private OrganizerManager oManager = new OrganizerManager();
    // private Events.EventManager eManager = new Events.EventManager(); ??
    // private ChatManager cManager = new ChatManager();
    // private MessageManager mManager = new MessageManager();
    Scanner input = new Scanner(System.in);
    int currentRequest;
    private String username;
    private String password;
    private String currentUserID;

    @Override
    void run() {

    }

    /**
     * Prompts user to input username and password.
     * @return true iff login info corresponds with an existing Organizer account.
     */
    @Override
    boolean login() {
        System.out.println("Enter your username: ");
        username = input.nextLine();
        System.out.println("Enter your password: ");
        password = input.nextLine();
        // if .findOrganizer(username, password) == true
        // currentUserID = oManager.getOrganizer(username, password)
        return false;
    }

    /**
     * Prompts user for relevant information and uses it to create a new Organizer account.
     * @return true iff new account has been created
     */
    @Override
    boolean createAccount() {
        System.out.println("Enter your full name: ");
        String name = input.nextLine();
        System.out.println("Enter a username for your account: ");
        String username = input.nextLine();
        System.out.println("Enter your password for your account: ");
        String password = input.nextLine();
        System.out.println("Enter your email: ");
        String email = input.nextLine();
        // oManager.createAccount(name, username, password, email)
        return false;
    }

    /**
     * Get's the Organizer user's contactList
     * @return true iff the presenter printed a formatted contactList
     */
    @Override
    public boolean getContactList() {
        // oManager.getContactList(currentUserID);
        // format list
        // send the Presenter the formatted contactList to print (if empty, say so)
        return false;
    }

    /**
     * Add a contact to the Organizer user's contactList
     * @return true iff the presenter printed a formatted contactList
     */
    @Override
    public boolean addContact(String contactUsername) {
        // String contactID = oManager.getID(contactUsername)
        // if oManager.addContact(currentUserID, contactID) and oManager.addContact(contactID, currentUserID):
        // update presenter to say contact was added
        return false;
    }

    /**
     * Get's the Organizer user's Chats
     * @return true iff the presenter printed a formatted list of Chats
     */
    @Override
    public boolean getChats() {
        // List Chats = oManager.getChatIDs(currentUserID);
        // List formattedChats;
        // for item in Chats:
        // formattedChats.add(cManager.format(chatID))
        // update presenter with the formatted Chats, i.e. contact usernames they have Chats with, to print
        // (if empty, say so)
        return false;
    }

    /**
     * Creates new Chat if contact is on contactList
     * @return true iff new Chat was created and added to user's Chat list and contact's contactList
     */
    @Override
    public boolean createChat(String contactUsername) {
        // contactID = oManager.getID(contactUsername)
        // if oManager.checkContact(contactID)
        // String chatID = cManager.createChat(currentUserID, contactID)
        // if oManager.addChat(currentUserID, chatID) and oManager.addChat(contactID, chatID)
        // update presenter to say Chat was created
        return false;
    }

    /**
     * Creates new Message for existing Chat
     * @return true iff new Message was created and added to Chat's messageList
     */
    @Override
    public boolean addMessage(String chatID, String messageContent) {
        // String messageID = mManager.createMessage(currentUser, messageContent);
        // cManager.addMessage(chatID, messageID)
        // update presenter to say message has been sent
        return false;
    }

    /**
     * Get's the Organizer user's Chat messages
     * @return true iff presenter was updated with a formatted list of Chat messages
     */
    @Override
    public boolean getMessages(String contactUsername) {
        // chatID = cManager.getChatID(currentUserID)
        // if chatID in oManager.getChats(currentUserID)
        // List messageIDs = cManager.getMessages(chatID);
        // List chatMessages;
        // for item in messageIDs:
        // chatMessages.add(mManager.getFormattedMessage(item))
        // send the Presenter the formatted chatMessages to print
        return false;
    }

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
