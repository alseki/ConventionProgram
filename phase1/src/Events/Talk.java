package Events;

import Events.Event;
import java.time.LocalDateTime;
import java.time.Duration;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 14 2020

// Architecture Level - Entity

public class Talk extends Event {

    private String speakerID;
    private String description;

    // A Talk differs from an event in that it is exactly one hour long, and cannot take place in the same room as
    // any other event while that event is occurring.

    public Talk (String name, String speakerID, LocalDateTime startTime) {
        super(name, startTime,startTime.plusHours(1));
        this.speakerID = speakerID;
        description = "Talk: " + name;
    }

    public Talk (String name, String speakerID, LocalDateTime startTime, String description) {
        super(name, startTime,startTime.plusHours(1));
        this.speakerID = speakerID;
        this.description = description;
    }

    /**
     * Getter for the description of this Talk
     * @return the description of this Talk (as a String)
     */
    public String getDescription() {
        return description;
    }

    public boolean conflictsWith(Event event) {
        return this.getStartTime().isBefore(event.getEndTime()) && event.getStartTime().isBefore(this.getEndTime());
    }
}
