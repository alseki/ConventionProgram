package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 12 2020

// Architecture level - Use class

// Note that this is a Facade for event management methods, and the objects inside of it may change as we figure out
// which actors are going to be using which ones

public class RoomManager {
    EventSignupManager eventSignup;
    EventAccessManager eventAccess;
    EventAdminManager eventAdmin;
    EventDB events;
    EventReader reader;


    /**
     * Creates a new EventManager with events read in by some sort of EventReader
     * @param reader The EventReader used to read in events
     */
    public RoomManager(EventReader reader) {
        Room thisRoom = new Room();
        this.reader = reader;
        this.rmBuilder(reader, thisRoom);
    }

    /**
     * Creates a new EventManager with events read in by some sort of EventReader
     * @param reader The EventReader used to read in events
     * @param name The name of this room
     */
    public RoomManager(EventReader reader, String name) {
        Room thisRoom = new Room(name);
        this.reader = reader;
        this.rmBuilder(reader, thisRoom);
    }

    private void rmBuilder(EventReader reader, Room room) {
        events = new EventDB(reader.readEvents(), room);
        eventSignup = new EventSignupManager(events);
        eventAccess = new EventAccessManager(events);
        eventAdmin = new EventAdminManager(events);
    }

    /**
     * Returns information about the entire list of events
     * @return The list of events
     */
    public Object getEventList() {
        return eventAccess.getEventList();
    }

    /**
     * Signs an individual attendee up for an event
     * @param personID The attendee
     * @param eventID The event
     */
    public boolean signUpForEvent(String personID, String eventID) {
        return eventSignup.signUpForEvent(personID, eventID);
    }

    /**
     * Takes an individual attendee off an event's attendee list
     * @param personID The attendee
     * @param eventID The event
     */
    public boolean removeFromEvent(String personID, String eventID) {
        return eventSignup.removeFromEvent(personID, eventID);
    }

    /**
     * Returns the ID of an event given its name
     * @param name The event's name
     * @return The ID
     */
    public String getEventID(String name) {
        return eventAccess.getEventID(name);
    }

    /**
     * Deletes an event
     * @param id The event's ID
     * @return Whether the event has been successfully deleted
     */
    public boolean removeEvent(String id) {
        return eventAdmin.removeEvent(id);
    }

    /**
     * Adds an event
     * @param event The event
     * @return Whether the event has been successfully added
     */
    public boolean addEvent(Event event) {
        return eventAdmin.addEvent(event);
    }

    public void saveRoom() {
        reader.save(eventAccess.getEventList());
    }
}
