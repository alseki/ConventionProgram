package Event;

import java.time.LocalDateTime;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 19 2020

// Architecture Level - Use Class

public class EventPermissions {

    private RoomAccess roomAccess;
    private EventAccess eventAccess;

    /**
     * Constructor for an EventPermissions object
     */
    public EventPermissions (RoomAccess roomAccess, EventAccess eventAccess) {
        this.roomAccess = roomAccess;
        this.eventAccess = eventAccess;
    }

    /**
     * Signs an individual Attendee up for an Event
     * @param personID The ID of the Attendee
     * @param eventID The ID of the Event
     * @param roomID  The ID of the Room the Event is in
     * @return Whether the Attendee was signed up
     */
    public boolean signUpForEvent(String personID, String eventID, String roomID) throws CapacityException {
        try {
            if (checkEventCapacity(eventID)) {
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
     * @param personID The ID of the Attendee to be removed
     * @param ID The Event to remove the Attendee from
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
     * Checks if an Event is full
     * @param eventID The ID of the Event
     * @return True if there is still space for more Attendees to register in this Event
     */
    private boolean checkEventCapacity(String eventID) {
        Event event = eventAccess.getEvent(eventID);
        return ((event.getCapacity() - event.getAttendeeIDs().size()) > 0);
    }

    /**
     * Checks if the inputted Event conflicts with multiple other inputted Events
     * @param startTime The time at which the Event will start
     * @param endTime The time at which the Event will end
     * @param type The type of Event, as an EventType
     * @param roomID The the ID of the room the Event will be held in
     * @return True if the event doesn't conflict with any existing events
     */
    public boolean checkConflicts(LocalDateTime startTime, LocalDateTime endTime, EventType type, String roomID) {
        try {
            String[] eventIDs = roomAccess.getEventIDs(roomID);
            return checkConflicts(startTime, endTime, type, eventIDs);
        } catch (NullPointerException n) {
            return true;
        }
    }

    /**
     * Checks if the inputted Event conflicts with any other Events in its Room and time slot
     * @param startTime The time at which the inputted Event will start
     * @param endTime The time at which the inputted Event will end
     * @param type The type of the inputted Event, as an EventType
     * @param events The IDs of the Events with which the new Event might conflict
     * @return True if the event does not conflict with any existing Events
     */
    public boolean checkConflicts(LocalDateTime startTime, LocalDateTime endTime, EventType type, String[] events) {
        Event event;
        if (type.equals(EventType.WORKSHOP)) {
            event = new Workshop("", "", startTime, endTime, "", 0);
        } else if (type.equals(EventType.TALK)) {
            event = new Talk("", "", startTime, endTime, "", 0);
        } else if (type.equals(EventType.PARTY)) {
            event = new Party("", startTime, endTime, "", 0);
        } else if (type.equals(EventType.PANEL)) {
            event = new Panel("", startTime, endTime, "", 0);
        } else {
            return false;
        }
        try {
            for (String eventID : events) {
                if (this.eventAccess.getEvent(eventID).conflictsWith(event)) {
                    return false;
                }
                return true;
            }
        } catch (NullPointerException n) {
            return true;
        }
        return true;
    }

}

