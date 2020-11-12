package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 5 2020
// Note that this is a Facade for event management methods, and the objects inside of it may change as we figure out
// which actors are going to be using which ones

public class EventManager {
    EventSignupManager eventSignup;
    EventAccessManager eventAccess;
    EventDB events;


    public EventManager(EventReader reader) {
        events = new EventDB(reader.readEvents());
        eventSignup = new EventSignupManager(events);
        eventAccess = new EventAccessManager(events);
    }

    public Object getEventList() {
        return eventAccess.getEventList();
    }

    public void signUpForEvent(Object person, Object event) {
        eventSignup.signUpForEvent(person, event);
    }

    public void removeFromEvent(Object person, Object event) {
        eventSignup.removeFromEvent(person, event);
    }
}
