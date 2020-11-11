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
    }

    public boolean createAccount(String name, String username, String password, String email) {
        Attendee newAtt = new Attendee(name, username, password, email);
        allAttendees.add(newAtt);
        return true;
    }

    public ArrayList<String> getAttendeeEventList(String userID) {
        for(Attendee a: allAttendees) {
            if((a.id).equals(userID)) {
                return a.eventsSignedUp;
            }
        }
        return null;
    }


}
