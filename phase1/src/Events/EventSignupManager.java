package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 5 2020
// Part of the EventManager Facade pattern.

class EventSignupManager {

    EventDB events;

    protected EventSignupManager(EventDB events) {
        this.events = events;
    }

    protected void signUpForEvent(Object person, Object event) {
        // signup code
    }

    protected void removeFromEvent(Object person, Object event) {
        // removal code
    }
}
