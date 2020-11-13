package Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrganizerManager extends PersonManager {
    //private ArrayList<Organizer> organizerList;
    //private Organizer currentOrganizer;
    //private Map<String, Organizer> usernameToOrganizer;

    public OrganizerManager(){
        super();
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
        if(!usernameToPerson.containsKey(username)) {
            Organizer og = new Organizer(fullName, username, password, email);
            updateAllPersons(og); // see below
            updateUsernameToPerson(og.getUsername(), og); // see below
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
        if (!og.getEventsSignedUp().contains(eventId)){
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
        if (og.getEventsSignedUp().contains(eventId)){
            og.cancelSpot(eventId);
            return true;
        }
        return false;
    }


    /**
     * adds the contact with username </username> to the organizer with userId </userid> to ehir contanct
     * @param contactId a string representing the contactId of the contact to be added to the userid contactlist
     * @return true iff username is not currently in the contact list
     */
    public boolean  updateContactList(String contactId, String userId){
        Organizer og = (Organizer)idToPerson.get(userId);
        if(!og.getContactList().contains(contactId)) {
            og.addContactList(contactId);
            return true;
        }
        return false;
    }

    /**
     * returns the current organizer's list of contancts
     * @return ArrayList</String> representing the organizer id contact list
     */
    @Override
    public ArrayList<String> getContactList(String organizerId){
        Organizer og = (Organizer)usernameToPerson.get(organizerId);
        return og.getContactList();
    }

    public ArrayList<String> getSignedUpForEvents(String organizerId){
        return idToPerson.get(organizerId).getEventList();
    }


    private void updateUsernameToPerson(String username, Organizer org){
        usernameToPerson.put(username,org);
    }

    private void updateAllPersons(Organizer org){
        allPersons.add(org);
    }


}
