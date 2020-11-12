package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 12 2020
// Part of the EventManager Facade pattern. Contains basic access methods used by Attendees.

class EventSignupManager {

    EventDB events;

    protected EventSignupManager(EventDB events) {
        this.events = events;
    }

    protected boolean signUpForEvent(String personID, String eventID) {
        Event event = events.getEvent(eventID);
        if (event != null) {
            event.addAttendee(personID);
            return true;
        }
        else {
            return false;
        }
    }

    protected boolean removeFromEvent(String personID, String eventID) {
        Event event = events.getEvent(eventID);
        if (event != null) {
            event.removeAttendee(personID);
            return true;
        }
        else {
            return false;
        }
    }
}
