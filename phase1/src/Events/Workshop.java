package Events;

import Events.Event;
import java.time.LocalDateTime;
import java.time.Duration;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 15 2020

// Architecture Level - Entity

public class Workshop extends Event {

    private String speakerID;
    private String description;

    // A Workshop is exactly one half hour long, and can take place in the same Room as other Workshops (but not Talks!)
    // so long as the number of its attendees does not exceed the capacity of that Room.

    /**
     * Constructor for Workshop objects
     * @param name The Workshop's name
     * @param speakerID The ID of the speaker at this Workshop
     * @param startTime The time when the Workshop starts
     */
    public Workshop (String name, String speakerID, LocalDateTime startTime) {
        super(name, startTime,startTime.plusMinutes(30));
        this.speakerID = speakerID;
        description = name;
    }

    /**
     * Alternate constructor for Workshop objects
     * @param name The Workshop's name
     * @param speakerID The ID of the speaker at this Workshop
     * @param startTime The time when the Workshop starts
     * @param description A description of this Workshop
     */
    public Workshop (String name, String speakerID, LocalDateTime startTime, String description) {
        super(name, startTime,startTime.plusMinutes(30));
        this.speakerID = speakerID;
        this.description = name + ": " + description;
    }

    /**
     * Getter for the description of this Workshop
     * @return the description of this Workshop (as a String)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of this Workshop
     * @param description A new description for this Workshop
     */
    protected void setDescription(String description){
        this.description = description;
    }

    /**
     * Getter for the speaker of this Workshop
     * @return the ID of the speaker of this Workshop (as a String)
     */
    public String getSpeakerID() {
        return speakerID;
    }

    /**
     * Setter for the speaker of this Workshop
     * @param speakerID The ID of a new speaker for this Workshop
     */
    protected void setSpeakerID(String speakerID){
        this.speakerID = speakerID;
    }

    /**
     * Checks if the inputted Event conflicts in time with this Workshop (recall that only a Talk can conflict)
     * @param event The event which is being checked for conflicts with this Workshop
     * @returns whether or not there is a conflict (true or false)
     */
    public boolean conflictsWith(Event event) {
        return !(event instanceof Talk);
    }

}
