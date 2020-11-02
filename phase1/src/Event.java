public abstract class Event {

    private String name;
    private int room;
    private int duration;

    // implement messages + attendees once messageManager and attendeeManager exist

    protected Event(String name, int room, int duration) {
        this.name = name;
        this.room = room;
        this.duration = duration;
    }

    /**
     * Getter for the name of this Event
     * @return the name of the Event (as a string)
     */
    public String getName() {
        return name;
    }


    /**
     * Getter for the room ID of this Event
     * @return the room ID of the Event (as an int)
     */
    public  int getRoomID() {
        return room;
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
    public Object getAnnouncements() {
        return null;
    }

    /**
     * Getter for the attendees attending this Event
     * @return an AttendeeManager object containing the aforementioned attendees
     */

    public Object getAttendeeIDs() {
        return null;
    }

    /**
     * Getter for the speaker(s) running this Event
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
}
