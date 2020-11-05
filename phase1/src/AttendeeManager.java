import java.util.ArrayList;
import java.util.List;

public class AttendeeManager extends PersonManager {

    private List<Attendee> allAttendees;

    public AttendeeManager() {
        allAttendees = new ArrayList<>();
    }

    public boolean createAccount(String name, String username, String password, String email) {
        Attendee newAtt = new Attendee(name, username, password, email);
        allAttendees.add(newAtt);
    }

    public ArrayList<String> getEventList(String userID) {
        for(Attendee a: allAttendees) {
            if((a.id).equals(userID)) {    // no idea what's up with the red text...
                return a.eventsSignedUp;
            }
        }
    }




    // BELOW IS PAUL'S STUFF... delete?

// please do not have variable names that are the same name as a person, it makes the code difficult to read.

    /**
     * This checks that the user is not already signed for two events
     * @param pers
     * @param event
     * @return
     */
    public boolean doubleBooking(Attendee pers, Event event) {

        return pers.getEventsSignedUp().contains(event.id);

    }

    /**
     * This checks that the user does not already have this contact in contactList
     * @param pers
     * @param contact
     * @return
     */
    public boolean doubleContact(Attendee pers, Person contact) {

        return pers.getContactList().contains(contact.id);
    }
}
