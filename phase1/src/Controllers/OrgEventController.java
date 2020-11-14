package Controllers;

// Programmers: Cara McNeil, Sarah Kronenfeld
// Description: All the methods that take user input in the Organizer Event Menu
// Date Created: 01/11/2020
// Date Modified: 14/11/2020

import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Person.SpeakerManager;
import Presenter.OrgEventMenu;

import java.util.Scanner;

public class OrgEventController implements SubMenu {

    private String currentUserID;
    private int currentRequest;
    private PersonManager personManager;
    private SpeakerManager speakerManager;
    private RoomManager roomManager;
    private MessageManager messageManager;
    private ChatManager chatManager;
    private OrgEventMenu presenter = new OrgEventMenu();
    Scanner input = new Scanner(System.in);

    public OrgEventController(String currentUserID, PersonManager personManager, SpeakerManager speakerManager,
                              RoomManager roomManager, MessageManager messageManager, ChatManager chatManager) {
        this.currentUserID = currentUserID;
        this.personManager = personManager;
        this.speakerManager = speakerManager;
        this.roomManager = roomManager;
        this.messageManager = messageManager;
        this.chatManager = chatManager;
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
     * Adds a room to the list of rooms in this Convention.
     * @param room The name/number of a room in the convention
     * @return true iff list was added to system.
     */
    public boolean addRoom(String room) {
        if (roomManager.addRoom(room) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Creates a new Person.Speaker account and adds it to the system.
     * @param name The name of the Speaker
     * @param username The username of the Speaker
     * @param password The password of the Speaker
     * @param email The email of the Speaker
     * @return true iff a new Person.Speaker object was created.
     */
    public boolean createSpeaker(String name, String username, String password, String email) {
        //if (sManager.findPerson(username)) {
            //sManager.createAccount(name, username, password, email);
            //return true;
        //}
        return false;
    }


    /**
     * Adds Speaker to an Event being held in a single room. At most one speaker speaks in each room at a given time
     * @param speakerUsername The username of the Speaker the current user wishes to schedule
     * @param room The name/number of a room in the convention
     * @return true iff the Speaker was added to the Event
     */
    private boolean scheduleSpeaker(String speakerUsername, String room) {

        return false;
    }


    /**
     * Creates a new Event for the convention
     * @param eventName The name of the Event the user has requested to create
     * @return true iff the Event was created
     */
    public boolean createEvent(String eventName, String speakerUsername, String room, int startTime) {
        String roomID = roomManager.getRoomId(room);
        String speakerID = ""; // add speaker ID
        roomManager.getRoom(roomID).addEvent(eventName, speakerID, startTime);
        return false;
    }


    /**
     * Creates a new Event for the convention
     * @param eventName The name of the Event the user has requested to create
     * @return true iff the Event was created
     */
    public boolean createEvent(String eventName, String speakerUsername, String room, int startTime,
                               String description) {
        String roomID = roomManager.getRoomId(room);
        String speakerID = ""; // add speaker ID
        roomManager.getRoom(roomID).addEvent(eventName, speakerID, startTime, description);
        return false;
    }

    /**
     * Prompts user to input the room they wish to add
     * @return true iff room adding prompt was printed
     */
    public boolean addRoomPrompt() {
        return true;
    }

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
