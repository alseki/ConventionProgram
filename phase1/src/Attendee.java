import java.util.ArrayList;
import java.util.UUID;

public class Attendee extends Person implements Signupable {

    protected ArrayList<String> eventsSignedUp;

    public Attendee (String fullName, String username, String password, String email){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * adds an event ID to the Attendee's list of signed up Events
     * @param eventID takes in the ID of the event
     */
    public void signUp(String eventID) {
        eventsSignedUp.add(eventID);
    }

    /**
     * removes an event ID from the Attendee's list of signed up Events
     * @param eventID takes in the ID of the event
     */
    public void cancelSpot(String eventID) {
        eventsSignedUp.remove(eventID);
    }

    /**
     * returns the events that the attendee has signed up for
     * @return ArrayList<String> eventsSignedup the events that the person has signed up for.
     */
    public ArrayList<String> getEventsSignedUp(){
        return eventsSignedUp;
    }

    /**
     * returns the contacts that Attendee has in list
     * @return ArrayList<String> of person's id
     */
    public ArrayList<String> getContactList() {
        return contactList;
    }

}
