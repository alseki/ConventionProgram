import java.util.ArrayList;
import java.util.List;

public class SpeakerP extends PersonP implements Signupable {


//    private String talkTitle; // strings of id of talks
//    private String talkTime; // hour contained in string
//                            // this talkTime should be set in Speaker by EventManager perhaps??
//
//    private boolean fellowSpeakersForTalk; // again EventManager will list one or more speakers
//                                          // for each talk, and Speaker can check that with Event


    private List talksList = new ArrayList();


    SpeakerP(String userName, String password, String emailAddress, String fullName, String talkTitle, String talkTime) {
        super(userName, password, emailAddress, fullName);

    }

    @Override
    public List getEventsSignedUpFor() {
        return eventIdList;
    }

    public List getEventsWhereSpeakerTalks() {
        return talksList;

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
    }
}