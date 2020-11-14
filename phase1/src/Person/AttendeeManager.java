package Person;

import Events.Event;

import java.util.ArrayList;
import java.util.List;

public class AttendeeManager extends PersonManager {

    // private List<Attendee> allAttendees;

    public AttendeeManager() {
        super();
    }

    @Override
    public boolean createAccount(String name, String username, String password, String email) {
        if(!usernameToPerson.containsKey(username)) {
            Attendee newAtt = new Attendee(name, username, password, email);
            usernameToPerson.put(username, newAtt);
            allPersons.add(newAtt);
            return true;
        }
        return false;
    }

    public boolean signupForEvent(String userID, String eventID) {
        Attendee curr = (Attendee)idToPerson.get(userID);
        if(!curr.getEventsSignedUp().contains(eventID)) {
            curr.signUp(eventID);
            return true;
        }
        return false;
    }

    public boolean removeSpotFromEvents(String userID, String eventID) {
        Attendee curr = (Attendee)idToPerson.get(userID);
        if(curr.getEventsSignedUp().contains(eventID)) {
            curr.cancelSpot(eventID);
            return true;
        }
        return false;
    }

    public ArrayList<String> getContactList(String userID) {
        return idToPerson.get(userID).getContactList();
    }

    public boolean checkForContact(String userID, String contactID) {
        return idToPerson.get(userID).getContactList().contains(contactID);
    }

    public boolean addContactToPerson(String userID, String contactID) {
        Attendee currAtt = (Attendee)idToPerson.get(userID);
        if(!currAtt.getContactList().contains(contactID)) {
            currAtt.addContact(contactID);
            return true;
        }
        return false;
    }

    public List<String> getSignedUpEvents(String userID) {
        return ((Attendee)idToPerson.get(userID)).getEventsSignedUp(); //gets the list from Attendee
    }

}

