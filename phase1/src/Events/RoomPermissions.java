package Events;

import java.time.LocalDateTime;
import java.time.LocalTime;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 15 2020

// Architecture Level - Use Class

public class RoomPermissions {

    private LocalDateTime conferenceStart;
    private Room room;

    protected RoomPermissions (LocalDateTime start, Room room) {
        conferenceStart = start;
        this.room = room;
    }

    protected LocalDateTime toEventTime(int hour) {

        LocalTime time = LocalTime.of(conferenceStart.getHour() + hour, conferenceStart.getMinute());

        return LocalDateTime.of(conferenceStart.toLocalDate(), time);

    }

    protected boolean checkConflicts(Event event1, Event event2) {
        return false;
    }

    protected boolean checkConflicts(Event event1, Event[] events) {
        return false;
    }

    protected int getRoomCapacity() {
        return room.getCapacity();
    }

    protected int spotsLeft(Event event) {
        return 0;
    }

}
