package Controllers;

// Programmers: Cara McNeil, Sarah Kronenfeld, Eytan Weinstein
// Description: All the methods that take user input in the Organizer Event Menu
// Date Created: 01/11/2020
// Date Modified: 15/11/2020

import Events.EventType;
import Events.RoomManager;
import Message.*;
import Person.PersonManager;
import Person.SpeakerManager;
import Presenter.OrgEventMenu;

import java.util.ArrayList;
import java.util.Scanner;
import Events.EventManager;
import Events.Event;

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
            switch (currentRequest) {
                case 0:
                    break;
                case 1:
                    addRoomPrompt();
                    break;
                case 2:
                    createEventPrompt();
                    break;
                case 3:
                    eventMessagePrompt();
                    break;
                case 4:
                    addSpeakerPrompt();
                    break;
            }
        }
        while (currentRequest != 0);
        return true;
        // TODO add switch statement to call the methods that correspond with currentRequest
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
        return true;
    }


    /**
     * Adds Speaker to an Event being held in a single room. At most one speaker speaks in each room at a given time
     * @param speakerUsername The username of the Speaker the current user wishes to schedule
     * @param room The name/number of a room in the convention
     * @return true iff the Speaker was added to the Event
     */
    private boolean scheduleSpeaker(String speakerUsername, String room) {

        return true;
    }


    /**
     * Creates a new Event for the convention
     * creates a new chat for the event and sets the event chatid to the id of this chat.
     * @param eventName The name of the Event the user has requested to create
     * @return true iff the Event was created
     */
    public boolean createTalk(String eventName, String speakerUsername, String room, int startTime) {
        String roomID = roomManager.getRoomID(room);
        String speakerID = speakerManager.getCurrentUserID(speakerUsername); // add speaker ID
        roomManager.getRoom(roomID).addEvent(eventName, speakerID, startTime);
        String id = roomManager.getRoom(roomID).getEventID(eventName);
        ArrayList<String> attendees = roomManager.getRoom(roomID).getSignUps(id);
        String acId = chatManager.createAnnouncementChat(id, attendees);
        roomManager.getRoom(roomID).setEventChatID(id, acId);

        return true;
    }

    /**
     * allows for input to create an event
     * @return true
     */
    public boolean createEventPrompt(){
        presenter.printCreateEventPrompt();
        presenter.printEventTypePrompt();
        EventType type = chooseEventType();
        presenter.printEventNamePrompt();
        String name = chooseRoom();
        presenter.printRoomNamePrompt();
        String roomName = input.nextLine();
        presenter.printSpeakerUsernamePrompt();
        String speakername = input.nextLine();
        if (type == EventType.TALK) {
            presenter.printStartTimePrompt();
            int start = chooseStartTime();
            createTalk(name, speakername, roomName, start);
        } else {
            // figure this out!
        }
        return true;

    }

    /**
     * Restricts users to choosing an event type from a list of event types
     * @return The type of event they have chosen
     */
    private EventType chooseEventType() {
        String type = input.nextLine();
        if (type.equals("0")) {
            presenter.printEventTypes();
            type = input.nextLine();
        }
        try {
            return EventType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException f) {
            presenter.printEventTypePrompt();
            return chooseEventType();
        }
    }

    /**
     * Restricts users to choosing a room from a list of rooms
     * @return The room they have chosen
     */
    private String chooseRoom() {
        String name = input.nextLine();
        if (name.equals("0")) {
            presenter.printRoomNames(roomManager.getRoomNames());
            name = input.nextLine();
        }
        if (roomManager.getRoomID(name) != null) {
            return name;
        } else {
            presenter.printRoomNamePrompt();
            return chooseRoom();
        }
    }

    /**
     * Restricts users to choosing a valid start time
     * @return The start time
     */
    private int chooseStartTime() {
        String time = input.nextLine();
        try {
            int value = Integer.valueOf(time);
            if (value >= 0 && value <= 24) {
                return value;
            } else {
                return chooseStartTime();
            }
        } catch (NumberFormatException f) {
            presenter.printStartTimePrompt();
            return chooseStartTime();
        }
    }

    /*
     * Creates a new Event for the convention
     * @param eventName The name of the Event the user has requested to create
     * @return true iff the Event was created

    public boolean createEvent(String eventName, String speakerUsername, String room, int startTime,
                               String description) {
        String roomID = roomManager.getRoomId(room);
        String speakerID = speakerManager.getCurrentUserID(speakerUsername); // add speaker ID
        roomManager.getRoom(roomID).addEvent(eventName, speakerID, startTime, description);
        return true;
    }
     */

    /**
     * Prompts user to input the room they wish to add
     * @return true iff room adding prompt was printed
     */
    public boolean addRoomPrompt() {
        presenter.addRoomPrompt();
        presenter.addRoomNamePrompt();
        String name = input.nextLine();
        addRoom(name);
        return true;
    }

    /**
     * Adds a room to the list of rooms in this Convention.
     * @param room The name/number of a room in the convention
     * @return true iff list was added to system.
     */
    public boolean addRoom(String room) {
        return roomManager.addRoom(room) != null;
    }

    /**
     * allows for input to create a speaker
     * @return true
     */
    public boolean addSpeakerPrompt(){
        presenter.printAddSpeakPrompt();
        presenter.printAddNamePrompt();
        String name = input.nextLine();
        presenter.printAddEmailPrompt();
        String email = input.nextLine();
        presenter.printAddUsernamePrompt();
        String username = input.nextLine();
        presenter.printAddPasswordPrompt();
        String pass = input.nextLine();
        createSpeaker(name, username, pass, email);
        return true;
    }

    /**
     * adds a message with content message cotnent.  to the announcementchat contained within the event with eventname.
     * @param eventName The name of the Event
     * @return true iff the Message was added to the event's chatlist
     */
    public boolean eventMessage(String eventName, String roomName, String messageContent){
        String roomId = roomManager.getRoomID(roomName);
        EventManager emanager = roomManager.getRoom(roomId);
        String eventId =  emanager.getEventID(eventName);
        String ev = emanager.getAnnouncementChat(eventId); // chatid
        AnnouncementChat ch = (AnnouncementChat)chatManager.getChat(ev);
        String pass = ch.getPassword(); // the password for the announcemenchat
        String m = messageManager.createMessage(eventId, messageContent);
        ch.addMessageIds(m, pass);
        return true;
    }

    /**
     * allows for input to create an event message
     * @return true
     */
    public boolean eventMessagePrompt(){
        presenter.printEventMessageIntro();
        presenter.printEventNamePrompt();
        String name = input.nextLine();
        presenter.printRoomNamePrompt();
        String rname = input.nextLine();
        presenter.printMessageContentPrompt();
        String content = input.nextLine();
        eventMessage(name, rname, content);
        return true;
    }


    /**
     * Sends a Message to one Attendee signed up for an event
     * @param eventName The name of the Event
     * @param attendeeUsername The username of the Attendee the user wishes to message
     * @return true iff the message was sent to the corresponding Attendee
     */
    public boolean eventMessage(String eventName, String attendeeUsername) {
        return true;
    }

}
