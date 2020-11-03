import java.util.List;

public interface SignupableP {

    List getEventsSignedUpFor();


    // Should the following be in this interface and implemented in the Manager classes? Is it okay for a Use case class (Manager) to implement
    // an entity interface
//    boolean checkDoubleBooking();
//
//    void addEventIdForUser();
//        // I'm wondering if this should be boolean rather than void, so that the class which
//        // implements this might return true/false to the Use case class, ex. attendeeManager
//
//    void cancelEvent();    // remove eventID from User's event list
//
//    void organizerAddEvent();
//
//    void organizerCancelEvent();

//    whichever class implements this has the ability to sign up for an Event]
//    Collection of Events signed up for (Event IDs)
//    Getter for this
//
//
//    *getter for this
}