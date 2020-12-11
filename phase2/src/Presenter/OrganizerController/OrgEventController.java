package Presenter.OrganizerController;

// Programmers: Cara McNeil, Sarah Kronenfeld, Eytan Weinstein
// Description: All the methods that take user input in the OrganizerController Event Menu
// Date Created: 01/11/2020
// Date Modified: 09/12/2020

import Event.*;
import Person.EmployeeManager;
import Person.OrganizerManager;
import Person.SpeakerManager;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.InvalidFormatException;
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

    // Methods for creating/deleting Rooms in RoomManager

    /**
     * Adds a Room to the list of Rooms in this convention
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


    // Methods for adding/cancelling Events in EventManager

    /**
     * Chooses a valid time for creating a new Event
     * @param time The time as a String
     * @return The time as a LocalDateTime object
     */
    private LocalDateTime getFormattedTime(String time) throws InvalidChoiceException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("date", "a date in the following format: yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Chooses a valid EventType for the new Event
     * @param type type of the new Event (as a String)
     * @return The type of the new Event (as an EventType object)
     */
    private EventType getEventType(String type) throws InvalidChoiceException {
        try {
            return EventType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new InvalidChoiceException("event type");
        }
    }

    /**
     * Creates a new Event in this convention
     * @param name        The name of the Event to be created
     * @param speakerUsername   The username of the Speaker at this Event,
     * @param startTime   The start time of the Event to be created, as a LocalDateTime object
     * @param endTime     The end time of the Event to be created, as a LocalDateTime object
     * @param description The description for the Event to be created
     * @param capacity    The capacity of the Event to be created
     * @param type        The Type of the Event to be created, as an EventType
     * @param room        The name of the Room the Event is in
     * @return true iff the Talk was created successfully
     */
    public boolean createEvent(String name, String speakerUsername, String startTime, String endTime, String
            description, int capacity, EventType type, String room) throws InvalidChoiceException {

        String speakerID = speakerManager.getCurrentUserID(speakerUsername);
        String roomID = roomManager.getRoomID(room);

        if (roomID == null) { // If the user inputs an incorrect room
            throw new InvalidChoiceException("room");
        }
        if (speakerID.equals("") || speakerID == null) { // If the user inputs an incorrect speaker
            throw new InvalidChoiceException("speaker");
        }
        if (eventManager.contains(name)) { // If the user inputs an event name that already exists in the system
            throw new OverwritingException("event");
        }
        LocalDateTime start = getFormattedTime(startTime);
        LocalDateTime end = getFormattedTime(endTime);
        if (eventPermissions.checkConflicts(start, end, type, roomID)) {
            throw new OverwritingException("event at this time");
        }
        if (eventPermissions.checkRoomCapacity(start, end, capacity, roomID)) {
            String eventID = this.addEvent(name, speakerID, start, end, description, capacity, type);
            roomManager.addEvent(roomID, eventID);
            return true;
        }
        return false;
    }

    /**
     * Helper method - adds a newly created Event into EventManager; also creates a new chat for this Event and sets
     * the Event's chatID to the ID of this new chat.
     * @param name        The name of the Event to be created
     * @param speakerID   The ID of the Speaker of the Event to be created, or "" if there is no Speaker
     * @param startTime   The start time of the Event to be created, as a LocalDateTime object
     * @param endTime     The end time of the Event to be created, as a LocalDateTime object
     * @param description The description for the Event to be created
     * @param capacity    The capacity of the Event to be created
     * @param type        The Type of the Event to be created, as an EventType
     * @returns the ID of the new Event
     */
    private String addEvent(String name, String speakerID, LocalDateTime startTime, LocalDateTime endTime,
                            String description, int capacity, EventType type) {
        String eventID = eventManager.addEvent(name, speakerID, startTime, endTime, description, capacity, type);
        ArrayList<String> attendeesID = eventManager.getAttendeeIDs(eventID);
        String announcementChatID = chatManager.createAnnouncementChat(eventID, attendeesID, name);
        eventManager.setEventChat(eventID, announcementChatID);
        this.updateSpeakerChatWithAnnouncement(speakerID, announcementChatID);
        this.updateSpeakerChat(speakerID, announcementChatID);
        String eventType = convertEventTypeToString(type);
        if(eventType == "PANEL"){
            speakerManager.addPanel(speakerID, eventID);
        } else {
            speakerManager.addNonPanel(speakerID, eventID);
        }
        return eventID;
    }

    /** Helper method for addEvent method above; converts EventType to String
     * @param event The type of Event (as an EventType object)
     * @return The Event's type as a String
     */
    public String convertEventTypeToString(EventType event) {
        String eventTypeString = EventType.convertToString(event);
        return eventTypeString;
    }

    /**
     * Adds the Speaker with speakerID to the Panel with ID eventID
     * @param speakerUsername   The username of the Speaker
     * @param eventID     The ID of the Panel
     * @return true iff the Speaker was signed up
     */
    public boolean addSpeakerToPanel(String speakerUsername, String eventID) throws InvalidChoiceException, NotPanelException,
            CapacityException {
        String speakerID = speakerManager.getCurrentUserID(speakerUsername);
        Event event = eventManager.getEvent(eventID);
        if (event == null) {
            throw new InvalidChoiceException("event");
        } else if (!(event.getClass().equals(Panel.class))) {
            throw new NotPanelException();
        } else {
            return eventPermissions.signSpeakerUpForPanel(speakerID, eventID);
        }
    }

    /**
     * Adds the Speaker with speakerID to the Panel with ID eventID
     * @param speakerUsername   The username of the Speaker
     * @param eventID     The ID of the Panel
     * @return true iff the Speaker was signed up
     */
    public boolean removeSpeakerFromPanel(String speakerUsername, String eventID) throws InvalidChoiceException,
            NotPanelException, CapacityException {
        String speakerID = speakerManager.getCurrentUserID(speakerUsername);
        Event event = eventManager.getEvent(eventID);
        if (event == null) {
            throw new InvalidChoiceException("event");
        } else if (!(event.getClass().equals(Panel.class))) {
            throw new NotPanelException();
        } else {
            return eventPermissions.removeSpeakerFromPanel(speakerID, eventID);
        }
    }






    /**
     * Cancels an Event in this Convention. Attendees are notified, then the Event is removed from Speaker's list of
     * Events, and finally Speaker is notified in message from Organizer.
     * @param eventName The ID of the Event to be cancelled
     * @return true if Event was cancelled successfully
     */

    private boolean cancelEvent(String eventName) {

        // TODO comment all sections of this function so it is legible.
        // TODO add try catch blocks

        // eventname, chatName, and speaker will be need below
        String eventID = eventManager.getEventID(eventName);
        String chatName = eventManager.getEventChat(eventID);
        String speakerID = eventManager.getSpeakerID(eventID);

        // getting the current time of day
        LocalDateTime now = LocalDateTime.now();
        int dayHour = now.getHour();
        int dayMinute = now.getMinute();

        // this is to get the start time of event
        LocalDateTime startTime = eventManager.getStartTime(eventID);
        int eventHour = startTime.getHour();
        int eventMinute = startTime.getMinute();

        // comparing start time of event to time of day (effectively permitting cancellation 1 minute before event starts: oh well!!)
        if (eventHour < dayHour && eventMinute < dayMinute) {

            // event will be removed if the time of day before time of event
            eventManager.removeEvent(eventID);

            // This message is pretty crucial for the purpose of the app. Attendees must be notified. And the message has to be sent in this method.
            String messageContent = eventName + " has been cancelled. An announcement by the event organizer will be made shortly.";
            eventMessage(eventName, chatName, messageContent);

            // This message is to Speaker (who is not in the chatID group linked with Event's creation. I asked Karyn and Ran about this.
            // Speaker won't get the message through event's particular chatID like attendees would.
            String messageContentToSpeaker = eventName + " has been cancelled. This is organizer. Attendees have been notified. I will call you very soon.";

            // So what is going on here is that Organizer has to message Speaker directly.

            // This line, I think is unnecessary.
            String organizerID = this.currentUserID;

            // Oh, and what is below would not apply to PARTY, so there will have to be a check Event type, contradicting
            // what I said below line 263 uugghh. (I wrote that first)

            // So Organizer might already have a 1-1 chat with the speaker, if that is the case .. send message away
            ArrayList<String> contacts = personManager.getContactList(organizerID);
            if (chatManager.existChat(organizerID, speakerID)) {
                String existingChatID = chatManager.findChat(organizerID, speakerID);
                messageManager.createMessage(organizerID, speakerID, existingChatID, messageContentToSpeaker);

                // if such is not the case, Organizer has to "create" chat with the said speaker and send message
            } else {
                String newChatID = chatManager.createChat(organizerID, speakerID);
                personManager.addChat(organizerID, newChatID);
                messageManager.createMessage(organizerID, speakerID, newChatID, messageContentToSpeaker);
            }

            // This is where event will be removed from speaker's Event list (I might add remove from already existing specific
            // panel or non-panel lists, but uuggghhh, then that requires event type checks right here and .... ??
            speakerManager.deleteEvent(eventID);

            // I believe this should be a boolean function.
            return true;
        }
        return false;
    }

    /**
     * Changes the capacity of an existing Event
     * @param eventName The name of the Event for which the capacity needs to be changed
     * @param capacity The new capacity of the Event
     * @return true iff the capacity was changed
     */
    public boolean changeCapacity(String eventName, int capacity) throws InvalidChoiceException {
        String eventID = eventManager.getEventID(eventName);
        if (eventManager.getEvent(eventID) != null) {
            eventManager.setCapacity(eventID, capacity);
            return true;
        } else {
            throw new InvalidChoiceException("event");
        }
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





