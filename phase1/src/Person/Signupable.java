package Person;

import java.util.ArrayList;

public interface Signupable {

    void signUp(String eventID);

    void cancelSpot(String eventID);

    ArrayList<String> getEventsSignedUp();
}