package Person;

import java.util.ArrayList;

public interface Signupable {

    // ArrayList<String> eventsSignedUp = new ArrayList<>();
    // ^ do we need this here? not sure.. feel free to delete

    void signUp(String eventID);

    void cancelSpot(String eventID);

    ArrayList<String> getEventsSignedUp();
}