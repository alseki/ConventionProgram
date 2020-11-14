package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 12 2020

// Architecture level - Use class

// Note that this is a Facade for event management methods, and the objects inside of it may change as we figure out
// which actors are going to be using which ones

import Message.MessageManager;

public class RoomManager {
   // EventSignupManager eventSignup;
   // EventAccessManager eventAccess;
   // EventAdminManager eventAdmin;
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
        //eventSignup = new EventSignupManager(events);
        //eventAccess = new EventAccessManager(events);
        //eventAdmin = new EventAdminManager(events);
    }

    /**
     * Returns information about the entire list of events
     * @return The list of events
     */
    public Event[] getEventList() {
        Event[] eventArray = {};
        return events.getEventList().toArray(eventArray);
    }

    /**
     * Signs an individual attendee up for an event
     * @param personID The attendee
     * @param eventID The event
     */
    public boolean signUpForEvent(String personID, String eventID) {
            Event event = events.getEvent(eventID);

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
     * @param eventID The event
     */
    public boolean removeFromEvent(String personID, String eventID) {
        // update capacity!

        Event event = events.getEvent(eventID);
        if (event != null) {
            event.removeAttendee(personID);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the ID of an event given its name
     * @param name The event's name
     * @return The ID
     */
    public String getEventID(String name) {
        return events.getEventID(name);
    }

    /**
     * Deletes an event
     * @param id The event's ID
     * @return Whether the event has been successfully deleted
     */
    public boolean removeEvent(String id) {
        return events.addEvent(events.getEvent(id));
    }

    /**
     * Adds an event
     * @param event The event
     * @return Whether the event has been successfully added
     */
    public boolean addEvent(Event event) {

        // check conflicts! also possibly edit so as to create the event itself?

        return events.addEvent(event);
    }

    public void saveRoom() {
        reader.save(getEventList());
    }

    public MessageManager getMessages(Event event) {
        return null; //get messages
    }
}
