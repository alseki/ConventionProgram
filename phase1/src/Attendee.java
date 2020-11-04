import java.util.ArrayList;
import java.util.UUID;

public class Attendee extends Person { //implements Signupable

    private ArrayList<String> eventsSignedUp;

    public Attendee (String username, String password, String fullName, String email){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * returns the events that the attendee has signed up for
     * @return ArrayList<String> eventsSignedup the events that the person has signed up for.
     */
    public ArrayList<String> getEventsSignedUp(){
        return eventsSignedUp;
    }

    /**
     * returns the contacts that Speaker has in list
     * @return ArrayList<String> of person's id
     */
    public ArrayList<String> contactList() {
        return contactList;
    }

}
