package Controllers;

// Programmers: Cara McNeil, Sarah Kronenfeld, Eytan Weinstein
// Description: All the methods that take user input in the Organizer Event Menu
// Date Created: 01/11/2020
// Date Modified: 18/11/2020

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        currentRequest = SubMenu.readInteger(input);
        return true;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff uthe ser requests to return to Main Menu
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
                    try {
                        createEventPrompt();
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                    }
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

    // OPTION 1

    /**
     * Prompts the user to input the information for the Room they wish to add
     * @return true iff addRoom prompt was printed
     */
    public boolean addRoomPrompt() {
        presenter.addRoomPrompt();
        presenter.roomNamePrompt();
        String name = input.nextLine();
        presenter.roomCapacityPrompt();
        int capacity = Integer.parseInt(input.nextLine());
        this.addRoom(name, capacity);
        return true;
    }

    /**
     * Adds a room to the list of rooms in this convention
     * @param name The name of the new Room in the convention (likely its number)
     * @param capacity The capacity of the new Room in the convention
     * @return true iff Room was added to the convention successfully
     */
    public boolean addRoom(String name, int capacity) {
        return this.roomManager.addRoom(name, capacity) != null;
    }

    // OPTION 2

    /**
     * Prompts the user to input the information for the Event they wish to add
     * @return true iff createEvent prompt was printed
     */
    public boolean createEventPrompt() throws InvalidChoiceException {
        presenter.printCreateEventPrompt();
        presenter.printEventTypePrompt();
        EventType type = chooseEventType();
        presenter.printRoomNamePrompt();
        String roomName = chooseRoom();
        presenter.printEventNamePrompt();
        String eventName = input.nextLine();
        presenter.printDescriptionPrompt();
        String eventDescription = input.nextLine();
        presenter.printStartTimePrompt();
        LocalDateTime start = chooseStartTime();
        if (type == EventType.TALK) {
            presenter.printSpeakerUsernamePrompt();
            String speakerUsername = input.nextLine();
            boolean created = this.createTalk(eventName, speakerUsername, start, eventDescription, roomName);
            if (created == false) {
                presenter.printCapacityError();
                return false;
            }
        }
        else if (type == EventType.WORKSHOP) {
            presenter.printSpeakerUsernamePrompt();
            String speakerUsername = input.nextLine();
            return this.createWorkshop(eventName, speakerUsername, start, eventDescription, roomName);
        }
        return true;
    }

    /**
     * Prompts the user to choose a type for the Event they wish to add
     * @return the type of event they have chosen (as an EventType object)
     */
    private EventType chooseEventType() throws InvalidChoiceException {
        String type = input.nextLine();
        if (type.equals("0")) {
            presenter.printEventTypes();
            type = input.nextLine();
        }
        try {
            return EventType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException f) {
            throw new InvalidChoiceException("event type");
        }
    }

    /**
     * Prompts the user to choose a Room from the list of existing Rooms
     * @return The name of the Room they have chosen
     */
    private String chooseRoom() throws InvalidChoiceException {
        String name = input.nextLine();
        if (name.equals("0")) {
            presenter.printRoomNames(roomManager.getRoomNames());
            name = input.nextLine();
        }
        if (roomManager.getRoomID(name) != null) {
            return name;
        }
        else {
            throw new InvalidChoiceException("room");
        }
    }

    /**
     * Prompts the user to choose a valid start time for the new Event
     * @return The start time as a LocalDateTime object
     */
    private LocalDateTime chooseStartTime() {
        String time = input.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            return dateTime;
        }
        catch (DateTimeParseException exc) {
            presenter.printDateError();
            presenter.printStartTimePrompt();
            return chooseStartTime();
        }
    }

    /**
     * Creates a new Talk in this convention; also creates a new chat for this Talk and sets the Talk's chatID to the
     * ID of this new chat.
     * @param name The name of the Talk
     * @param speaker The username of the Speaker at the Talk
     * @param start The start time of the Talk
     * @param description A description for the Talk
     * @param room The name of the Room the Talk is in
     * @return true iff the Talk was created successfully
     */
    public boolean createTalk(String name, String speaker, LocalDateTime start, String description, String room)
            throws InvalidChoiceException {
        String roomID = roomManager.getRoomID(room);
        if (roomID == null) {
            throw new InvalidChoiceException("room");
        }

        String speakerID = speakerManager.getCurrentUserID(speaker);
        if (speakerID == null) {
            throw new InvalidChoiceException("speaker");
        }

        boolean created = roomManager.getRoom(roomID).addTalk(name, speakerID, start, description);
        String eventID = roomManager.getRoom(roomID).getEventID(name);

        addEvent(eventID, roomID, speakerID);

        return created;
    }

    /**
     * Creates a new Workshop in this convention; also creates a new chat for this Workshop and sets the Workshop's
     * chatID to the ID of this new chat.
     * @param name The name of the Workshop
     * @param speaker The username of the Speaker at the Workshop
     * @param start The start time of the Workshop
     * @param description A description for the Workshop
     * @param room The name of the Room the Workshop is in
     * @return true iff the Workshop was created successfully
     */
    public boolean createWorkshop(String name, String speaker, LocalDateTime start, String description, String room)
            throws InvalidChoiceException{
        String roomID = roomManager.getRoomID(room);
        if (roomID == null) {
            throw new InvalidChoiceException("room");
        }

        String speakerID = speakerManager.getCurrentUserID(speaker);
        if (speakerID == null) {
            throw new InvalidChoiceException("speaker");
        }


        boolean created = roomManager.getRoom(roomID).addWorkshop(name, speakerID, start, description);
        String eventID = roomManager.getRoom(roomID).getEventID(name);

        addEvent(eventID, roomID, speakerID);

        return created;
    }

    /**
     * Helper method - adds a newly created Event into the system
     * @param eventID The event's ID
     * @param roomID The ID of the room the event is in
     * @param speakerID The ID of the speaker holding the event
     */
    private void addEvent(String eventID, String roomID, String speakerID) {

        EventManager room = roomManager.getRoom(roomID);

        ArrayList<String> attendees = room.getSignUps(eventID);
        String announcementChatID = chatManager.createAnnouncementChat(eventID, attendees);
        room.setEventChatID(eventID, announcementChatID);

        this.updateSpeakerChatWithAnnouncement(speakerID, announcementChatID);
        this.updateSpeakerChat(speakerID, announcementChatID);
        this.speakerManager.addTalkId(speakerID, eventID);
        speakerManager.addTalkIdToDictionary(speakerID, eventID, room.getEventName(eventID));


    }

    /**
     * This is a helper method for the methods above; updates Speaker's chat list
     * @param personID
     * @param chatID
     */
    private void updateSpeakerChat(String personID, String chatID) {
        this.speakerManager.addChat(personID, chatID);
    }

    /**
     * This is helper method for the methods above; updates Speaker's announcementChatIDs
     * @param personID
     * @param announcementChatID
     */
    private void updateSpeakerChatWithAnnouncement(String personID, String announcementChatID) {
        this.speakerManager.addAnnouncementChats(personID, announcementChatID);
    }

    // OPTION 3

    /**
     * Prompts the user to input the information required to create a new Speaker account
     * @return true iff addSpeaker prompt was printed
     */
    public boolean addSpeakerPrompt(){
        presenter.printAddSpeakerPrompt();
        presenter.printAddNamePrompt();
        String name = input.nextLine();
        presenter.printAddEmailPrompt();
        String email = input.nextLine();
        presenter.printAddUsernamePrompt();
        String username = input.nextLine();
        presenter.printAddPasswordPrompt();
        String pass = input.nextLine();
        this.createSpeaker(name, username, pass, email);
        return true;
    }

    /**
     * Creates a new Person.Speaker account and adds it to the system
     * @param name The name of the Speaker
     * @param username The username of the Speaker
     * @param password The password of the Speaker
     * @param email The email of the Speaker
     * @return true iff a new Person.Speaker object was created
     */
    public boolean createSpeaker(String name, String username, String password, String email) {
        if (speakerManager.findPerson(username)) {
            speakerManager.createAccount(name, username, password, email);
            return true;
        }
        return true;
    }

    // OPTION 4

    /**
     * Prompts the user to input the information required to create an Event Message
     * @return true iff eventMessage prompt was printed
     */
    public boolean eventMessagePrompt() {
        presenter.printEventMessageIntro();
        presenter.printEventNamePrompt();
        String name = input.nextLine();
        presenter.printRoomNamePrompt();
        try {
            String roomName = chooseRoom();
            presenter.printMessageContentPrompt();
            String content = input.nextLine();
            eventMessage(name, roomName, content);
            return true;
        } catch (InvalidChoiceException e) {
            presenter.printException(e);
            return false;
        }
    }

    /**
     * Adds a Message with content content the AnnouncementChat contained within the Event with eventName
     * @param eventName The name of the Event
     * @return true iff the Message was added to the event's chat list
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
     * Sends a Message to one Attendee signed up for an event
     * @param eventName The name of the Event
     * @param attendeeUsername The username of the Attendee the user wishes to message
     * @return true iff the Message was sent to the corresponding Attendee
     */
    public boolean eventMessage(String eventName, String attendeeUsername) {
        return true;
    }

}
