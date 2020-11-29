package Presenter.Central;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: The central Controller of the Convention System. Calls all other Presenter.
// Date Created: 01/11/2020
// Date Modified: 18/11/2020

import Presenter.AttendeeController.AttendeeController;
import Presenter.OrganizerController.OrganizerController;
import Presenter.SpeakerController.SpeakerController;
import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConventionPlanningSystem {

    private int accountChoice;
    private Scanner input = new Scanner(System.in);
    private CPSMenu presenter = new CPSMenu();

    private MessageManager mm = new MessageManager();
    private ChatManager cm = new ChatManager();
    private static final FileGateway<MessageManager> messageLoader = new FileGateway<MessageManager>("messages.ser");
    private static final FileGateway<ChatManager> chatLoader = new FileGateway<ChatManager>("chats.ser");

    private RoomManager rm = new RoomManager();
    private static final FileGateway<RoomManager> roomLoader = new FileGateway<RoomManager>("rooms.ser");
    private EventManager em = new EventManager();
    private static final FileGateway<EventManager> eventLoader = new FileGateway<>("events.ser");

    private Map<String, Person> personByName = new HashMap<>();
    private Map<String, Person> personByID = new HashMap<>();
    private static final FileGateway<Map<String, Person>> id2Person =
            new FileGateway<Map<String, Person>>("pByID.ser");
    private static final FileGateway<Map<String, Person>> n2Person =
            new FileGateway<Map<String, Person>>("pByName.ser");


    /**
    * Calls appropriate Presenter based on user input.
    */
    public void run() {
        load();

        do {
            // TODO get rid of CPSMenu class
            // TODO call two methods in this.vp to present JOptionPane box greetings
            accountChoice = Integer.parseInt(presenter.printIntroMessage());
            setController(accountChoice);
        }
        while (accountChoice != 0);
    }


    /**
     * Set the user's controller based on login selection.
     */
    private void setController(int choice) {

        switch (choice) {
            case 0:
                save(); // SAVE FILES
                break;
            case 1:
                AttendeeManager am = new AttendeeManager(personByName, personByID);
                AttendeeController AC =  new AttendeeController(am, rm, em, mm, cm);
                AC.run();
                break;
            case 2:
                OrganizerManager om = new OrganizerManager(personByName, personByID);
                SpeakerManager sm = new SpeakerManager(personByName, personByID);
                AttendeeManager attMan = new AttendeeManager(personByName, personByID);
                OrganizerController OC = new OrganizerController(om, sm, rm, em, mm, cm, attMan);
                OC.run();
                break;
            case 3:
                SpeakerManager sman = new SpeakerManager(personByName, personByID);
                SpeakerController SC = new SpeakerController(sman, rm, em, mm, cm);
                SC.run();
                break;
        }
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
    }

    /**
     * Saves data to a set filepath
     */
    private void save() {
        roomLoader.writeFile(rm);
        eventLoader.writeFile(em);
        messageLoader.writeFile(mm);
        chatLoader.writeFile(cm);
        id2Person.writeFile(personByID);
        n2Person.writeFile(personByName);
    }

}
