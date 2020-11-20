package Person;

import java.util.ArrayList;
import java.util.UUID;

public class Attendee extends Person implements Signupable {

    protected ArrayList<String> eventsSignedUp = new ArrayList<>();
    protected ArrayList<String> chatList = new ArrayList<>();
    protected ArrayList<String> anChatList = new ArrayList<>();


    public Attendee (String fullName, String username, String password, String email){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * adds an event ID to the Person.Person.Attendee's list of signed up Events
     * @param eventID takes in the ID of the event
     */
    public void signUp(String eventID) {
        this.eventsSignedUp.add(eventID);
    }

    /**
     * removes an event ID from the Person.Person.Attendee's list of signed up Events
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
     * returns the contacts that Person.Person.Attendee has in list
     * @return ArrayList<String> of person's id
     */
    public ArrayList<String> getContactList() { // why does this need to be overriden shouldn't the one in person suffice?
        return this.contactList;
    }

    @Override
    public ArrayList<String> getChatList() {
        return chatList;
    }

    public ArrayList<String> getAnChatList() {
        return anChatList;
    }

    @Override
    public void addChat(String chatID) {
        chatList.add(chatID);
    }

    public void addAnChat(String chatID) {
        anChatList.add(chatID);
    }

    @Override
    public void removeChat(String chatID) {
        if (chatList.contains(chatID)) {
            chatList.remove(chatID);
        }
    }

    public void removeAnChat(String chatID) {
        if (anChatList.contains(chatID)) {
            anChatList.remove(chatID);
        }
    }

}
