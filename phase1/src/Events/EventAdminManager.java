package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 12 2020

// Architecture level - Use class

// Part of the EventManager Facade pattern. Contains basic access methods used by Organizers.

public class EventAdminManager {

    EventDB events;

    protected EventAdminManager(EventDB events) {
        this.events = events;
    }

    // edit so it creates the event?
    protected boolean addEvent(Event event) {

        // check conflicts!

        return events.addEvent(event);
    }
    protected boolean removeEvent(String eventID) {
        return events.addEvent(events.getEvent(eventID));
    }
}
