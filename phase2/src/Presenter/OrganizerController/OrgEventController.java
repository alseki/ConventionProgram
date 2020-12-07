package Presenter.OrganizerController;

// Programmers: Cara McNeil, Sarah Kronenfeld, Eytan Weinstein
// Description: All the methods that take user input in the OrganizerController Event Menu
// Date Created: 01/11/2020
// Date Modified: 19/11/2020

import Event.Event;
import Event.EventPermissions;
import Event.EventType;
import Person.EmployeeManager;
import Person.SpeakerManager;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.OverwritingException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrgEventController extends SubMenu {

    private String currentUserID;
    private SpeakerManager speakerManager;
    private EmployeeManager employeeManager;
    private EventPermissions eventPermissions;
    private OrgEventMenu presenter;

    public OrgEventController(SubMenu subMenu, String currentUserID, SpeakerManager speakerManager,
                              EmployeeManager employeeManager) {
        super(subMenu);
        this.currentUserID = currentUserID;
        this.speakerManager = speakerManager;
        this.employeeManager = employeeManager;
        eventPermissions = new EventPermissions(roomManager, eventManager);
        presenter = new OrgEventMenu(roomManager, eventManager, personManager);
    }

    // OPTION 1

    /**
     * Adds a room to the list of rooms in this convention
     * @param name The name of the new Room in the convention (likely its number)
     * @param capacity The capacity of the new Room in the convention
     * @return true iff Room was added to the convention successfully
     */
    public boolean addRoom(String name, int capacity) throws InvalidChoiceException {
        if (name.equals("1") || name.equals("0") || roomManager.contains(name)) {
            throw new OverwritingException("room");
        }
        return this.roomManager.addRoom(name, capacity) != null;
    }

    // OPTION 2

    /**
     * Converts a room name into ID
     */
    private String getRoom(String name) throws InvalidChoiceException {
        if (roomManager.getRoomID(name) != null) {
            return roomManager.getRoomID(name);
        }
        else {
            throw new InvalidChoiceException("room");
        }
    }

    /**
     * Prompts the user to choose a valid start time for the new Event
     * @return The start time as a LocalDateTime object
     */
    private LocalDateTime getStartTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Prompts the user to choose a valid start time for the new Event
     * @return The start time as a LocalDateTime object
     */
    private EventType getEventType(String type) throws InvalidChoiceException {
        try {
            return EventType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new InvalidChoiceException("event type");
        }
    }

    /**
     * Creates a new Talk in this convention; also creates a new chat for this Talk and sets the Talk's chatID to the
     * ID of this new chat.
     * @param name The name of the Talk
     * @param speaker The username of the SpeakerController at the Talk
     * @param start The start time of the Talk
     * @param description A description for the Talk
     * @param room The name of the Room the Talk is in
     * @return true iff the Talk was created successfully
     */
    public boolean createEvent(String name, String speaker, String start, String description, String room,
                                String type)
            throws InvalidChoiceException {
        String roomID = roomManager.getRoomID(room);
        if (roomID == null) {
            throw new InvalidChoiceException("room");
        }

        String speakerID = speakerManager.getCurrentUserID(speaker);
        if (speakerID == null) {
            throw new InvalidChoiceException("speaker");
        }

        if (eventManager.contains(name)) {
            throw new OverwritingException("event");
        }

        if (eventPermissions.checkConflicts(getStartTime(start), getEventType(type), roomID)) {
            String eventID = addEvent(name, speakerID, getStartTime(start), description, getEventType(type));

            roomManager.addEvent(roomID, eventID);
            return true;
        }
        return false;
    }

    /**
     * Helper method - adds a newly created Event into the system
     * @param speakerID The ID of the speaker holding the event
     */
    private String addEvent(String name, String speakerID, LocalDateTime start, String description,
                            EventType type) {
        // FIXME
        /*String eventID = eventManager.addEvent(name, speakerID, start, description, type);

        ArrayList<String> attendeesID = eventManager.getAttendeeIDs(eventID);

        String announcementChatID = chatManager.createAnnouncementChat(eventID, attendeesID, name);
        eventManager.setEventChat(eventID, announcementChatID);

        this.updateSpeakerChatWithAnnouncement(speakerID, announcementChatID);
        this.updateSpeakerChat(speakerID, announcementChatID);
        speakerManager.addTalk(eventID, speakerID);
        speakerManager.addTalkIdToDictionary(speakerID, eventID, eventManager.getEventName(eventID));


        return eventID;*/
        return null; // TODO delete this line when above is fixed
    }

    // TODO private String?? event ID

    /**
     * This is a helper method for the methods above; updates SpeakerController's chat list
     * @param personID The  id of the person
     * @param chatID
     */
    private void updateSpeakerChat(String personID, String chatID) {
        this.speakerManager.addChat(personID, chatID);

    }

    /**
     * This is helper method for the methods above; updates SpeakerController's announcementChatIDs
     * @param personID the id of the person
     * @param announcementChatID the id of the annoucnemchat
     */
    private void updateSpeakerChatWithAnnouncement(String personID, String announcementChatID) {
        this.speakerManager.addAnnouncementChats(personID, announcementChatID);

    }

    // OPTION 3

    /**
     * Prompts the user to input the information required to create a new SpeakerController account
     */
    public void addSpeaker(String name, String email, String username, String password) throws InvalidChoiceException {
        if (!speakerManager.findPerson(username)) {
            speakerManager.createAccount(name, username, password, email);
        }
        else {
            throw new OverwritingException("account");
        }
    }

    // OPTION 4

    /**
     * Creates a new Person.Employee account and adds it to the system (including into a specific dictionary of employees solely)
     * @param name The name of the employee
     * @param username The username of the employee
     * @param password The password of the employee
     * @param email The email of the employee
     * @return true iff a new employee object was created
     */
    public void createEmployee(String name, String username, String password, String email) throws InvalidChoiceException {
        if (!employeeManager.findPerson(username)) {
            employeeManager.createAccount(name, username, password, email);
        }
        else {
            throw new OverwritingException("account");
        }
    }

    // TODO createAttendee


    // **** createEmployee will be in AdminEventController. Only administrators will create employees along with organizers

    // OPTION 4

    /**
     * Prompts the user to input the information required to create an Event Message
     */
    public void eventMessagePrompt() {
        presenter.printEventMessageIntro();
        presenter.printEventNamePrompt();
        String name = "";//SubMenu.readInput(input);
        presenter.printChatNamePrompt();
        String chatName = "";//SubMenu.readInput(input);
        presenter.printMessageContentPrompt();
        String content = "";//SubMenu.readInput(input);
        eventMessage(name, chatName, content);
    }

    /**
     * Adds a Message with content content the AnnouncementChat contained within the Event with eventName
     * @param eventName The name of the Event
     */
    private void eventMessage(String eventName, String chatName, String messageContent){
        String eventId =  eventManager.getEventID(eventName); // eventId
        String chatID = eventManager.getEventChat(chatName);
        String ev = eventManager.getEventChat(eventId); // chatid
        String m = messageManager.createMessage(eventId, chatID, messageContent); // creating message for event
        chatManager.addMessageIds(ev,m);// adding message to event chat
    }


    @Override
    public OrgEventMenu getPresenter() {
        return this.presenter;
    }
}
