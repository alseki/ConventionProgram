package Event;

import java.util.ArrayList;
import java.time.LocalDateTime;

// Contributors: Eytan Weinstein
// Last edit: Dec 6 2020

// Architecture Level - Entity

public class Panel extends Event {

    // A Panel is a discussion on a particular topic that takes place between Speakers, and (optionally) in front of
    // other observing Attendees who are not participating in the Panel discussion. It cannot cannot take place in the
    // same Room as any other Event while that Event is occurring.

    private ArrayList<String> speakers;

    /**
     * Constructor for Panel objects
     * @param name The Panel's name
     * @param startTime The time when the Panel starts
     * @param endTime The time when the Panel ends
     * @param description The description of this Panel
     * @param capacity The capacity of this Panel
     */
    public Panel (String name, LocalDateTime startTime, LocalDateTime endTime, String description, int capacity) {
        super(name, startTime, endTime, description, capacity);
        this.speakers = new ArrayList<String>();
    }

    /**
     * Checks if the inputted Event conflicts in time with this Panel
     * @param event The Event which is being checked for conflicts with this Panel
     * @return whether or not there is a conflict (true or false)
     */
    public boolean conflictsWith(Event event) {
        return this.getStartTime().isBefore(event.getEndTime()) && event.getStartTime().isBefore(this.getEndTime());
    }

    /**
     * Getter for the IDs of the Speakers participating in this Panel
     * @return an array of these Speaker IDs
     */
    public ArrayList<String> getSpeakerIDs() {
        return this.speakers;
    }

    /**
     * Adds a Speaker to this Panel's list of Speakers
     * @param ID The ID of the new Speaker
     */
    public void addSpeaker(String ID) {
        this.speakers.add(ID);
    }

    /**
     * Removes a Speaker from this Panel's list of Speakers
     * @param ID The ID of the Speaker being removed
     */
    public void removeSpeaker(String ID) {
        this.speakers.remove(ID);
    }

}
