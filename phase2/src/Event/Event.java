package Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDateTime;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Dec 5 2020

// Architecture Level - Entity

public abstract class Event implements Serializable {

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private String ID;
    private String password;
    private String chatID;
    private ArrayList<String> attendees;

    /**
     * Constructor for Event objects
     * @param name The Event's name
     * @param startTime The time when the Event starts
     * @param endTime The time when the Event ends
     * @param description A description for this Event
     */
    protected Event(String name, LocalDateTime startTime, LocalDateTime endTime, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        ID = UUID.randomUUID().toString();
        this.password = UUID.randomUUID().toString().replace("-", "");
        this.chatID = UUID.randomUUID().toString();
        attendees = new ArrayList<String>();
    }

    /**
     * Getter for the name of this Event
     * @return the name of the Event (as a String)
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of this Event
     * @param name The new name of this Event
     */
    public void setName(String name) {
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
    public void setStartTime(LocalDateTime startTime) {this.startTime = startTime;}

    /**
     * Getter for the end time of this Event
     * @return the end time of this Event
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Setter for the end time of this Event
     * @param endTime The new end time of this Event
     */
    public void setEndTime(LocalDateTime endTime) {this.endTime = endTime;}

    /**
     * A getter for the description of this Event
     * @return a description of this Event (as a String)
     */
    public String getDescription() {return this.description;};

    /**
     * Setter for the description of this Talk
     * @param description A new description for this Talk
     */
    protected void setDescription(String description){
        this.description = description;
    }

    /**
     * Getter for the ID of this Event
     * @return the ID of the event
     */
    public String getID() {
        return ID;
    }

    /**
     * Getter for this Event's password
     * @return String representing the Event's password
     */
    public String getPassword(){
        return this.password;
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
     * Getter for the Attendees attending this Event
     * @return an array of these Attendees
     */
    public ArrayList<String> getAttendeeIDs() {
        return attendees;
    }

    /**
     * Adds an Attendee to this Event's list of Attendees
     * @param ID The ID of the new Attendee
     */
    public void addAttendee(String ID) {
        attendees.add(ID);
    }

    /**
     * Removes an Attendee from this Event's list of Attendees
     * @param ID The ID of the Attendee being removed
     */
    public void removeAttendee(String ID) {
        attendees.remove(ID);
    }

    /**
     * A textual representation of this event
     * @return a description of this event (as a String)
     */
    @Override
    public String toString() {
        return "Title: " + getName() + "\n" + getDescription();
    }

    /**
     * Returns whether or not another Event conflicts with this one.
     * @param event The other Event. (NOTE: the other event is presumed to be in the same Room.)
     * @return True or false.
     */
    protected abstract boolean conflictsWith(Event event);

}