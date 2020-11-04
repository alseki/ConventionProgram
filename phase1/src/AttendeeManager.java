

public class AttendeeManager extends PersonManager {

    /**
     * This calls doubleBooking below and then adds Attendee to person's event list. I will add the person id to event
     * object's attendee list soon
     * @param person
     */

    public void addToEventsList(Attendee person,Event event) {
        if(doubleBooking(person, event) == false) {
            person.getEventsSignedUp().add(person.getId());
        }
    }

    public void cancelEvent(Attendee person, Event event) {
        if(person.getEventsSignedUp().contains(event.id)) {
            person.getEventsSignedUp().remove(event.id);

        }
    }

    /**
     * This calls doubleContact below and adds a contact id to person's contactList
     * @param person
     * @param contact
     */
    public void addToContactsList(Attendee person, Person contact ) {
        if(doubleContact(person, contact) == false) {
            person.getContactList().add(contact.getId());
        }
    }

    /**
     * This checks that the user is not already signed for two events
     * @param person
     * @param event
     * @return
     */
    public boolean doubleBooking(Attendee person, Event event) {

        return person.getEventsSignedUp().contains(event.id);

    }

    /**
     * This checks that the user does not already have this contact in contactList
     * @param person
     * @param contact
     * @return
     */
    public boolean doubleContact(Attendee person, Person contact) {

        return person.getContactList().contains(contact.id);
    }
}
