package Presenter.Central;
// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: The central Controller of the Convention System. Calls all other Controllers.
// Date Created: 01/11/2020
// Date Modified: 18/11/2020

import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.*;
import Presenter.AttendeeController.AttendeeController;
import Presenter.OrganizerController.OrganizerController;
import Presenter.PersonController.PersonController;
import Presenter.SpeakerController.SpeakerController;
import Request.RequestManager;


import java.util.HashMap;
import java.util.Map;

public class ConventionPlanningSystem {

    private int accountChoice;
    private PersonController PC;
    String introTitle = "CONVENTION SYSTEM LOGIN";
    String introMessage = "Welcome to convention system!";
    String chooseAccountTitle = "User Account Selection";
    String chooseAccountMessage = "To Login or create an account as an Attendee, Enter '1';" + "\nTo Login or create an " +
            "account as an Organizer, Enter '2';" + "\nTo Login or create an account as a Speaker, Enter '3';" +
            "\nTo Quit the program and save any changes made, Enter '0'.";

    private MessageManager mm = new MessageManager();
    private ChatManager cm = new ChatManager();
    private static final FileGateway<MessageManager> messageLoader = new FileGateway<MessageManager>("messages.ser");
    private static final FileGateway<ChatManager> chatLoader = new FileGateway<ChatManager>("chats.ser");

    private RoomManager rm = new RoomManager();
    private static final FileGateway<RoomManager> roomLoader = new FileGateway<RoomManager>("rooms.ser");
    private EventManager em = new EventManager();
    private static final FileGateway<EventManager> eventLoader = new FileGateway<>("events.ser");

    private RequestManager rqm = new RequestManager();
    private static final FileGateway<RequestManager> requestLoader = new FileGateway<>("requests.ser");

    private Map<String, Person> personByName = new HashMap<>();
    private Map<String, Person> personByID = new HashMap<>();
    private static final FileGateway<Map<String, Person>> id2Person =
            new FileGateway<Map<String, Person>>("pByID.ser");
    private static final FileGateway<Map<String, Person>> n2Person =
            new FileGateway<Map<String, Person>>("pByName.ser");

    // TODO add requestManagers into controller instantiations

    public ConventionPlanningSystem() {
        load();
    }

    public String getIntroTitle() {
        return introTitle;
    }

    public String getIntroMessage() {
        return introMessage;
    }

    public String getChooseAccountTitle() {
        return chooseAccountTitle;
    }

    public String getChooseAccountMessage() {
        return chooseAccountMessage;
    }

    /**
     * Set the user's controller based on login selection.
     */
    private PersonController getController(int choice) {

        switch (choice) {
            // option 0 moved to view
            case 1:
                AttendeeManager am = new AttendeeManager(personByName, personByID);
                PC =  (AttendeeController) new AttendeeController(am, rm, em, mm, cm, rqm);
                break;
            case 2:
                OrganizerManager om = new OrganizerManager(personByName, personByID);
                SpeakerManager sm = new SpeakerManager(personByName, personByID);
                AttendeeManager attMan = new AttendeeManager(personByName, personByID);
                PC = (OrganizerController) new OrganizerController(om, sm, rm, em, mm, cm, attMan, rqm);
                break;
            case 3:
                SpeakerManager sman = new SpeakerManager(personByName, personByID);
                PC = (SpeakerController) new SpeakerController(sman, rm, em, mm, cm, rqm);
                break;
        }
        return PC;
    }

    /**
     * Loads saved data from a set filepath
     */
    private void load() {
        if (roomLoader.readFile()!= null) {
            rm = roomLoader.readFile();
        }
        if (eventLoader.readFile()!= null) {
            em = eventLoader.readFile();
        }
        if (messageLoader.readFile() != null) {
            mm = messageLoader.readFile();
        }
        if (chatLoader.readFile() != null) {
            cm = chatLoader.readFile();
        }
        if (id2Person.readFile() != null) {
            personByID = id2Person.readFile();
        }
        if (n2Person.readFile() != null) {
            personByName = n2Person.readFile();
        }
        if (requestLoader.readFile() != null) {
            rqm = requestLoader.readFile();
        }
    }

    /**
     * Saves data to a set filepath
     */
    public void save() {
        roomLoader.writeFile(rm);
        eventLoader.writeFile(em);
        messageLoader.writeFile(mm);
        chatLoader.writeFile(cm);
        id2Person.writeFile(personByID);
        n2Person.writeFile(personByName);
        requestLoader.writeFile(rqm);
    }

}
