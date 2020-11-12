package Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrganizerManager extends PersonManager {
    //private ArrayList<Organizer> organizerList;
    private Organizer currentOrganizer;
    private Map<String, Organizer> usernameToOrganizer;

    public OrganizerManager(){
        super();
        usernameToOrganizer = new HashMap<>();
    }

    /**
     * sets the currentOrganizer to the organizer with the userName </userName>, if there exists an organizer
     * with such a userName.
     * @param userName a String representing the username of the organizer.
     * @return True iff the current organizer is set to the organizer
     */
    public boolean setCurrentOrganizer(String userName){
        if (usernameToOrganizer.containsKey(userName)){
            currentOrganizer = usernameToOrganizer.get(userName);
            return true;
        }
        return false;
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
        if(!usernameToOrganizer.containsKey(username)) {
            Organizer og = new Organizer(fullName, username, password, email);
            updateAllPersons(og);
            updateUsernameToOrganizer(og.getUsername(), og);
            return true;
        }

        return false;

    }

    /**
     * if the the currentOrganizer is not signed up for event with eventID then the organizer will be signedup
     * @param eventId a string representing the eventID
     * @return true iff the currentorganizer gets signed up for the event.
     */

    public boolean addEvent(String eventId){
        if (!currentOrganizer.getEventsSignedUp().contains(eventId)){
            currentOrganizer.signUp(eventId);
            return true;
        }
        return false;
    }

    /**
     * if the currentorganizer is currently signedup for the event then they will be unsigned up for the event
     * @param eventId string representing the event ID
     * @return true iff they are removed from the event
     */

    public boolean removeEvent(String eventId){
        if (currentOrganizer.getEventsSignedUp().contains(eventId)){ // does this make problems with extendability
            // becauuse i'm using the fact that it's a list implemenation
            currentOrganizer.cancelSpot(eventId);
            return true;
        }
        return false;
    }

    /**
     * getter for the current organizer
     * @return currentorganizer an organizer object representing the current organizer
     */
    public Organizer getCurrentOrganizer(){
        return currentOrganizer;
    }

    /**
     * adds the contact with username </username> to the currentorganizer contacts
     * @param username a string representing the username of the contact to be added to the currentorganizer contactlist
     * @return true iff username is not currently in the contact list
     */
    public boolean  updateContactList(String username){
        if(!currentOrganizer.getContactList().contains(username)) {
            currentOrganizer.addContactList(username);
            return true;
        }
        return false;
    }

    /**
     * returns the current organizer's list of contancts
     * @return ArrayList</String> representing the currentorganizer contact id's
     */
    @Override
    public ArrayList<String> getContactList(String organizerId){
        Organizer og = usernameToOrganizer.get(organizerId);
        return og.getContactList();
    }

    private void updateUsernameToOrganizer(String username, Organizer org){
        usernameToOrganizer.put(username,org);
    }
    private void updateAllPersons(Organizer org){
        allPersons.add(org);
    }


}
