package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 14 2020

// Architecture level - Use class

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RoomPermissions {
    Room room;
    LocalDateTime conferenceStart;

    protected RoomPermissions (LocalDateTime start, Room room) {
        this.room = room;
        conferenceStart = start;
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
