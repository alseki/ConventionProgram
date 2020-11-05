package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 5 2020
// This contains the data used in the EventManager Facade pattern. It contains basic methods to access information
// in Events

class EventDB {
    Event[] events;

    protected EventDB (Event[] events) {
        this.events = events;
    }

    protected Event getEvent() {//should take some criteria
        return null; // Should return events based on the input given
        // may require multiple search methods depending on way of searching? unsure
    }

    protected Event[] getEventList() {
        return events;
    }
}
