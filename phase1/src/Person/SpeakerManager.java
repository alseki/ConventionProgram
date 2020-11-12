package Person;

import Events.Event;
import Person.Person;

public class SpeakerManager extends PersonManager {

    /**
     * This calls doubleBooking below and then adds Person.Speaker to person's event list. I will add the person id to event
     * object's attendee list soon
     */

    public boolean createAccount(String name, String username, String password, String email) {

        return false;
    }

    public void addToEventsList(Speaker pers, Event event) {
        if(!doubleBooking(pers, event)) {
            //pers.getEventsSignedUp().add(pers.getId());
        }
    }

    public void cancelEvent(Speaker pers, Event event) {
        //if(pers.getEventsSignedUp().contains(event.getID())) {
        //    pers.getEventsSignedUp().remove(event.getID());

        //}
    }

    /**
     * This calls doubleContact below and adds a contact id to person's contactList
     * @param pers
     * @param contact
     */
    public void addToContactsList(Speaker pers, Person contact ) {
        //if(!doubleContact(pers, contact)) {
         //   pers.getContactList().add(contact.getId());
        //}
    }

    /**
     * This checks that the user is not already signed for two events
     * @param pers
     * @param event
     * @return
     */
    public boolean doubleBooking(Speaker pers, Event event) {

        //return pers.getEventsSignedUp().contains(event.getID());
        return false;

    }

    /**
     * This checks that the user does not already have this contact in contactList
     * @param pers
     * @param contact
     * @return
     */
    public boolean doubleContact(Speaker pers, Person contact) {

        //return pers.getContactList().contains(contact.getId());
        return false;
    }

}






