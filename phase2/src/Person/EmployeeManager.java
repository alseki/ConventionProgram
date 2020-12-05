package Person;

import java.util.ArrayList;
import java.util.Map;

public class EmployeeManager extends PersonManager {

    // This account is created by organizer or administrator

    // private List<Attendee> allAttendees;
    private ArrayList<String> requestIds;

    public EmployeeManager(Map<String, Person> usernameToPerson, Map<String, Person> idToPerson) {
        super(usernameToPerson, idToPerson);
        this.requestIds = new ArrayList<String>();
    }



    @Override
    public boolean createAccount(String name, String username, String password, String email) {
        if (!usernameToPerson.containsKey(username)) {
            Speaker newSpeaker = new Speaker(name, username, password, email);
            usernameToPerson.put(username, newSpeaker);
            idToPerson.put(newSpeaker.getID(), newSpeaker);
            return true;
        }
        return false;
    }

    public boolean addAnnouncementChats(String personId, String acId) {
        Speaker individual = (Speaker) idToPerson.get(personId);
        if(!individual.getAnnouncementChats().contains(acId)) {
            individual.announcementChatIds.add(acId);
            return true;
        }
        return false;
    }

    /**
     * Get list of all talks in a Speaker object, referred to by the Speaker's ID.
     * @param speakerID ID of the Speaker
     * @return Arraylist of Strings corresponding to Talk Event IDs
     */
    public ArrayList<String> getSpeakerIdAllTalks(String speakerID){
        Speaker spe = (Speaker) getPerson(speakerID);
        return spe.getAllTalks();}


    // I believe confirmEmployee is EXACT same method as typePerson in Person Class.
    /*
    public int confirmEmployee(String username) {
        if(usernameToPerson.containsKey(username)) {
            return getPersonByUsername(username).getTypePerson();
        }
        return -1;
    }*/

    public String IDtoUsername(String Id){
        return getPerson(Id).getUsername();
    }
    // apply notify observers

    // get messages from Organizer and from other Employees /**

    // check request board

    // change the status of requests, and implement notifications

}
