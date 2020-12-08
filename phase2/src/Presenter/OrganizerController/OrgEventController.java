package Presenter.OrganizerController;

// Programmers: Cara McNeil, Sarah Kronenfeld, Eytan Weinstein
// Description: All the methods that take user input in the OrganizerController Event Menu
// Date Created: 01/11/2020
// Date Modified: 19/11/2020

import Event.EventManager;
import Event.EventPermissions;
import Event.EventType;
import Person.EmployeeManager;
import Person.OrganizerManager;
import Person.SpeakerManager;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.OverwritingException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class OrgEventController extends SubMenu {

    private String currentUserID;
    private SpeakerManager speakerManager;
    private EmployeeManager employeeManager;
    private OrganizerManager organizerManager;
    private EventPermissions eventPermissions;
    private EventManager eventManager;
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

    // Methods for Rooms and RoomManager

    /**
     * Adds a Room to the list of rooms in this convention
     * @param name     The name of the new Room in the convention (likely its number)
     * @param capacity The capacity of the new Room in the convention
     * @return true iff Room was added to the convention successfully
     */
    public boolean addRoom(String name, int capacity) throws InvalidChoiceException {
        if (name.equals("See all events") || roomManager.contains(name)) {
            throw new OverwritingException("room");
        }
        return this.roomManager.addRoom(name, capacity) != null;
    }

    /**
     * Converts a Room name into ID
     * @param name The name of the Room
     * @return the ID of the Room
     */
    private String getRoom(String name) throws InvalidChoiceException {
        if (roomManager.getRoomID(name) != null) {
            return roomManager.getRoomID(name);
        } else {
            throw new InvalidChoiceException("room");
        }
    }

    // Methods for Events and EventManager

    /**
     * Prompts the user to choose a valid start time for the new Event
     * @param time The start time as a String
     * @return The start time as a LocalDateTime object
     */
    private LocalDateTime getStartTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Prompts the user to choose a valid end time for the new Event
     * @param time The end time as a String
     * @return The end time as a LocalDateTime object
     */
    private LocalDateTime getEndTime(String time) throws DateTimeParseException {
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
     * Creates a new Event in this convention; also creates a new chat for this Event and sets the Event's chatID to the
     * ID of this new chat.
     * @param name        The name of the Event to be created
     * @param speakerID   The ID of the Speaker at this Event,
     * @param startTime   The start time of the Event to be created, as a LocalDateTime object
     * @param endTime     The end time of the Event to be created, as a LocalDateTime object
     * @param description The description for the Event to be created
     * @param capacity    The capacity of the Event to be created
     * @param type        The Type of the Event to be created, as an EventType
     * @param room        The name of the Room the Event is in
     * @return true iff the Talk was created successfully
     */
    public boolean createEvent(String name, String speakerID, LocalDateTime startTime, LocalDateTime endTime, String
            description, int capacity, EventType type, String room) throws InvalidChoiceException {
        String roomID = roomManager.getRoomID(room);
        if (roomID == null) {
            throw new InvalidChoiceException("room");
        }
        if (speakerID == "" || speakerID == null) {
            throw new InvalidChoiceException("speaker");
        }
        if (eventManager.contains(name)) {
            throw new OverwritingException("event");
        }
        if (eventPermissions.checkConflicts(startTime, endTime, type, roomID)) {
            String eventID = addEvent(name, speakerID, startTime, endTime, description, capacity, type);

            roomManager.addEvent(roomID, eventID);
            return true;
        }
        return false;
    }

    /**
     * Helper method - adds a newly created Event into EventManager
     * @param name        The name of the Event to be created
     * @param speakerID   The ID of the Speaker of the Event to be created, or "" if there is no Speaker
     * @param startTime   The start time of the Event to be created, as a LocalDateTime object
     * @param endTime     The end time of the Event to be created, as a LocalDateTime object
     * @param description The description for the Event to be created
     * @param capacity    The capacity of the Event to be created
     * @param type        The Type of the Event to be created, as an EventType
     */
    private String addEvent(String name, String speakerID, LocalDateTime startTime, LocalDateTime endTime,
                            String description, int capacity, EventType type) {
        String eventID = eventManager.addEvent(name, speakerID, startTime, endTime, description, capacity, type);
        ArrayList<String> attendeesID = eventManager.getAttendeeIDs(eventID);
        String announcementChatID = chatManager.createAnnouncementChat(eventID, attendeesID, name);
        eventManager.setEventChat(eventID, announcementChatID);
        this.updateSpeakerChatWithAnnouncement(speakerID, announcementChatID);
        this.updateSpeakerChat(speakerID, announcementChatID);
        speakerManager.addTalk(eventID, speakerID);
        speakerManager.addTalkIdToDictionary(speakerID, eventID, eventManager.getEventName(eventID));
        return eventID;
    }

    /**
     * Cancels an Event in this Convention. Attendees are notified, then the Event is removed from Speaker's list of
     * Events, and finally Speaker is notified in message from Organizer.
     * @param eventID The ID of the Event to be cancelled
     * @return true if Event was cancelled successfully
     */

    private boolean cancelEvent(String eventID) {
        String eventName = eventManager.getEventName(eventID);
        String chatName = eventManager.getEventChat(eventID);
        String speakerID = eventManager.getSpeakerID(eventID);
        LocalDateTime now = LocalDateTime.now();
        int dayHour = now.getHour();
        int dayMinute = now.getMinute();
        LocalDateTime startTime = getStartTime(eventID);
        int eventHour = startTime.getHour();
        int eventMinute = startTime.getMinute();
        if (eventHour < dayHour && eventMinute < dayMinute) {
            eventManager.removeEvent(eventID);
            String messageContent = eventName + " has been cancelled. An announcement by the event organizer will be made shortly.";
            eventMessage(eventName, chatName, messageContent);
            String messageContentToSpeaker = eventName + " has been cancelled. This is organizer. Attendees have been notified. I will call you very soon.";
            String organizerID = this.currentUserID;
            ArrayList<String> contacts = personManager.getContactList(organizerID);
            if (chatManager.existChat(organizerID, speakerID)) {
                String existingChatID = chatManager.findChat(organizerID, speakerID);
                messageManager.createMessage(organizerID, speakerID, existingChatID, messageContentToSpeaker);
            } else {
                String newChatID = chatManager.createChat(organizerID, speakerID);
                personManager.addChat(organizerID, newChatID);
                messageManager.createMessage(organizerID, speakerID, newChatID, messageContentToSpeaker);
            }
            speakerManager.removeTalk(speakerID, eventID);
            return true;
        }
        return false;
    }

    // Methods for Messages and MessageManager

    /**
     * This is a helper method for the methods above; updates SpeakerController's chat list
     * @param personID The  id of the person
     * @param chatID
     */
    private void updateSpeakerChat (String personID, String chatID){
        this.speakerManager.addChat(personID, chatID);
    }

    /**
     * This is helper method for the methods above; updates SpeakerController's announcementChatIDs
     * @param personID the id of the person
     * @param announcementChatID the id of the announcementChat
     */
    private void updateSpeakerChatWithAnnouncement (String personID, String announcementChatID){
        this.speakerManager.addAnnouncementChats(personID, announcementChatID);
    }


    /**
     * Adds a Message with content the AnnouncementChat contained within the Event with eventName*
     * @param eventName The name of the Event
     * @param chatName The name of the Chat
     */
    private void eventMessage (String eventName, String chatName, String messageContent){
        String eventID = eventManager.getEventID(eventName);
        String chatID = eventManager.getEventChat(chatName);
        String ev = eventManager.getEventChat(eventID);
        String m = messageManager.createMessage(eventID, chatID, messageContent);
        chatManager.addMessageIds(ev, m);
    }

    @Override
    public OrgEventMenu getPresenter() {
            return this.presenter;
        }

    }



