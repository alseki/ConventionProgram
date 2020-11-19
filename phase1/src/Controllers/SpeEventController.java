package Controllers;

import Events.EventManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
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
     */
    @Override
    public void menuOptions() {
        presenter.printMenuOptions();
        currentRequest = SubMenu.readInteger(input);
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     */
    @Override
    public void menuChoice() {
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
                    //String eventNameA = SubMenu.readInput(input);
                    //presenter.printContentPrompt();
                    //String contentA = SubMenu.readInput(input);
                    //this.eventMessage(eventNameA, contentA);
                    //presenter.printMessageSent();
                    //break;
                case 2:
                    // Send message to all Attendees in an Event
                    presenter.printEventNamePrompt();
                    String eventNameB = this.addSpeUsername(SubMenu.readInput(input));
                    presenter.printContentPrompt();
                    String contentB = SubMenu.readInput(input);
                    this.eventMessageForAttendees(eventNameB, contentB);
                    presenter.printMessageSent();
                    break;
                case 3:
                    // Send message to all Attendees in all of your Events
                    presenter.printContentPrompt();
                    String contentC = this.addSpeUsername(SubMenu.readInput(input));
                    this.allSpeakerEventsMessagingById(contentC);
                    presenter.printMessageSent();
                    break;
                case 4:
                    // Send message to all Attendees in some of your Events
                    presenter.printContentPrompt();
                    String contentD = this.addSpeUsername(SubMenu.readInput(input));
                    presenter.printEventNamesPrompt();
                    String eventNames = SubMenu.readInput(input);
                    String[] someSpeakerEvents = eventNames.split(",");
                    this.allSpeakerEventsMessage(someSpeakerEvents, contentD);
                    presenter.printMessageSent();
                    break;
                case 5:
                    // Send message to a user that Speaker knows the username of
                    presenter.printContentPrompt();
                    String contentE = this.addSpeUsername(SubMenu.readInput(input));
                    presenter.printEnterUsername();
                    String username = SubMenu.readInput(input);
                    this.sendIndividualMessage(username, contentE);
                    presenter.printMessageSent();;
                    // But we didn't plan to allow a Speaker to send personal message to a User.
                    // Speakers can only send announcements.
                    break;

            }
        }
        while (currentRequest != 0);
    }


    /**
     * Get the list of talk the user is scheduled to speak at
     * @return String chunk of formatted Talks
     */
    private String getFormattedTalks() {
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
     */
    private void eventMessage(String eventName, String messageContent) {
        String eventID = eventManager.getEventID(eventName);
        ArrayList<String> attIDs = eventManager.getSignUps(eventID);
        String messageID = messageManager.createMessage(eventID, messageContent);
        String acID = chatManager.createAnnouncementChat(eventID, new ArrayList<String>(attIDs));
        chatManager.addMessageIds(acID, messageID);
    }


    /**
     * This is to sent a message to all attendees of an event. It uses method eventMessage from below
     * @param eventName
     * @param messageContent
     */
    private void eventMessageForAttendees(String eventName, String messageContent) {
        String eventID = eventManager.getEventID(eventName);
        ArrayList<String> attendeeList = eventManager.getSignUps(eventID);
        for (String attendee : attendeeList) {
            eventMessage(eventName, messageContent, attendee);
        }
    }

    /**
     * This is to send a message to all attendees of all of Speaker's events. For example, if Speaker wanted to
     * announce, "download so and so application for the our talk today" to all talks, this method proves useful. This
     * uses eventMessageForAttendees from above
     * @param allSpeakerEvents ArrayList of Event Names hosted by the speaker
     * @param messageContent String representing content of the message
     */

    private void allSpeakerEventsMessage(ArrayList<String> allSpeakerEvents, String messageContent) {
            for (String eventName : allSpeakerEvents) {
                eventMessageForAttendees(eventName, messageContent);
            }
    }

    /**
     * This is to send a message to all attendees of all or some of Speaker's events. For example, if Speaker wanted to
     * announce, "download so and so application for the our talk today" to all talks, this method proves useful. This
     * uses eventMessageForAttendees from above
     * @param allSpeakerEvents String Array of Names of Events hosted by the speaker
     * @param messageContent String representing content of the message
     */

    private void allSpeakerEventsMessage(String[] allSpeakerEvents, String messageContent) {
        for (String eventName : allSpeakerEvents) {
            eventMessageForAttendees(eventName, messageContent);
        }
    }

    /**
     * This is the same method from above, but only using messageContent as a parameter instead of the list of all
     * Speaker talks
     * @param messageContent
     */
    private void allSpeakerEventsMessagingById(String messageContent) {
        ArrayList <String> allSpeakerEvents = personManager.getSpeakerIdAllTalks(currentUserID);
        allSpeakerEventsMessage(allSpeakerEvents, messageContent);
    }

    /**
     * This is the same method from above (allSpeakerEventsMessage), but using speaker's currentUserId as a parameter
     * instead of the list of all Speaker talks
     * @param currentUserId
     * @param messageContent
     */
    private void allSpeakerEventsMessagingByUsername(String currentUserId, String messageContent) {
        ArrayList <String> allSpeakerEvents = personManager.getSpeakerAllTalks(currentUserId);
        allSpeakerEventsMessage(allSpeakerEvents, messageContent);

    }

    /**
     * Sends a Message to one Attendee signed up for an event
     * @param eventName The name of the Event
     * @param attendeeUsername Th username of the Attendee the user wishes to message
     */
    private void eventMessage(String eventName, String messageContent, String attendeeUsername) {
        String eventID = eventManager.getEventID(eventName);
        String attID = personManager.getCurrentUserID(attendeeUsername);
        String[] attendeeID = new String[]{attID};
        String messageID = messageManager.createMessage(eventID, messageContent);
        String acID = chatManager.createAnnouncementChat(eventID, new ArrayList<String>(Arrays.asList(attendeeID)));
        chatManager.addMessageIds(acID, messageID);
    }
    //List<String> attList = Arrays.asList(eventManager.getSignUps(eventManager.getEventID(eventName)));
    //if (!(attList.contains(attID))){
    //throw new InvalidIDException();
    //    return false;}

    /**
     *
     * @param content Content of the message
     * @return Content following with the sentence: ["Contact me using this username:"]\newline
     *                                              [username of the Speaker]
     */
    private String addSpeUsername(String content){return content + "\n" + "Contact me using this username:\n"
            + personManager.getCurrentUsername(currentUserID);}

    private void sendIndividualMessage(String username, String messageContent){
        String recipientID = personManager.getCurrentUserID(username);
        String messageID = messageManager.createMessage(currentUserID, recipientID ,messageContent);
        String chatID = chatManager.createChat(currentUserID, recipientID);
        chatManager.addMessageIds(chatID, messageID);
        personManager.addChat(currentUserID, chatID);
        personManager.addChat(recipientID, chatID);
    }
}
