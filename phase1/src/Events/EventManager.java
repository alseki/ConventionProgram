package Events;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 15 2020

// Architecture Level - Use Class

public class EventManager {

    /** A mapping of Event names to their respective IDs. */
    private Map<String, String> eventsByName;

    /** A mapping of IDs to the respective Events they represent. */
    private Map<String, Event> events;

    /** The RoomPermissions object which delegates permissions to these Events. */
    private RoomPermissions permissionChecker;

    /**
     * Creates a new empty EventManager
     * @param checker A RoomPermissions object based on the room in which these Events are being held
     */
    public EventManager(RoomPermissions checker) {
        events = new TreeMap<String, Event>();
        eventsByName = new TreeMap<String, String>();
        permissionChecker = checker;
    }

    /**
     * Creates a new EventManager from an array of Events
     * @param checker A RoomPermissions object based on the room in which these Events are being held
     * @param events The array of Events to be read in
     */
    public EventManager(RoomPermissions checker, Event[] events) {
        this.events = new TreeMap<String, Event>();
        eventsByName = new TreeMap<String, String>();
        for (Event event: events) {
            this.events.put(event.getID(), event);
            eventsByName.put(event.getName(), event.getID());
        }
        permissionChecker = checker;
    }

    /**
     * Helper getter for the all the Events in this EventManager
     * @return an array of all Events in this EventManager
     */
    private Event[] getEvents() {
        Event[] eventArray = {};
        return events.entrySet().toArray(eventArray);
    }

    /**
     * Returns all the Events stored in the EventManager
     * @return The Events, as an array of their String representations
     */
    public String[] getEventList() {
        Event[] eventArray = getEvents();
        String[] eventInfoArray = new String[eventArray.length];
        for (int i = 0; i < eventArray.length; i++) {
            eventInfoArray[i] = eventArray[i].toString();
        }
        return eventInfoArray;
    }

    /**
     * Returns a list of Events stored in the EventManager
     * @param eventsIn The IDs of the Events you want returned
     * @return The Events, as an array of their String representations
     */
    public String[] getEventList(String[] eventsIn) {
        String[] eventInfoArray = new String[eventsIn.length];
        for (int i = 0; i < eventsIn.length; i++) {
            eventInfoArray[i] = events.get(eventsIn[i]).toString();
        }
        return eventInfoArray;
    }

    /**
     * Returns a textual representation of an Event
     * @param eventID The ID of the Event
     * @return The Event, as a String
     */
    public String getEvent(String eventID) {
        return events.get(eventID).toString();
    }

    /**
     * Returns the ID of an Event given its name
     * @param name The Event's name
     * @return The ID
     */
    public String getEventID(String name) {
            return eventsByName.get(name);
    }

    /**
     * Adds an Event to EventManager (without a description)
     * @param name The Event
     * @return whether the Event has been successfully added
     */
    public String addEvent(String name, String speakerID, int startTime) {
        Event event = new Talk(name, speakerID, permissionChecker.toEventTime(startTime));
        permissionChecker.checkConflicts(event, getEvents());
        events.put(event.getID(), event);
        eventsByName.put(event.getName(), event.getID());
        return event.getID();
    }

    /**
     * Adds an event to EventManager (with a description)
     * @param name The Event
     * @return whether the Event has been successfully added
     */
    public String addEvent(String name, String speakerID, int startTime, String description) {
        Event event = new Talk(name, speakerID, permissionChecker.toEventTime(startTime), description);
        permissionChecker.checkConflicts(event, getEvents());
        events.put(event.getID(), event);
        eventsByName.put(event.getName(), event.getID());
        return event.getID();
    }

    /**
     * Deletes an Event
     * @param ID The Event's ID
     * @return whether the Event has been successfully deleted
     */
    public boolean removeEvent(String ID) {
        if (events.get(ID) != null) {
            eventsByName.remove(events.get(ID).getName());
            events.remove(ID);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks to see whether two Events conflict
     * @param event1ID The ID of the first Event
     * @param event2ID The ID of the second Event. We assume this Event is held in this Room.
     * @return whether the two Events conflict (true or false)
     */
    public boolean getConflict(String event1ID, String event2ID) {
        if (events.get(event1ID) != null) {
            return permissionChecker.checkConflicts(events.get(event1ID), events.get(event2ID));
        }
        return false;
    }

    /**
     * Signs an individual Attendee up for an Event
     * @param personID The ID of the Attendee
     * @param ID The Event
     * @return whether the Attendee was signed up (true or false)
     */
    public boolean signUpForEvent(String personID, String ID) {
            Event event = events.get(ID);
            // check + update capacity!
            if (event != null) {
                event.addAttendee(personID);
                return true;
            }
            else {
                return false;
            }
    }

    /**
     * Takes an individual Attendee off an Event's list of attendees
     * @param personID The Attendee
     * @param ID The Event
     * @return whether the Attendee was removed from the Event (true or false)
     */
    public boolean removeFromEvent(String personID, String ID) {
        Event event = events.get(ID);
        if (event != null) {
            event.removeAttendee(personID);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the chatID for the Event with the inputted eventID
     * @param eventID The ID of the Event for which the chatID is being requested
     * @return the chatID for the inputted Event (as a String)
     */
    public String getAnnouncementChat(String eventID){
        Event ev = events.get(eventID);
        return ev.getChatID();
    }

    /**
     * Returns an array list of all the Attendees (by ID) signed up to the Event with the inputted eventID
     * @param eventID The Event we are interested in
     * @return an array list of the IDs of all Attendees
     */
    public ArrayList<String> getSignUps(String eventID){
        Event ev = events.get(eventID);
        return ev.getAttendeeIDs();
    }

    /**
     * Sets the chatID for the Event with the inputted eventID
     * @param eventID The ID of the Event for which we want to set the chatID
     * @param chatID The chatID
     */
    public void setEventChatID(String eventID, String chatID) {
        Event ev = events.get(eventID);
        ev.setChatID(chatID);
    }

    /**
     * Getter for this password of the Event with the inputted eventID
     * @param eventID The ID of the Event for which we want the password
     * @return String representing the Event's password
     */
    public String getEventPassword(String eventID) {
        Event ev = events.get(eventID);
        return ev.getPassword();
    }
}
