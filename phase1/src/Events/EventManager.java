package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 14 2020

// Architecture level - Use class

// Note that this is a Facade for event management methods, and the objects inside of it may change as we figure out
// which actors are going to be using which ones.

import Message.MessageManager;

public class EventManager {
   // EventSignupManager eventSignup;
   // EventAccessManager eventAccess;
   // EventAdminManager eventAdmin;
    EventDB events;
    Room room;

    /**
     * Creates a new EventManager with events read in by some sort of EventReader
     * @param room The EventReader used to read in events
     */
    public EventManager(Room room) {
        this.room = room;
    }

    


    /**
     * Returns information about the entire list of events
     * @return The list of events
     */
    public Event[] getEventList() {
        return events.getEventList();
    }

    /**
     * Returns the ID of an event given its name
     * @param name The event's name
     * @return The ID
     */
    public String getEventID(String name) {
        return events.getEventID(name);
    }




    /**
     * Adds an event
     * @param event The event
     * @return Whether the event has been successfully added
     */
    public boolean addEvent(Event event) {

        // check conflicts! also possibly edit so as to create the event itself?

        return events.addEvent(event);
    }

    /**
     * Deletes an event
     * @param id The event's ID
     * @return Whether the event has been successfully deleted
     */
    public boolean removeEvent(String id) {
        return events.addEvent(events.getEvent(id));
    }




    /**
     * Signs an individual attendee up for an event
     * @param personID The attendee
     * @param eventID The event
     */
    public boolean signUpForEvent(String personID, String eventID) {
            Event event = events.getEvent(eventID);

            // check + update capacity!

            if (event != null) {
                event.addAttendee(personID);
                return true;
            }
            else {
                return false;
            }
    }

    /**
     * Takes an individual attendee off an event's attendee list
     * @param personID The attendee
     * @param eventID The event
     */
    public boolean removeFromEvent(String personID, String eventID) {
        // update capacity!

        Event event = events.getEvent(eventID);
        if (event != null) {
            event.removeAttendee(personID);
            return true;
        }
        else {
            return false;
        }
    }



    public boolean announceToEvent(Event event) {
        return false; //get messages
    }

    public MessageManager getMessages(Event event) {
        return null; //get messages
    }
}
