package Controllers;

import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Person.SpeakerManager;

public class SpeEventController implements SubMenu {

    private String currentUserID;
    private PersonManager personManager;
    // EventManager??
    private MessageManager messageManager;
    private ChatManager chatManager;

    public SpeEventController(String currentUserID, PersonManager personManager, MessageManager messageManager,
                              ChatManager chatManager) {
        this.currentUserID = currentUserID;
        this.personManager = personManager;
        this.messageManager = messageManager;
        this.chatManager = chatManager;
    }

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     * @return true iff choice was inputted
     */
    @Override
    public boolean menuOptions() {
        // SpeEventMenu.menuOptions()
        // choice = input.NextLine()
        return true;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        // menuOptions();
        // while (choice != 0)
        // do {
        // switch statement to decide method
        // }
        return true;
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
