package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 10 2020
// Note that this is a Facade for event management methods, and the objects inside of it may change as we figure out
// which actors are going to be using which ones

public class EventManager {
    EventSignupManager eventSignup;
    EventAccessManager eventAccess;
    EventDB events;


    /**
     * Creates a new EventManager with events read in by some sort of EventReader
     * @param reader The EventReader used to read in events
     */
    public EventManager(EventReader reader) {
        events = new EventDB(reader.readEvents());
        eventSignup = new EventSignupManager(events);
        eventAccess = new EventAccessManager(events);
    }

    /**
     * Returns information about the entire list of events
     * @return The list of events
     */
    public Object getEventList() {
        return eventAccess.getEventList();
    }

    /**
     * Signs an individual attendee up for an event
     * @param person The attendee
     * @param event The event
     */
    public void signUpForEvent(Object person, Object event) {
        eventSignup.signUpForEvent(person, event);
    }

    /**
     * Takes an individual attendee off an event's attendee list
     * @param person The attendee
     * @param event The event
     */
    public void removeFromEvent(Object person, Object event) {
        eventSignup.removeFromEvent(person, event);
    }

    /**
     * Returns the ID of an event given its name
     * @param name The event's name
     * @return The ID
     */
    public String getEventID(String name) {
        return eventAccess.getEventID(name);
    }
}
