package Person;

import java.util.ArrayList;
import java.util.Map;

public class OrganizerManager extends PersonManager{

    /**
     * constructor for OrganizerManager
     * @param usernameToPerson a map of String linking username to organizer object
     * @param idToPerson a map of String linking id to ogranizer object
     */

    // dictionary of all organizers; this is under the theme of having an short list for organizers to looks up each other
    protected ArrayList usernameToOrganizer = new ArrayList<>();
    protected ArrayList userIdToOrganizer = new ArrayList<>();

    public OrganizerManager(Map<String, Person> usernameToPerson, Map<String, Person> idToPerson) {
        super(usernameToPerson, idToPerson);

    }

    public OrganizerManager(Map<String, Person> usernameToPerson, Map<String, Person> idToPerson, boolean firstLogIn) {
        super(usernameToPerson, idToPerson);
        if (firstLogIn) {
            firstLogin();
        }

    }


    /**
     * checks to see if there exists an organizer with this username and if there doesn't creates a new organize object
     * It also adds to the persons lists. it also updates the username to organizer map which is private.
     * @param fullName name of the Organizer
     * @param username name of the organizer
     * @param password password of the organizer
     * @param email of the organizer
     * @return true iff it creates the organizer account
     */
    @Override
    public boolean createAccount(String fullName, String username, String password, String email){
        // will change this if we make some errors.
        if(!usernameToPerson.containsKey(username)) { // check that there is not someone with that username
            Organizer og = new Organizer(fullName, username, password, email);
            updateUsernameToPerson(og.getUsername(), og); // see below
            idToPerson.put(og.getID(), og);
            userIdToOrganizer.add(og.getID());
            usernameToOrganizer.add(og.getID());
            return true;
        }

        return false;

    }

    /**
     * if the the currentOrganizer is not signed up for event with eventID then the organizer will be signedup
     * @param eventId a string representing the eventID
     * @return true iff the currentorganizer gets signed up for the event.
     */

    public boolean addEvent(String eventId, String userId){
        Organizer og = (Organizer)idToPerson.get(userId);
        if (!og.getEventsSignedUp().contains(eventId)){ // checking that they are not currently signed up for the event
            og.signUp(eventId);
            return true;
        }
        return false;
    }

    /**
     * if the the organizer with </userId> is currently signedup for the event then they will be unsigned up for the event
     * @param eventId string representing the event ID
     * @return true iff they are removed from the event
     */

    public boolean cancelEvent(String userId, String eventId){
        Organizer og = (Organizer)idToPerson.get(userId);
        if (og.getEventsSignedUp().contains(eventId)){ // check that they are currently signed up fro the event
            og.cancelSpot(eventId);
            return true;
        }
        return false;
    }

    /**
     *
     * @param username String representing the username
     * @return returns an int depending on the type of username
     */

    public int confirmOrganizer(String username){
        if (usernameToPerson.containsKey(username)) {
            return getPersonByUsername(username).getTypePerson();
        }
        return -1;
    }


    /** a getter for the signedup events
     * @param organizerId a string representing the organizer id
     * @return an ArrayList</String> representing the id's of the lists
     */
    public ArrayList<String> getSignedUpForEvents(String organizerId){
        return idToPerson.get(organizerId).getEventList();
    }


    private void updateUsernameToPerson(String username, Organizer org){
        usernameToPerson.put(username,org);
    }

    public ArrayList getOrganizerOnlyMapByID(String userID){
        return userIdToOrganizer;
    }

    public ArrayList getOrganizerOnlyMapByUsername(String userID){
        return userIdToOrganizer;
    }
    private void firstLogin() {
        createAccount("admin", "admin", "admin", null);
    }


}
