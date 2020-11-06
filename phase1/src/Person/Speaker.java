package Person;

import Person.Person;

import java.util.ArrayList;
import java.util.UUID;

public class Speaker extends Person { //implements Person.Signupable
    //private ArrayList<String> eventsSignedUp;
    private ArrayList<String> eventSpeakingAt;

    Speaker(String username, String password, String fullName, String email){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.id = UUID.randomUUID().toString();
    }
//
//    /**
//     * returns the events that the Person.Speaker has signed up for in the capacity of an attendee
//     * @return ArrayList<String> eventsSignedUp the events that the person has signed up for.
//     */
//    public ArrayList<String> getEventsSignedUp(){
//        return eventsSignedUp;
//    }
    // notice that a speaker cannot sign up for an event.

    /**
     * returns the events where the Person.Speaker is to give a Talk
     * @return ArrayList<String> eventsSpeakingAt the events where the Person.Speaker has been signed up for (by event organizers)
     */

    public ArrayList<String> getEventSpeakingAt() {
        return eventSpeakingAt;
    }

    /**
     * returns the contacts that Person.Speaker has in list
     * @return ArrayList<String> of person's id
     */
    protected ArrayList<String> contactList() {
        return contactList;
    }
    // why is this protected.
}



