package Events;

import Message.Message;

import java.util.ArrayList;
import java.util.UUID;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 12 2020

// Architecture level - Entity

public abstract class Event {

    private String name;
    private int startTime;
    private int duration;
    private String ID;
    private String speakerID;
    private ArrayList<String> attendees;
    private ArrayList<String> messages;

    /**
     * Constructer for Event objects
     * @param name The Event's name
     * @param startTime The time the Event starts
     * @param duration The length of the Event
     */
    protected Event(String name, int startTime, int duration) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
        ID = UUID.randomUUID().toString();
        attendees = new ArrayList<String>();
        messages = new ArrayList<String>();
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
     * Getter for the start time (in hours) of this Event
     * @return the duration of this Event (as an int)
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Getter for the time (in hours) that this Event will run
     * @return the duration of this Event (as an int)
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Getter for the announcements associated with this Event
     * @return a MessageManager object containing the aforementioned announcements
     */
    public Message[] getAnnouncements() {
        Message[] messageArray = {};
        return messages.toArray(messageArray);
    }

    /**
     * Getter for the attendees attending this Event
     * @return An array of the aforementioned attendees
     */
    public String[] getAttendeeIDs() {
        String[] attendeeArray = {};
        return attendees.toArray(attendeeArray);
    }

    /**
     * Adds an attendee to this event's attendee list
     * @param id The ID of the attendee
     */
    public void addAttendee(String id) {
        attendees.add(id);
    }

    /**
     * Removes an attendee from this event's attendee list
     * @param id The ID of the attendee
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
     * Getter for the speaker running this Event
     * @return an array of IDs corresponding to speakers in the system
     */
    public void setSpeakerID(String id) {
        speakerID = id;
    }

    /**
     * A textual representation of this event
     * @return a description of this event in the form of a String
     */
    public abstract String getDescription();

    /**
     * Returns whether or not another Event conflicts with this one.
     * @param event The other Event. (NOTE: the other event is presumed to be in the same Room.)
     * @return True or false.
     */
    public abstract boolean conflictsWith(Event event);
}

