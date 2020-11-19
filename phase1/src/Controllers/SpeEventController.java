package Controllers;

import Events.EventManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Person.Speaker;
import Presenter.SpeEventMenu;

import java.util.ArrayList;
import java.util.Arrays;
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
        currentRequest = SubMenu.readInteger(input);
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
            switch (currentRequest){
                case 0:
                    // return to main menu
                    break;
                case 1:
                    // View the talk you're going to talk at
                    presenter.printString(this.getFormattedTalks());
                    break;
                //case 2:
                    // Send message to all Users in an Event
                    //presenter.printEventNamePrompt();
                    //String eventNameA = input.nextLine();
                    //presenter.printContentPrompt();
                    //String contentA = input.nextLine();
                    //this.eventMessage(eventNameA, contentA);
                    //presenter.printMessageSent();
                    //break;
                case 2:
                    // Send message to all Attendees in an Event
                    presenter.printEventNamePrompt();
                    String eventNameB = input.nextLine();
                    presenter.printContentPrompt();
                    String contentB = input.nextLine();
                    this.eventMessageForAttendees(eventNameB, contentB);
                    presenter.printMessageSent();
                    break;
                case 3:
                    // Send message to all Attendees in all of your Events
                    presenter.printContentPrompt();
                    String contentC = input.nextLine();
                    this.allSpeakerEventsMessagingById(contentC);
                    presenter.printMessageSent();
                    break;
                case 4:
                    // Send message to all Attendees in some of your Events
                    presenter.printContentPrompt();
                    String contentD = input.nextLine();
                    presenter.printEventNamesPrompt();
                    String eventNames = input.nextLine();
                    String[] someSpeakerEvents = eventNames.split(",");
                    this.allSpeakerEventsMessage(someSpeakerEvents, contentD);
                    presenter.printMessageSent();
                    break;
                case 5:
                    // to be finished
                    break;
                case 6:
                    // Send message to one Attendee in an Event
                    // But we didn't plan to allow a Speaker to send personal message to a User.
                    // Speakers can only send announcements.
                    break;

            }
        }
        while (currentRequest != 0);
        return true;
    }


    /**
     * Get the list of talk the user is scheduled to speak at
     * @return String chunk of formatted Talks
     */
    public String getFormattedTalks() {
        StringBuilder result = new StringBuilder();
        ArrayList <String> events = personManager.getEventList(currentUserID);
        for (String e: events){
            result.append(eventManager.getFormattedEvent(e)).append("\n");}
        return result.toString();
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
        String acID = chatManager.createAnnouncementChat(eventID, new ArrayList<String>(attIDs));
        chatManager.addMessage(acID, messageID);
        return true;
    }

    /**
     * This is to sent a message to all attendees of an event. It uses method eventMessage from below
     * @param eventName
     * @param messageContent
     * @return boolean; returns true if sending message to all attendees is successful.
     */
    public boolean eventMessageForAttendees(String eventName, String messageContent) {
        String eventID = eventManager.getEventID(eventName);
        ArrayList<String> attendeeList = eventManager.getSignUps(eventID);
        for (String attendee : attendeeList) {
            eventMessage(eventName, messageContent, attendee);
        }
        return true;
    }

    /**
     * This is to send a message to all attendees of all of Speaker's events. For example, if Speaker wanted to announce, "download
     * so and so application for the our talk today" to all talks, this method proves useful. This uses eventMessageForAttendees from above
     * @param allSpeakerEvents ArrayList of Event Names hosted by the speaker
     * @param messageContent String representing content of the message
     * @return boolean; returns true if sending message to all attendees of all of a speaker's event was successful
     */

    public boolean allSpeakerEventsMessage(ArrayList<String> allSpeakerEvents, String messageContent) {
            for (String eventName : allSpeakerEvents) {
                eventMessageForAttendees(eventName, messageContent);
            }
            return true;
    }

    /**
     * This is to send a message to all attendees of all of Speaker's events. For example, if Speaker wanted to announce, "download
     * so and so application for the our talk today" to all talks, this method proves useful. This uses eventMessageForAttendees from above
     * @param allSpeakerEvents String Array of Names of Events hosted by the speaker
     * @param messageContent String representing content of the message
     * @return boolean; returns true if sending message to all attendees of all of a speaker's event was successful
     */

    public boolean allSpeakerEventsMessage(String[] allSpeakerEvents, String messageContent) {
        for (String eventName : allSpeakerEvents) {
            eventMessageForAttendees(eventName, messageContent);
        }
        return true;
    }

    /**
     * This is the same method from above, but only using speakerId as a parameter instead of the list of all Speaker talks
     * @param messageContent
     * @return boolean; returns true if sending message to all attendees of all of a speaker's event was successful
     */
    public boolean allSpeakerEventsMessagingById(String messageContent) {
        ArrayList <String> allSpeakerEvents = personManager.getSpeakerIdAllTalks(currentUserID);
        allSpeakerEventsMessage(allSpeakerEvents, messageContent);
        return true;

    }

    /**
     * This is the same method from above (allSpeakerEventsMessage), but only using speaker's username as a parameter instead of the list of all Speaker talks
     * @param username
     * @param messageContent
     * @return
     */
    public boolean allSpeakerEventsMessagingByUsername(String username, String messageContent) {
        ArrayList <String> allSpeakerEvents = personManager.getSpeakerAllTalks(username);
        allSpeakerEventsMessage(allSpeakerEvents, messageContent);

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
        String acID = chatManager.createAnnouncementChat(eventID, new ArrayList<String>(Arrays.asList(attendeeID)));
        chatManager.addMessage(acID, messageID);
        return true;
        }
    //List<String> attList = Arrays.asList(eventManager.getSignUps(eventManager.getEventID(eventName)));
    //if (!(attList.contains(attID))){
    //throw new InvalidIDException();
    //    return false;}




}
