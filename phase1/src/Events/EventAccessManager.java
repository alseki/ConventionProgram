package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 5 2020
// Part of the EventManager Facade pattern.

class EventAccessManager {

    EventDB events;

    protected EventAccessManager(EventDB events) {
        this.events = events;
    }

    protected Object getEventList() {
        return  null; //returns some sort of eventlist
    }
}
