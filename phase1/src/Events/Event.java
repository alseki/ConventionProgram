package Events;

import java.util.UUID;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 5 2020

public abstract class Event {

    private String name;
    private int room;
    private int duration;
    private String ID;

    // implement messages + attendees once messageManager and attendeeManager exist

    protected Event(String name, int room, int duration) {
        this.name = name;
        this.room = room;
        this.duration = duration;
        ID = UUID.randomUUID().toString();
    }

    /**
     * Getter for the name of this Events.Event
     * @return the name of the Events.Event (as a string)
     */
    public String getName() {
        return name;
    }


    /**
     * Getter for the room ID of this Events.Event
     * @return the room ID of the Events.Event (as an int)
     */
    public  int getRoomID() {
        return room;
    }

    /**
     * Getter for the time (in hours) that this Events.Event will run
     * @return the duration of this Events.Event (as an int)
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Getter for the announcements associated with this Events.Event
     * @return a Message.MessageManager object containing the aforementioned announcements
     */
    public Object getAnnouncements() {
        return null;
    }

    /**
     * Getter for the attendees attending this Events.Event
     * @return an Person.Person.AttendeeManager object containing the aforementioned attendees
     */

    public Object getAttendeeIDs() {
        return null;
    }

    /**
     * Getter for the speaker(s) running this Events.Event
     * @return an array of IDs corresponding to speakers in the system
     */
    public abstract String[] getSpeakers();

    /**
     * A textual representation of this event
     * @return a description of this event in the form of a String
     */
    public String getDescription() {
        return name + ", in Room " + room;
    }

    /**
     * Getter for the ID of this Events.Event
     * @return the ID of the event
     */
    public String getID() {
        return ID;
    };
}

