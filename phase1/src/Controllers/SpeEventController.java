package Controllers;

import Events.EventManager;
import Events.RoomManager;
import Message.AnnouncementChat;
import Message.ChatManager;
import Message.MessageManager;
import Person.Attendee;
import Person.PersonManager;
import Person.SpeakerManager;
import Presenter.SpeEventMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SpeEventController implements SubMenu {

    private String currentUserID;
    private int currentRequest;
    private PersonManager personManager;
    private MessageManager messageManager;
    private ChatManager chatManager;
    private EventManager eventManager;
    private SpeEventMenu presenter = new SpeEventMenu();
    Scanner input = new Scanner(System.in);

    public SpeEventController(String currentUserID, PersonManager personManager, MessageManager messageManager,
                              ChatManager chatManager, EventManager eventManager) {
        this.currentUserID = currentUserID;
        this.personManager = personManager;
        this.messageManager = messageManager;
        this.chatManager = chatManager;
        this.eventManager = eventManager;
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
     * @param messageContent Content of the Message to be sent
     * @return true iff the Message was sent to every user on the event list
     */
    public boolean eventMessage(String eventName, String messageContent) {
        String eventID = eventManager.getEventID(eventName);
        ArrayList<String> attIDs = eventManager.getSignUps(eventID);
        String messageID = messageManager.createMessage(eventID, messageContent);
        String acID = chatManager.createAnnouncementChat(eventID, attIDs);
        chatManager.addMessage(acID, messageID);
        return true;
    }

    /**
     * Sends a Message to one Attendee signed up for an event
     * @param eventName The name of the Event
     * @param attendeeUsername Th username of the Attendee the user wishes to message
     * @return true iff the message was sent to the corresponding Attendee
     */
    public boolean eventMessage(String eventName, String messageContent, String attendeeUsername) {
        String eventID = eventManager.getEventID(eventName);
        String attID = personManager.getCurrentUserID(attendeeUsername);
        String[] attendeeID = new String[]{attID};
        String messageID = messageManager.createMessage(eventID, messageContent);
        String acID = chatManager.createAnnouncementChat(eventID, attendeeID);
        chatManager.addMessage(acID, messageID);
        return true;
        }
    //List<String> attList = Arrays.asList(eventManager.getSignUps(eventManager.getEventID(eventName)));
    //if (!(attList.contains(attID))){
    //throw new InvalidIDException();
    //    return false;}

    /**
     * Sends a Message to every user signed up for every event this user is speaking at
     * @return true iff the Message was sent to every user on the event list for every event this user is speaking at
     */
    public boolean allEventMessage() {

        return true;
    }
}
