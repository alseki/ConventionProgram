package Events;

import java.io.Serializable;
import java.time.LocalDateTime;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 15 2020

// Architecture Level - Use Class

public class EventPermissions implements Serializable {

    private RoomAccess roomAccess;
    private EventAccess eventAccess;

    /**
     * Constructor for a RoomPermissions object
     */
    public EventPermissions (RoomAccess roomAccess, EventAccess eventAccess) {
        this.roomAccess = roomAccess;
        this.eventAccess = eventAccess;
    }

    /**
     * Signs an individual Attendee up for an Event
     * @param personID The ID of the Attendee
     * @param eventID The Event
     * @return Whether the Event exists
     */
    public boolean signUpForEvent(String personID, String eventID, String roomID) throws CapacityException {
        try {
            if (checkRoomCapacity(eventID, roomID)) {
                Event event = eventAccess.getEvent(eventID);
                event.addAttendee(personID);
                return true;
            }
            throw new CapacityException();
        } catch (NullPointerException n) {
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
        Event event = eventAccess.getEvent(ID);
        if (event != null) {
            event.removeAttendee(personID);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Getter for the capacity of the Room whose permissions are dealt with here
     * @return the capacity (as an int)
     */
    private boolean checkRoomCapacity(String eventID, String roomID) {
        Event event = eventAccess.getEvent(eventID);
        Room room = roomAccess.getRoom(roomID);
        return (room.getCapacity() - event.getAttendeeIDs().size()) > 0;
    }

    /**
     * Checks if the inputted Event conflicts with multiple other inputted Events
     * @param roomID The the ID of the roomt he Event will be held in
     * @return True or False
     */
    public boolean checkConflicts(LocalDateTime startTime, EventType type, String roomID) throws NullPointerException {
        Event event;
        String[] events = roomAccess.getEventIDs(roomID);
        if (type.equals(EventType.WORKSHOP)) {
            event = new Workshop("", "", startTime, "");
        } else if (type.equals(EventType.TALK)) {
            event = new Talk("", "", startTime, "");
        } else {
            return false;
        }

        for (String e : events) {
            if (eventAccess.getEvent(e).conflictsWith(event)) {
                return false;
            }
        }
        return true;
    }

}
