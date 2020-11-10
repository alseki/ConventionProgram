package Controllers;

// Programmer: Cara McNeil
// Description: Controllers.Main menu for Person.Speaker users.
// Date Created: 01/11/2020
// Date Modified: 09/11/2020


import Person.SpeakerManager;

import java.util.Scanner;

public class SpeakerController extends PersonController {
    // private EventManager eManager = new EventManager(); ??
    // private Message.Message.ChatManager cManager = new Message.Message.ChatManager();
    // private Message.MessageManager mManager = new Message.MessageManager();
    Scanner input = new Scanner(System.in);
    int currentRequest;
    private String username;
    private String password;
    private String currentUserID;

    public SpeakerController(SpeakerManager manager) {
        super(manager);
    }

    // login()
    // createAccount()
    // getContactList()
    // addContact(String contactUsername)
    // getChats()
    // createChat(String contactUsername)
    // addMessage(String chatID, String messageContent)
    // getMessages(String contactUsername)

    @Override
    void run() {

    }

    /**
     * Get the list of talk the user is scheduled to speak at
     * @return true iff a list of talks has been gotten
     */
    public boolean getTalks() {
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

    /**
     * Sends a Message to every user signed up for every event this user is speaking at
     * @return true iff the Message was sent to every user on the event list for every event this user is speaking at
     */
    public boolean allEventMessage() {
        return false;
    }

}
