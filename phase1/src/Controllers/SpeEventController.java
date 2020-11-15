package Controllers;

import Events.EventManager;
import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Person.SpeakerManager;
import Presenter.SpeEventMenu;

import java.util.Scanner;

public class SpeEventController implements SubMenu {

    private String currentUserID;
    private int currentRequest;
    private PersonManager personManager;
    // EventManager??
    private MessageManager messageManager;
    private ChatManager chatManager;
    private SpeEventMenu presenter = new SpeEventMenu();
    Scanner input = new Scanner(System.in);

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
        presenter.printMenuOptions();
        // TODO update presenter class with a print statement for each option
        currentRequest = input.nextInt();
        return true;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        do {
            menuOptions();
            // TODO add switch statement to call the methods that correspond with currentRequest
        }
        while (currentRequest != 0);
        return true;
    }

    /**
     * Get the list of talk the user is scheduled to speak at
     * @return true iff a list of talks has been gotten
     */
    public boolean getTalks() {
        return true;
    }

    /**
     * Sends a Message to every user signed up for an event
     * @param eventName The name of the Event
     * @return true iff the Message was sent to every user on the event list
     */
    public boolean eventMessage(String eventName) {
        String eventId = EventManager.;

        return true;
    }

    /**
     * Sends a Message to one Attendee signed up for an event
     * @param eventName The name of the Event
     * @param attendeeUsername Th username of the Attendee the user wishes to message
     * @return true iff the message was sent to the corresponding Attendee
     */
    public boolean eventMessage(String eventName, String attendeeUsername) {
        return true;
    }

    /**
     * Sends a Message to every user signed up for every event this user is speaking at
     * @return true iff the Message was sent to every user on the event list for every event this user is speaking at
     */
    public boolean allEventMessage() {
        return true;
    }
}
