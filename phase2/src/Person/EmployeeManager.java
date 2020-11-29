package Person;

import java.util.ArrayList;
import java.util.Map;

public class EmployeeManager extends PersonManager {

    // This account is created by organizer or administrator

    public EmployeeManager(Map<String, Person> usernameToPerson, Map<String, Person> idToPerson) {
        super(usernameToPerson, idToPerson);
    }

    // private List<Attendee> allAttendees;
    private ArrayList<String> requestIds;

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
}
