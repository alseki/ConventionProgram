package Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// There are no events for which a Speaker can sign for in the manner of an Attendee; nor can a Speaker register
// himself or herself for an Event. This can be done exclusively by an Organizer.

public class Speaker extends Person {

    /** A list of the IDs for all Events where this Speaker is speaking. */
    protected ArrayList<String> allTalksID = new ArrayList<>();

    /** A mapping of the IDs for all Events where this Speaker is speaking to the names of those Events. */
    protected Map<String, ArrayList<String>> allTalksDictionary = new HashMap<String, ArrayList<String>>();

    /** A list of all announcement chatIDs for this Speaker. */
    protected ArrayList<String> announcementChatIds = new ArrayList<>();

    Speaker(String fullName, String username, String password, String email){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.id = UUID.randomUUID().toString();
        this.typePerson = 3;
    }

    /**
     * Adds the ID of an Event to the Speaker's list of Events
     * @param eventID the ID of the Event
     */
    public void signUp(String eventID) {
        this.allTalksID.add(eventID);
    }

    /**
     * Returns the Events that the Speaker has been signed up for by the Organizer
     * @return the IDs of the Events that the Speaker has signed up for
     */
    public ArrayList<String> getAllTalks(){
        return allTalksID;
    }

    /**
     * Getter for the Events that the Speaker has been signed up for by the Organizer
     * @return a mapping of the IDs for all Events where this Speaker is speaking to the names of those Events
     */
    public Map getAllTalksDictionary() {
        return allTalksDictionary;
    }

    /**
     * Getter for the IDs of announcement chats that this Speaker has made
     * @return a list of all announcement chatIDs for this Speaker
     */
    public ArrayList<String> getAnnouncementChats() {
        return this.announcementChatIds;
    }

}



