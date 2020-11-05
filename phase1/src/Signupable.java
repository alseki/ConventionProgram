public interface Signupable {

    void signUp(String eventID);

    void cancelSpot(String eventID);


    boolean checkUsernamePasswordAtLogin();
    boolean doubleBooking();
    // signupable is just about a person being able to join an event. It is not meant to have anythng to do with
    // double bookings or anything or Checkusername and passwords. That is irrelavent.



}