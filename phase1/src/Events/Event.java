package Events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.Duration;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 15 2020

// Architecture Level - Entity

public abstract class Event implements Serializable {

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String ID;
    private String speakerID;
    private ArrayList<String> attendees;
    private String chatID;
    private String password;

    /**
     * Constructor for Event objects
     * @param name The Event's name
     * @param startTime The time when the Event starts
     * @param endTime The time when the Event ends
     */
    protected Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        ID = UUID.randomUUID().toString();
        speakerID = null;
        attendees = new ArrayList<String>();
        this.chatID = UUID.randomUUID().toString();
        this.password = UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Getter for the name of this Event
     * @return the name of the Event (as a string)
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of this Event
     * @param name The new name of this Event
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the start time of this Event
     * @return the start time of this Event
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Setter for the start time of this Event
     * @param startTime The new start time of this Event
     */
    protected void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for the end time of this Event
     * @return the end time of this Event
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Setter for the end time of this Event
     * @param endTime The new start time of this Event
     */
    protected void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter for the time (in hours) that this Event will run
     * @return the duration of this Event (as an int)
     */
    public int getDuration() {
        Duration diff = Duration.between(this.startTime, this.endTime);
        return (int) diff.toHours();
    }

    /**
     * Getter for the ID of this Event
     * @return the ID of the event
     */
    public String getID() {
        return ID;
    }

    /**
     * Getter for the speaker running this Event
     * @return an array of IDs corresponding to speakers in the system
     */
    public String getSpeaker() {
        if (speakerID == null) {
            return "";
        }
        else{
            return speakerID;
        }
    }

    /**
     * Setter for the speaker running this Event
     * @param id The ID of the new speaker
     */
    protected void setSpeakerID(String id) {
        speakerID = id;
    }

    /**
     * Getter for the attendees attending this Event
     * @return an array of these attendees
     */
    public ArrayList<String> getAttendeeIDs() {
        return attendees;
    }

    /**
     * Adds an attendee to this Event's attendee list
     * @param ID The ID of the new attendee
     */
    public void addAttendee(String ID) {
        attendees.add(ID);
    }

    /**
     * Removes an attendee from this Event's attendee list
     * @param ID The ID of the attendee being removed
     */
    public void removeAttendee(String ID) {
        attendees.remove(ID);
    }

    /**
     * Getter for this Event's chatID
     * @return String representing the Event's chatID
     */
    public String getChatID(){
        return this.chatID;
    }

    /**
     * Setter for this Event's chatID
     * @param ID The new chatID for this Event
     */
    public void setChatID(String ID){
        this.chatID = ID;
    }

    /**
     * Getter for this Event's password
     * @return String representing the Event's password
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * A textual representation of this event
     * @return a description of this event in the form of a String
     */
    protected abstract String getDescription();

    @Override
    public String toString() {
        return getDescription();
    }

    /**
     * Returns whether or not another Event conflicts with this one.
     * @param event The other Event. (NOTE: the other event is presumed to be in the same Room.)
     * @return True or false.
     */
    protected abstract boolean conflictsWith(Event event);

}