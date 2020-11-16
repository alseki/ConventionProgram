package Events;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 15 2020

// Architecture Level - Use Class

public class RoomPermissions implements Serializable {

    private LocalDateTime conferenceStart;
    private Room room;

    /**
     * Constructor for a RoomPermissions object
     * @param start The start time of the conference (as a LocalDateTime object)
     * @param room The room whose permissions are dealt with here
     */
    protected RoomPermissions (LocalDateTime start, Room room) {
        conferenceStart = start;
        this.room = room;
    }

    /**
     * Creates a LocalDateTime object for the time at which a particular Event starts, and makes this time the inputted
     * number of hours after the start of the conference
     * @param hour The number of hours after the conference start time when this Event should start
     * @return The time at which a particular Event starts (as a LocalDateTime object)
     */
    protected LocalDateTime toEventTime(int hour) {
        LocalTime time = LocalTime.of(conferenceStart.getHour() + hour, conferenceStart.getMinute());
        return LocalDateTime.of(conferenceStart.toLocalDate(), time);
    }

    /**
     * Checks if the two inputted Events conflict with each other in time
     * @param ev1 The first Event
     * @param ev2 The second Event
     * @return True or False
     */
    protected boolean checkConflicts(Event ev1, Event ev2) {
        return ev1.getStartTime().isBefore(ev2.getEndTime()) && ev2.getStartTime().isBefore(ev1.getEndTime());
    }

    /**
     * Checks if the inputted Event conflicts with multiple other inputted Events
     * @param ev1 The Event being evaluated for conflicts against a list of other Events
     * @param events The list of other Events
     * @return True or False
     */
    protected boolean checkConflicts(Event ev1, Event[] events) {
        for (Event event : events) {
            if (ev1.getStartTime().isBefore(event.getEndTime()) && event.getStartTime().isBefore(ev1.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for the capacity of the Room whose permissions are dealt with here
     * @return the capacity (as an int)
     */
    protected int getRoomCapacity() {
        return room.getCapacity();
    }

    protected String getName() {
        return room.getName();
    }

}
