package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 12 2020

// Architecture level - Use class

// This contains the data used in the EventManager Facade pattern. It contains basic methods to access information
// in Events

import Person.Attendee;

import java.util.*;

class EventDB {
    ArrayList<String> eventIDs;
    ArrayList<String> eventNames;
    ArrayList<Event> events;

    Room room;

    /**
     * Creates an EventDB from an array of events
     * @param events The array
     */
    protected EventDB (Event[] events, Room room) {
        this.events = new ArrayList<Event>(Arrays.asList(events));

        this.eventNames = new ArrayList<String>();
        this.eventIDs = new ArrayList<String>();

        for(Event event: events) {
            eventNames.add(event.getName());
            eventIDs.add(event.getID());
        }

        this.room = room;
    }

    /**
     * Adds an event to the EventDB
     * @param event The event to be added
     * @return Whether the event was successfully added
     */
    protected boolean addEvent(Event event) {
        events.add(event);
        eventNames.add(event.getName());
        eventIDs.add(event.getID());
        if (events.indexOf(event) == eventIDs.indexOf(event.getID()) &&
                events.indexOf(event) == eventNames.indexOf(event.getName())){
            return true;
        }
        else {
            events.remove(event);
            eventNames.remove(event.getName());
            eventIDs.remove(event.getID());
            System.out.println("Arrays unbalanced in EventDB");
            return false;
        }
    }

    /**
     * Removes an event from the EventDB
     * @param event The event to be removed
     * @return Whether the event was successfully revmoved
     */
    protected boolean removeEvent(Event event) {
        if (events.contains(event)) {
            events.remove(event);
            eventIDs.remove(event.getID());
            eventIDs.remove(event.getName());
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
        if (eventIDs.contains(id)) {
            return events.get(eventIDs.indexOf(id));
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
        if (eventNames.contains(name)) {
            return eventIDs.get(eventNames.indexOf(name));
        }
        else {
            return null;
        }
    }

    /**
     * Returns all the events in the EventDB
     * @return The ArrayList of events
     */
    protected ArrayList<Event> getEventList() {
        return events;
    }

    /**
     * Returns the capacity of this Room
     * @return This Room's capacity
     */
    protected int roomCapacity() {
        return room.getCapacity();
    }
}
