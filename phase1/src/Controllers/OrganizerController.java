package Controllers;// Programmer: Cara McNeil
// Description: Controllers.Main menu for Person.Person.Organizer users.
// Date Created: 01/11/2020
// Date Modified: 09/11/2020

import Events.EventReader;
import Person.OrganizerManager;
import Person.PersonManager;
import Person.SpeakerManager;
import Events.RoomManager;

import java.util.Scanner;

public class OrganizerController extends AttendeeController {
    private RoomManager eManager;
    // private Message.Message.ChatManager cManager = new Message.Message.ChatManager();
    // private Message.MessageManager mManager = new Message.MessageManager();
    Scanner input = new Scanner(System.in);
    int currentRequest;
    private String username;
    private String password;
    private String currentUserID;
    private OrganizerManager manager;
    private SpeakerManager sManager;


    public OrganizerController(PersonManager manager, SpeakerManager sManager) {
        super(manager);
        this.manager = (OrganizerManager) manager;
        this.sManager = sManager;
        //eManager = New RoomManager();
    }

    @Override
    void run() {

    }


    // NOTE: make all classes private except run?



    /**
     * Sends a Message to every user signed up for an event
     * @param eventName The name of the Event
     * @return true iff the Message was sent to every user on the event list
     */
    public boolean eventMessage(String eventName) {
        // messages the event, if it is sent, then it is added.
        return false;
    }

    /**
     * Sends a Message to one Attendee signed up for an event
     * @param eventName The name of the Event
     * @param attendeeUsername The username of the Attendee the user wishes to message
     * @return true iff the message was sent to the corresponding Attendee
     */
    public boolean eventMessage(String eventName, String attendeeUsername) {
        return false;
    }


}
