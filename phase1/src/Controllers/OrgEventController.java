package Controllers;

// Programmers: Cara McNeil, Sarah Kronenfeld, Eytan Weinstein
// Description: All the methods that take user input in the Organizer Event Menu
// Date Created: 01/11/2020
// Date Modified: 15/11/2020

import Events.EventManager;
import Events.EventType;
import Events.RoomManager;
import Message.AnnouncementChat;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Person.SpeakerManager;
import Presenter.OrgEventMenu;

import java.util.ArrayList;
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
        currentRequest = SubMenu.readInteger(input);
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
                    addSpeakerPrompt();
                    break;
                case 4:
                    eventMessagePrompt();
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
        if (speakerManager.findPerson(username)) {
            speakerManager.createAccount(name, username, password, email);
            return true;
        }
        return true;
    }

    /**
     * This is a helper function for the method createEvent below
     * @param chatId
     * @param personID
     * @return boolean; this is to update Speaker's chat list should OrgEvent Controller wish to do so in this class
     */

    private boolean updateSpeakerChat(String personID, String chatId) {
        this.speakerManager.addChat(personID, chatId);
        return true;
    }

    /**
     * This is helper function for the method createEvent below
     * @param acId
     * @param personId
     * @return boolean; this is to update Speaker's announcementChatIds (announcement chats created by Organizer pertaining to event)
     */

    private boolean updateSpeakerChatWithAnnouncement(String personId, String acId) {
        this.speakerManager.addAnnouncementChats(personId, acId);
        return true;
    }



    /**
     * Creates a new Event for the convention
     * creates a new chat for the event and sets the event chatid to the id of this chat.
     * @param eventName The name of the Event the user has requested to create
     * @return true iff the Event was created
     */
    public boolean createEvent(String eventName, String speakerUsername, String room, int startTime) {
        String roomID = roomManager.getRoomID(room); // roomId
        String speakerID = speakerManager.getCurrentUserID(speakerUsername); // add speaker ID
        roomManager.getRoom(roomID).addEvent(eventName, speakerID, startTime); // Adds an event to room manager
        String id = roomManager.getRoom(roomID).getEventID(eventName); // event id
        ArrayList<String> attendees = roomManager.getRoom(roomID).getSignUps(id); // attendees for event
        String acId = chatManager.createAnnouncementChat(id, attendees); // announcement chat for the event
        roomManager.getRoom(roomID).setEventChatID(id, acId); // setting the event chatId to the announcement chatId

        // also setting up setEventChatID for speaker(s) of event as well
        updateSpeakerChatWithAnnouncement(speakerID, acId);
        // also setting upEventChatId for speaker(s) of event
        updateSpeakerChat(speakerID, acId);
        // addEventIDtoList(String personID, String eventID)
        speakerManager.addTalkId(speakerID, id);
        // addEventIDtoMap(String personID, String eventID, String eventName)
        speakerManager.addTalkIdToDictionary(speakerID, id, eventName);
        return true;
    }




    /**
     * Creates a new Event for the convention
     * creates a new chat for the event and sets the event chatid to the id of this chat.
     * @param eventName The name of the Event the user has requested to create
     * @return true iff the Event was created
     */
    public boolean createTalk(String eventName, String speakerUsername, String room, int startTime) {
        String roomID = roomManager.getRoomID(room); // roomId
        String speakerID = speakerManager.getCurrentUserID(speakerUsername); // add speaker ID
        roomManager.getRoom(roomID).addEvent(eventName, speakerID, startTime); // Adds an event to room manager
        String id = roomManager.getRoom(roomID).getEventID(eventName); // event id
        ArrayList<String> attendees = roomManager.getRoom(roomID).getSignUps(id); // attendees for event
        String acId = chatManager.createAnnouncementChat(id, attendees); // announcement chat for the event
        roomManager.getRoom(roomID).setEventChatID(id, acId); // setting the event chatId to the announcement chatId

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
            //TODO: add workshop option
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
        String roomId = roomManager.getRoomID(roomName); // roomid
        EventManager emanager = roomManager.getRoom(roomId); // eventmanager for events with roomid
        String eventId =  emanager.getEventID(eventName); // eventId
        String ev = emanager.getAnnouncementChat(eventId); // chatid
        AnnouncementChat ch = (AnnouncementChat)chatManager.getChat(ev); // the annoucement chat for the event
        String pass = ch.getPassword(); // the password for the announcemenchat
        String m = messageManager.createMessage(eventId, messageContent); // creating message for event
        ch.addMessageIds(m, pass); // adding message to event chat
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
        String rname = chooseRoom();
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
