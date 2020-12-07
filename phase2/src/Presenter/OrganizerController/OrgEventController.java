package Presenter.OrganizerController;

// Programmers: Cara McNeil, Sarah Kronenfeld, Eytan Weinstein
// Description: All the methods that take user input in the OrganizerController Event Menu
// Date Created: 01/11/2020
// Date Modified: 19/11/2020

import Event.Event;
import Event.EventPermissions;
import Event.EventType;
import Event.EventManager;
import Person.EmployeeManager;
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

    // OPTION 1

    /**
     * Adds a room to the list of rooms in this convention
     *
     * @param name     The name of the new Room in the convention (likely its number)
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
        } else {
            throw new InvalidChoiceException("room");
        }
    }

    /**
     * Prompts the user to choose a valid start time for the new Event
     *
     * @return The start time as a LocalDateTime object
     */
    private LocalDateTime getStartTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Prompts the user to choose a valid end time for the new Event
     *
     * @return The end time as a LocalDateTime object
     */
    private LocalDateTime getEndTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Prompts the user to choose a valid start time for the new Event
     *
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
     *
     * @param name        The name of the Event to be created
     * @param startTime   The start time of the Event to be created, as a LocalDateTime object
     * @param endTime     The end time of the Event to be created, as a LocalDateTime object
     * @param description The description for the Event to be created
     * @param capacity    The capacity of the Event to be created
     * @param type        The Type of the Event to be created, as an EventType
     * @param room        The name of the Room the Event is in
     * @return true iff the Talk was created successfully
     */
    public boolean createEvent(String name, String speakerID, LocalDateTime startTime, LocalDateTime endTime, String description, int
            capacity, EventType type, String room)
            throws InvalidChoiceException {
        String roomID = roomManager.getRoomID(room);
        if (roomID == null) {
            throw new InvalidChoiceException("room");
        }
        // TODO FIX THIS LINE!!! The line has been removed, and speakerID added as parameter.
        //String speakerID = speakerManager.getCurrentUserID(currentUserID);
        if (speakerID == null) {
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
     *
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

    // TODO private String?? event ID

    private boolean cancelEvent(String eventID) {
        String eventName = eventManager.getEventID(eventID);
        String chatName = eventManager.getEventChat(eventID);
        LocalDateTime now = LocalDateTime.now();
        int dayHour = now.getHour();
        int dayMinute = now.getMinute();

        // TODO fix when getStartTime is back
        LocalDateTime startTime = getStartTime(eventID);
        int eventHour = startTime.getHour();
        int eventMinute = startTime.getMinute();

        if (eventHour < dayHour && eventMinute < dayMinute) {
            eventManager.removeEvent(eventID);
            String messageContent = eventName + " has been cancelled. An announcement by the event organizer will be made shortly.";
            eventMessage (eventName, chatName, messageContent);
            return true;

            // TODO will do - do we want to send automatic announcementChat or will Organizer proceed to that method himself once he's done with this method?

            // TODO will do - instead of update Speaker's TalkList, and send out message/announcement to Speaker and Employees


        }
        return false;
    }

        //char eventManger

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

        // OPTION 4

        /**
         * Prompts the user to input the information required to create an Event Message
         */
        public void eventMessagePrompt () {
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
         * Adds a Message with content the AnnouncementChat contained within the Event with eventName
         * @param eventName The name of the Event
         */
        private void eventMessage (String eventName, String chatName, String messageContent){
            String eventId = eventManager.getEventID(eventName); // eventId
            String chatID = eventManager.getEventChat(chatName);
            String ev = eventManager.getEventChat(eventId); // chatid
            String m = messageManager.createMessage(eventId, chatID, messageContent); // creating message for event
            chatManager.addMessageIds(ev, m);// adding message to event chat
        }


        @Override
        public OrgEventMenu getPresenter() {
            return this.presenter;
        }
    }



