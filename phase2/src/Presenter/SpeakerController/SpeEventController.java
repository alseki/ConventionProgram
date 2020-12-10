package Presenter.SpeakerController;

// Programmer: Karyn Komatsu,
// Description: Event Menu for SpeakerController users.
// Date Created: 01/11/2020
// Date Modified: 19/11/2020

import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.NoDataException;
import Presenter.Central.SubMenu;
import Person.SpeakerManager;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

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
     * Sends a Message to every user signed up for an Event
     * @param eventName The name of the Event
     * @param messageContent Content of the Message to be sent
     */
    public String eventMessage(String eventName, String messageContent) {
        String eID = eventManager.getEventID(eventName);
        // FIXME
        /*if (eID != null) {
            String messageID = messageManager.createMessage(eID, presenter.addSpeUsername(messageContent));
            String acID = eventManager.getEventChat(eID);
            chatManager.addMessageIds(acID, messageID);
            return presenter.printMessageSent();
        }
        return presenter.printException(new InvalidChoiceException("event"));*/
        return null; // TODO delete this line after above is fixed
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
        ArrayList allEvents_list = speakerManager.getSpeakerInEvents(currentUserID);
        String[] allEvents = new String[allEvents_list.size()];
        for(int j =0;j<allEvents_list.size();j++){
            allEvents[j] = (String) allEvents_list.get(j);
        }
        return multipleEventsAnnouncement(allEvents, messageContent);
    }

    @Override
    public SpeEventMenu getPresenter() {
        return this.presenter;
    }
}
