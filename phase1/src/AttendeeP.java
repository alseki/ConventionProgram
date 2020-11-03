import java.util.List;
public class AttendeeP extends PersonP implements SignupableP{


        AttendeeP(String userName, String password, String emailAddress, String fullName) {
            super(userName, password, emailAddress, fullName);
        }

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


        //implements everything from Signupable
    }


