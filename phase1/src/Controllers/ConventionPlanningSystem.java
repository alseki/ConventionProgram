package Controllers;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: The central Controller of the Convention System. Calls all other Controllers.
// Date Created: 01/11/2020
// Date Modified: 16/11/2020

import Events.RoomManager;
import Message.MessageManager;
import Person.*;
import Presenter.CPSMenu;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ConventionPlanningSystem {

    private int accountChoice;
    private Scanner input = new Scanner(System.in);
    private CPSMenu presenter = new CPSMenu();
    private MessageManager mm = new MessageManager();
    private RoomManager rm = new RoomManager(LocalDateTime.now()); //TODO: ADD DATE INPUT
    private static final FileGateway<RoomManager> roomLoader = new FileGateway<RoomManager>("events.ser");
    private static final FileGateway<MessageManager> messageLoader = new FileGateway<MessageManager>("messages.ser");

    /**
    * Calls appropriate Controllers based on user input.
    */
    public void run() {
        do {
            presenter.printIntroMessage();
            accountChoice = input.nextInt();
            setController(accountChoice);
        }
        while (accountChoice != 0);
    }


    /**
     * Set the user's controller based on login selection.
     */
    private void setController(int choice) {

        load();

        switch (choice) {
            case 0:
                save(); // SAVE FILES
                break;
            case 1:
                AttendeeManager am = new AttendeeManager();
                AttendeeController AC =  new AttendeeController(am, rm, mm);
                AC.run();
                break;
            case 2:
                OrganizerManager om = new OrganizerManager();
                SpeakerManager sm = new SpeakerManager();
                OrganizerController OC = new OrganizerController(om, sm, rm, mm);
                OC.run();
                break;
            case 3:
                SpeakerManager sman = new SpeakerManager();
                SpeakerController SC = new SpeakerController(sman, rm, mm);
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
        if (messageLoader.readFile() != null) {
            mm = messageLoader.readFile();
        }
    }

    /**
     * Saves data to a set filepath
     */
    private void save() {
        roomLoader.writeFile(rm);
        messageLoader.writeFile(mm);
    }

}
