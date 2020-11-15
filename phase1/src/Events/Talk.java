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
        description = name;
    }

    public Talk (String name, String speakerID, LocalDateTime startTime, String description) {
        super(name, startTime,startTime.plusHours(1));
        this.speakerID = speakerID;
        this.description = name + ": " + description;
    }

    /**
     * Getter for the description of this Talk
     * @return the description of this Talk (as a String)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of this Talk
     * @param description A new description for this Talk
     */
    protected void setDescription(String description){
        this.description = description;
    }

    /**
     * Getter for the speaker of this Talk
     * @return the ID of the speaker of this Talk (as a String)
     */
    public String getSpeakerID() {
        return speakerID;
    }

    /**
     * Setter for the speaker of this Talk
     * @param speakerID The ID of a new speaker for this Talk
     */
    protected void setSpeakerID(String speakerID){
        this.speakerID = speakerID;
    }

    public boolean conflictsWith(Event event) {
        return this.getStartTime().isBefore(event.getEndTime()) && event.getStartTime().isBefore(this.getEndTime());
    }
}
