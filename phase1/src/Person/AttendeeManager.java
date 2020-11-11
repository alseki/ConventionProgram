package Person;

import Events.Event;

import java.util.ArrayList;
import java.util.List;

public class AttendeeManager extends PersonManager {

    private List<Attendee> allAttendees;

    public AttendeeManager() {
        allAttendees = new ArrayList<>();
    }

    public boolean findPerson(String username, String password) {
        for(Attendee a: allAttendees) {
            if((a.username).equals(username) && (a.password).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String getPerson(String username, String password) {
        for(Attendee a: allAttendees) {
            if((a.username).equals(username) && (a.password).equals(password)) {
                return a.getID();
            }
        }
        return null;
    }

    public boolean createAccount(String name, String username, String password, String email) {
        if(!findPerson(username, password)) {
            Attendee newAtt = new Attendee(name, username, password, email);
            allAttendees.add(newAtt);
            return true;
        }
        return false;
    }

    public ArrayList<String> getContactList(String userID) {
        for(Attendee a: allAttendees) {
            if((a.id).equals(userID)) {
                return a.getContactList();
            }
        }
        return null;
    }

    public ArrayList<String> getEventsSignedUpFor(String userID) {
        for(Attendee a: allAttendees) {
            if((a.id).equals(userID)) {
                return a.getEventsSignedUp();
            }
        }
        return null;
    }


}
