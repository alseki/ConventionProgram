package Person;

import java.util.ArrayList;
import java.util.Map;

public class OrganizerManager extends PersonManager {
    private ArrayList<Organizer> organizerList;
    private Organizer currentOrganizer;
    private Map usernameToOrganizer;
    OrganizerManager(String organizerName){
        organizerList  = new ArrayList<Organizer>();
    }
    public boolean createOrganizer(){ //waiting for builder add organizer to list update map
        return true;
    }

    public ArrayList<Organizer> getAttendeeList() {
        return organizerList;
    }

    public boolean createSpeaker() { // waiting for person factory
        return true;
    }
    public boolean updateEventSignups(){ // updates the events the current organizer is signed up fro

    }
    public String getAttribute(String attribute){ // see if there is a way to be able to do this.

    }

    public void updateCurrentOrganizer(String Username){ // use the map to update the person

    }
    public void updateContactList(String Username){
        currentOrganizer.getContactList().add(Username);
    }

}
