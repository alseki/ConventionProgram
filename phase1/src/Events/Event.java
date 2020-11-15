package Events;

import Message.Message;

import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.Duration;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 14 2020

// Architecture Level - Entity

public abstract class Event {

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String ID;
    private String speakerID;
    private ArrayList<String> attendees;
    private ArrayList<String> messages;
    private String chatId;

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
        attendees = new ArrayList<String>();
        messages = new ArrayList<String>();
        this.chatId = UUID.randomUUID().toString();
    }

    /**
     * a getter for this event's chat IDr
     * @return String representing the event Chat Id
     */
    public String getChatId(){
        return this.chatId;
    }

    /**
     * Getter for the ID of this Event
     * @return the ID of the event
     */
    public String getID() {
        return ID;
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
     * Getter for the announcements associated with this Event
     * @return an array of these announcements
     */
    public Message[] getAnnouncements() {
        Message[] messageArray = {};
        return messages.toArray(messageArray);
    }

    /**
     * Getter for the attendees attending this Event
     * @return an array of these attendees
     */
    public String[] getAttendeeIDs() {
        String[] attendeeArray = {};
        return attendees.toArray(attendeeArray);
    }

    /**
     * Adds an attendee to this event's attendee list
     * @param id The ID of the new attendee
     */
    public void addAttendee(String id) {
        attendees.add(id);
    }

    /**
     * Removes an attendee from this event's attendee list
     * @param id The ID of the attendee being removed
     */
    public void removeAttendee(String id) {
        attendees.remove(id);
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


