import java.util.List;

public class OrganizerP extends PersonP implements Signupable {


    OrganizerP(String userName, String password, String emailAddress, String fullName) {
        super(userName, password, emailAddress, fullName);
    }

    //    *store fellowOrganisersList
//
//            messageEveryoneManager
//
//come up with a list of all organizers
//
//come up with a list of all persons
//
//come up with a list of all speakers

    //come up with a list of all attendees

    @Override
    public List getEventsSignedUpFor() {
        return eventIdList;
        // then AttendeeManager will produce the list of strings (talkTitle, talkTime, etc.) based off this id)
    }

//    @Override
//    public boolean checkDoubleBooking() {
//        return false;
//    }
//
//    @Override
//    public void addEventIDForUser() {
//
//    }
//
//    @Override
//    public void cancelEvent() {
//
//    }
//
//    @Override
//    public void organizerAddEvent() {
//
//    }
//
//    @Override
//    public void organizerCancelEvent() {
//
//    }

//implements everything from Signupable;

}