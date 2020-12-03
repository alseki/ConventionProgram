package Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Attendee extends Person implements Signupable, PropertyChangeListener {

    protected ArrayList<String> eventsSignedUp = new ArrayList<>();
    protected ArrayList<String> anChatList = new ArrayList<>();
    private Map<String, Boolean> requestIdToStatus;

    public Attendee (String fullName, String username, String password, String email){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = UUID.randomUUID().toString();
        this.typePerson = 1;
        this.requestIdToStatus = new HashMap<>();
    }

    /**
     * adds an event ID to the attendee's list of signed up Event
     * @param eventID takes in the ID of the event
     */
    public void signUp(String eventID) {
        this.eventsSignedUp.add(eventID);
    }

    /**
     * removes an event ID from the attendee's list of signed up Event
     * @param eventID takes in the ID of the event
     */
    public void cancelSpot(String eventID) {
        this.eventsSignedUp.remove(eventID);
    }

    /**
     * returns the events that the attendee has signed up for
     * @return ArrayList<String> eventsSignedup the events that the person has signed up for.
     */
    public ArrayList<String> getEventsSignedUp(){
        return this.eventsSignedUp;
    }

    /**
     * returns the contacts that the attendee has in list
     * @return ArrayList<String> of person's id
     */
    public ArrayList<String> getContactList() { // why does this need to be overriden shouldn't the one in person suffice?
        return this.contactList;
    }

    public ArrayList<String> getAnChatList() {
        return anChatList;
    }

    public void addAnChat(String chatID) {
        anChatList.add(chatID);
    }

    public void removeAnChat(String chatID) {
        if (anChatList.contains(chatID)) {
            anChatList.remove(chatID);
        }
    }
    public String getRequestStatus(String requestId){
        if (!requestIdToStatus.get(requestId)){
            return "Pending";
        }
        else{
            return "Fulfilled";
        }
    }
    public void newRequest(String requestId){
        requestIdToStatus.put(requestId, false);
    }

    // @Override
    public void propertyChange(PropertyChangeEvent evt) { // should this be in use case as it's modifying the variables?
        this.requestIdToStatus.replace(evt.getPropertyName(),(boolean)evt.getNewValue());

    }

}
