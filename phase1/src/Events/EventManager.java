package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 14 2020

// Architecture level - Use class

import Message.MessageManager;

import java.util.ArrayList;
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
     * Returns a list of events stored in the EventManager
     * @param eventsIn The IDs of the events you want returned
     * @return The events, as an array of their String representations
     */
    public String[] getEventList(String[] eventsIn) {

        String[] eventInfoArray = new String[eventsIn.length];
        for (int i = 0; i < eventsIn.length; i++) {
            eventInfoArray[i] = events.get(eventsIn[i]).toString();
        }

        return eventInfoArray;

    }

    /**
     * Returns a textual representation of an event
     * @param eventID The ID of the event
     * @return The events, as a String
     */
    public String getEvent(String eventID) {
        return events.get(eventID).toString();
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

    /**
     * gets the event chatID for event with eventId
     * @param eventId a string reppign the event it
     * @return a string representing the chatId for this event
     */
    public String getAnnouncementChat(String eventId){
        Event ev = events.get(eventId);
        return ev.getChatId();
    }



    public boolean announceToEvent(Event event) {
        return false; //get messages
    }

    public MessageManager getMessages(Event event) {
        return null; //get messages
    }

    /**
     * gives access to all the people signed up to an event with eventid
     * @param eventid a string representing the event
     * @return an arraylist of strings with the id's of the attendees.
     */
    public ArrayList<String> getSignUps(String eventid){
        Event ev = events.get(eventid);
        return ev.getAttendeeIDs();
    }

    /**
     *sets the eventchatid for the event with eventid
     * @param eventid the id of the event for which we want to set the event
     * @param chatId a string representing the chatid
     * @return true
     */
    public boolean setEventChatId(String eventid, String chatId)
    {
        Event ev = events.get(eventid);
        ev.setChatId(chatId);
        return true;
    }
}
