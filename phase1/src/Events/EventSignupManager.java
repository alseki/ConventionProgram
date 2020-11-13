package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 12 2020
// Architecture level - Use class
// Part of the EventManager Facade pattern. Contains basic access methods used by Attendees.

import Message.MessageManager;

class EventSignupManager {

    EventDB events;

    protected EventSignupManager(EventDB events) {
        this.events = events;
    }

    protected boolean signUpForEvent(String personID, String eventID) {
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

    protected boolean removeFromEvent(String personID, String eventID) {

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

    protected MessageManager getMessages(Event event) {
        return null; // return a MessageManager of this Event's messages
    }
}
