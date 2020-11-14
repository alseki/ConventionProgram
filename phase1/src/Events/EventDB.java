package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 14 2020

// Architecture level - Use class

// This contains the data used in the EventManager Facade pattern. It contains basic methods to access information
// in Events

import Person.Attendee;

import java.util.*;

class EventDB {
    //ArrayList<Event> events;
    //Map<Room, ArrayList<String>> eventsByRoom;
    Map<String, String> eventsByName;
    Map<String, Event> events;

    /**
     * Creates an EventDB from an array of events
     * @param events The array
     */
    protected EventDB (Event[] events, Room room) {

        this.events = new TreeMap<String, Event>();

        eventsByName = new TreeMap<String, String>();

        for (Event event: events) {
            this.events.put(event.getID(), event);
            eventsByName.put(event.getName(), event.getID());
        }
    }

    /**
     * Adds an event to the EventDB
     * @param event The event to be added
     * @return Whether the event was successfully added
     */
    protected boolean addEvent(Event event) {
        events.put(event.getID(), event);
        eventsByName.put(event.getName(), event.getID());
        return true;
    }

    /**
     * Removes an event from the EventDB
     * @param id The ID of the event to be removed
     * @return Whether the event was successfully revmoved
     */
    protected boolean removeEvent(String id) {
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
     * Finds an event in the EventDB by ID
     * @param id The event's ID
     * @return The event
     */
    protected Event getEvent(String id) {
        if (events.get(id) != null) {
            return events.get(id);
        }
        else
        {
            return null;
        }
    }

    /**
     * Finds an event in the EventDB by name
     * @param name The event's name
     * @return The event's ID
     */
    protected String getEventID(String name) {
        if (eventsByName.get(name) != null) {
            return eventsByName.get(name);
        }
        else {
            return null;
        }
    }

    /**
     * Returns all the events in the EventDB
     * @return The ArrayList of events
     */
    protected Event[] getEventList() {

        Event[] eventArray = {};
        return events.entrySet().toArray(eventArray);

    }
}
