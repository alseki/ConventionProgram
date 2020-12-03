package Presenter.SpeakerController;

// Programmer: Karyn Komatsu,
// Description: Event Menu for SpeakerController users.
// Date Created: 01/11/2020
// Date Modified: 19/11/2020

import Presenter.Exceptions.NoDataException;
import Presenter.Central.SubMenu;
import Person.SpeakerManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SpeEventController extends SubMenu {

    private String currentUserID;
    private SpeEventMenu presenter;
    private SpeakerManager speakerManager;
    Scanner input = new Scanner(System.in);

    public SpeEventController(SubMenu subMenu, SpeakerManager speakerManager, String currentUserID) {
        super(subMenu);
        this.currentUserID = currentUserID;
        this.speakerManager = speakerManager;
        presenter = new SpeEventMenu(roomManager, eventManager, speakerManager);
    }
    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     */
    public void menuChoice() {
        do {
            switch (1){
                case 0:
                    // return to main menu
                    break;
                case 1:
                    getOwnTalks();
                    break;
                case 2:
                    // Send message to all Attendees in an Event
                    presenter.printEventNamePrompt();
                    String eventNameB = "";//SubMenu.readInput(input);
                    presenter.printContentPrompt();
                    String contentB = this.addSpeUsername("");//SubMenu.readInput(input));
                    this.eventMessage(eventNameB, contentB);
                    presenter.printMessageSent();
                    break;
                case 3:
                    // Send message to all Attendees in all of your Events
                    presenter.printContentPrompt();
                    String contentC = this.addSpeUsername("");//SubMenu.readInput(input));
                    this.allSpeakerEventsMessage(contentC);
                    presenter.printMessageSent();
                    break;
                case 4:
                    // Send message to all Attendees in some of your Events
                    presenter.printContentPrompt();
                    String contentD = this.addSpeUsername("");//SubMenu.readInput(input));
                    presenter.printEventNamesPrompt();
                    String eventNames ="";// SubMenu.readInput(input);
                    String[] someSpeakerEvents = eventNames.split(",");
                    this.multipleEventsAnnouncement(someSpeakerEvents, contentD);
                    presenter.printMessageSent();
                    break;
            }
        }
        while (true);
    }


    /**
     * Get the list of talk the user is scheduled to speak at
     * @return String chunk of formatted Talks
     */
    private void getOwnTalks() {
        try {
            String[] events = {};
            events = speakerManager.getEventList(currentUserID).toArray(events);
            presenter.printEventList(" you speak at", events);
        } catch (NullPointerException e) {
            presenter.printException(new NoDataException("event"));
        }
    }

    /**
     * Sends a Message to every user signed up for an event
     * @param eventName The name of the Event
     * @param messageContent Content of the Message to be sent
     */
    private void eventMessage(String eventName, String messageContent) {
        String eID = eventManager.getEventID(eventName);
        String messageID = messageManager.createMessage(eID, messageContent);
        String acID = eventManager.getEventChat(eID);
        chatManager.addMessageIds(acID, messageID);
    }

    /**
     * This is to send a message to all attendees of all of SpeakerController's events. For example, if SpeakerController wanted to
     * announce, "download so and so application for the our talk today" to all talks, this method proves useful. This
     * uses eventMessageForAttendees from above
     * @param events ArrayList of Event Names hosted by the speaker
     * @param messageContent String representing content of the message
     */

    private void  multipleEventsAnnouncement (String[] events, String messageContent) {

        try {
            for (String event : events) {
                eventMessage(event, messageContent);
            }
        } catch (InputMismatchException ime){
                System.out.println("You have entered an event name incorrectly.");
        }
    }


    /**
     * This is the same method from above, but only using messageContent as a parameter instead of the list of all
     * SpeakerController talks
     * @param messageContent
     */
    private void allSpeakerEventsMessage (String messageContent){
        String[] allTalks = {};
        allTalks = speakerManager.getSpeakerIdAllTalks(currentUserID).toArray(allTalks);
        multipleEventsAnnouncement(allTalks, messageContent);
    }

    /**
     *
     * @param content Content of the message
     * @return Content following with the sentence: ["Contact me using this username:"]\newline
     *                                              [username of the SpeakerController]
     */
    private String addSpeUsername (String content){
        return content + "\n" + "Contact me using this username:\n"
                + speakerManager.getCurrentUsername(currentUserID);
    }
}
