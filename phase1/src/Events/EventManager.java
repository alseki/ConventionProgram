package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 14 2020

// Architecture level - Use class

import Message.MessageManager;

import java.util.Map;
import java.util.TreeMap;

public class EventManager {
    Map<String, String> eventsByName;
    Map<String, Event> events;
    RoomPermissions permissionChecker;

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




    private Event[] getEvents() {
        Event[] eventArray = {};
        return events.entrySet().toArray(eventArray);
    }

    /**
     * Returns all the events stored in the EventManager
     * @return The events, as an array of their String representations
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
     * Returns the ID of an event given its name
     * @param name The event's name
     * @return The ID
     */
    public String getEventID(String name) {
        if (eventsByName.get(name) != null) {
            return eventsByName.get(name);
        }
        else
        {
            return null;
        }
    }




    /**
     * Adds an event
     * @param name The event
     * @return Whether the event has been successfully added
     */
    public String addEvent(String name, String speakerID, int startTime) {

        Event event = new Talk(name, speakerID, permissionChecker.toEventTime(startTime));

        permissionChecker.checkConflicts(event, getEvents());

        events.put(event.getID(), event);
        eventsByName.put(event.getName(), event.getID());

        return event.getID();
    }

    /**
     * Adds an event
     * @param name The event
     * @return Whether the event has been successfully added
     */
    public String addEvent(String name, String speakerID, int startTime, String description) {

        Event event = new Talk(name, speakerID, permissionChecker.toEventTime(startTime), description);

        permissionChecker.checkConflicts(event, getEvents());

        events.put(event.getID(), event);
        eventsByName.put(event.getName(), event.getID());

        return event.getID();
    }

    /**
     * Deletes an event
     * @param id The event's ID
     * @return Whether the event has been successfully deleted
     */
    public boolean removeEvent(String id) {
        if (events.get(id) != null) {
            eventsByName.remove(events.get(id).getName());
            events.remove(id);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks to see whether two events conflict
     * @param event1ID The ID of the first event
     * @param event2ID The ID of the second event. We assume this event is held in this Room.
     * @return Whether the two events conflict
     */
    public boolean getConflict(String event1ID, String event2ID) {
        if (events.get(event1ID) != null) {
            return permissionChecker.checkConflicts(events.get(event1ID), events.get(event2ID));
        }
        return false;
    }




    /**
     * Signs an individual attendee up for an event
     * @param personID The attendee
     * @param id The event
     */
    public boolean signUpForEvent(String personID, String id) {
            Event event = events.get(id);

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
     * Takes an individual attendee off an event's attendee list
     * @param personID The attendee
     * @param id The event
     */
    public boolean removeFromEvent(String personID, String id) {

        Event event = events.get(id);
        if (event != null) {
            event.removeAttendee(personID);
            return true;
        }
        else {
            return false;
        }
    }



    public boolean announceToEvent(Event event) {
        return false; //get messages
    }

    public MessageManager getMessages(Event event) {
        return null; //get messages
    }
}
