package Events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.time.LocalDateTime;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 17 2020

// Architecture Level - Use Class

public class EventManager implements Serializable {

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
     * Getter for the capacity of this EventManager (how many total attendees can be signed up)
     * @return the capacity of this EventManager (as an int)
     */
    public int getEventManagerCapacity() {
        return this.permissionChecker.getRoomCapacity();
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
     * Helper getter for the total number of attendees currently signed up for the Events in this EventManager
     * @return the total number of attendees (as an int)
     */
    private int getNumAttendees() {
        int total = 0;
        for (Event ev : this.getEvents()) {
            total = total + ev.getAttendeeIDs().size();
        }
        return total;
    }

    /**
     * Returns all the Events stored in the EventManager
     * @return The Events, as an array of their String representations
     */
    public String[] getEventList() {
        Event[] eventArray = getEvents();
        if (eventArray != null) {
            String[] eventInfoArray = new String[eventArray.length];
            for (int i = 0; i < eventArray.length; i++) {
                eventInfoArray[i] = eventArray[i].toString();
            }
            return eventInfoArray;
        }
        return null;
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
     * Returns the name of an Event stored in this EventManager, given its ID
     * @param eventID The ID of the Event
     * @return The name of the Event, as a String
     */
    public String getEventName(String eventID) {
        return events.get(eventID).getName();
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
     * Adds a Talk to EventManager
     * @param name The name of the Talk
     * @return whether the Talk has been successfully added
     */
    public boolean addTalk(String name, String speakerID, LocalDateTime startTime, String description) {
        Event event = new Talk(name, speakerID, startTime, description);
        if (this.permissionChecker.checkConflicts(event, this.getEvents())) {
            return false;
        }
        else {
            events.put(event.getID(), event);
            eventsByName.put(event.getName(), event.getID());
            return true;
        }
    }

    /**
     * Adds a Workshop to EventManager
     * @param name The name of the Workshop
     * @return whether the Workshop has been successfully added
     */
    public boolean addWorkshop(String name, String speakerID, LocalDateTime startTime, String description) {
        Event event = new Workshop(name, speakerID, startTime, description);
        events.put(event.getID(), event);
        eventsByName.put(event.getName(), event.getID());
        return true;
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
     * Signs an individual Attendee up for an Event
     * @param personID The ID of the Attendee
     * @param ID The Event
     * @return whether the Attendee was signed up (true or false)
     */
    public boolean signUpForEvent(String personID, String ID) {
            Event event = events.get(ID);
            if ((event != null) && (this.getNumAttendees() < this.getEventManagerCapacity())) {
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

    /**
     * Getter for the name of this Room
     * @return the Name
     */
    public String getRoomName() {
        return permissionChecker.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != EventManager.class) {
            return false;
        }
        EventManager events2 = (EventManager) obj;
        if(!events2.getRoomName().equals(this.getRoomName())) {
            return false;
        }
        if (events2.getEventList().length == this.getEventList().length) {
            for (Event event: this.getEvents()) {
                if(events2.getEvent(event.getID()).equals(event.toString())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Getter for the ID of an event's chat
     * @param id The event's ID
     * @return The event's chat ID
     */
    public String getEventChat(String id) {
        Event event = events.get(id);
        if (event != null) {
            return event.getChatID();
        }
        else {
            return null;
        }
    }

    /**
     * Get Event formatted as: "[Event ID]: [ID of the Event]\new line
     *                         [Event Name]: [Name of Event]\newline
     *                         [Time] : [Start time to End time]\newline
     *                         [Description]: [Textual Representation of Event]\newline
     * @param eventID of the Event that is to be formatted.
     * @return Formatted string representation of the Event.
     */
    public String getFormattedEvent(String eventID){
        String eventName = getEventName(eventID);
        String desc = events.get(eventID).getDescription();
        String startToEnd = events.get(eventID).getStartTime().toString() + " to " +
                events.get(eventID).getEndTime().toString();
        return "Event ID: " + eventID + "\n" + "Event Name: " + eventName + "\n" +
                "Time: " + startToEnd + "\n" + "Description: " + desc + "\n";}
}
