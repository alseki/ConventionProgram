package Presenter.SpeakerController;

// Programmer: Karyn Komatsu,
// Description: Event Menu for SpeakerController users.
// Date Created: 01/11/2020
// Date Modified: 19/11/2020

import Presenter.Exceptions.InvalidChoiceException;
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
        presenter = new SpeEventMenu(roomManager, eventManager, speakerManager, currentUserID);
    }

    /**
     * Sends a Message to every user signed up for an event
     * @param eventName The name of the Event
     * @param messageContent Content of the Message to be sent
     */
    public String eventMessage(String eventName, String messageContent) {
        String eID = eventManager.getEventID(eventName);
        if (eID != null) {
            String messageID = messageManager.createMessage(eID, presenter.addSpeUsername(messageContent));
            String acID = eventManager.getEventChat(eID);
            chatManager.addMessageIds(acID, messageID);
            return presenter.printMessageSent();
        }
        return presenter.printException(new InvalidChoiceException("event"));
    }

    /**
     * This is to send a message to all attendees of all of SpeakerController's events. For example, if SpeakerController wanted to
     * announce, "download so and so application for the our talk today" to all talks, this method proves useful. This
     * uses eventMessageForAttendees from above
     * @param events ArrayList of Event Names hosted by the speaker
     * @param messageContent String representing content of the message
     */

    public String multipleEventsAnnouncement (String[] events, String messageContent) {
        try {
            String sent = presenter.printException(new NoDataException("event"));
            for (String event : events) {
                sent =  eventMessage(event, messageContent);
                if (!sent.equals(presenter.printMessageSent())) {
                    return sent;
                }
            }
            return sent;
        } catch (InputMismatchException ime) {
            return presenter.printException(new InvalidChoiceException("event"));
        }
    }


    /**
     * This is the same method from above, but only using messageContent as a parameter instead of the list of all
     * SpeakerController talks
     * @param messageContent
     */
    public String allSpeakerEventsMessage (String messageContent){
        String[] allTalks = {};
        allTalks = speakerManager.getSpeakerIdAllTalks(currentUserID).toArray(allTalks);
        return multipleEventsAnnouncement(allTalks, messageContent);
    }
}
