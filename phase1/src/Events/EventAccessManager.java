package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 12 2020

// Architecture level - Use class

// Part of the EventManager Facade pattern. Contains basic access methods used by multiple actors.

class EventAccessManager {

    EventDB events;

    protected EventAccessManager(EventDB events) {
        this.events = events;
    }

    protected Event[] getEventList() {
        Event[] eventArray = {};
        return events.getEventList().toArray(eventArray);
    }

    protected String getEventID(String name) {
        return events.getEventID(name);
    }
}
