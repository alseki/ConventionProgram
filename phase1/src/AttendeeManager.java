import java.util.ArrayList;
import java.util.List;

public class AttendeeManager { // extends PersonManager

    protected List<Attendee> allAttendees;

    public AttendeeManager() {
        allAttendees = new ArrayList<>();
    }

    public boolean createAccount(String name, String username, String password, String email) {
        Attendee newAtt = new Attendee(name, username, password, email);
        allAttendees.add(newAtt);
    }


    // BELOW IS PAUL'S STUFF... delete?

// please do not have variable names that are the same name as a person, it makes the code difficult to read.
    /**
     * This calls doubleBooking below and then adds Attendee to person's event list. I will add the person id to event
     * object's attendee list soon
     * @param pers
     */

    public void addToEventsList(Attendee pers,Event event) {
        if(!doubleBooking(pers, event)) {
            pers.getEventsSignedUp().add(pers.getId());
        }
    }

    public void cancelEvent(Attendee person, Event event) {
        if(person.getEventsSignedUp().contains(event.id)) {
            person.getEventsSignedUp().remove(event.id);

        }
    }

    /**
     * This calls doubleContact below and adds a contact id to person's contactList
     * @param pers
     * @param contact
     */
    public void addToContactsList(Attendee pers, Person contact ) {
        if(!doubleContact(pers, contact)) {
            pers.getContactList().add(contact.getId());
        }
    }

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
