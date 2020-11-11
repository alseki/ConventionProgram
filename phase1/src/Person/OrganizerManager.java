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
     *sets the currentOrganizer to the organizer with the userName </userName>, if there exists an organizer
     * with such a userName.
     * @param userName the username of the organizer.
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
     * It also adds to the persons lists. it also updates the username to organizer map.
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

    public boolean addEvent(String eventId){
        if (!currentOrganizer.getEventsSignedUp().contains(eventId)){
            currentOrganizer.signUp(eventId);
            return true;
        }
        return false;
    }

    public boolean removeEvent(String eventId){
        if (currentOrganizer.getEventsSignedUp().contains(eventId)){ // does this make problems with extendability
            // becauuse i'm using the fact that it's a list implemenation
            currentOrganizer.cancelSpot(eventId);
            return true;
        }
        return false;
    }

    public Organizer getCurrentOrganizer(){
        return currentOrganizer;
    }
    public boolean  updateContactList(String username){
        if(!currentOrganizer.getContactList().contains(username)) {
            currentOrganizer.addContactList(username);
            return true;
        }
        return false;
    }
    private void updateUsernameToOrganizer(String username, Organizer org){
        usernameToOrganizer.put(username,org);
    }
    private void updateAllPersons(Organizer org){
        allPersons.add(org);
    }


}
