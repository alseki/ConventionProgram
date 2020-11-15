package Person;

import Events.Event;

import java.util.ArrayList;
import java.util.List;

public class AttendeeManager extends PersonManager {

    // private List<Attendee> allAttendees;

    public AttendeeManager() {
        super();
    }

    /**
     * instantiates an Attendee object, and assigns it the attributes given by the client entering their info
     * @param name the full name of the person who would like to create an account
     * @param username the username that the person would like to use (will be checked if it exists already or not)
     * @param password the password that the person will secure their account with
     * @param email the email that the person wants to tie their account with
     * @return returns true if an account has been successfully created; returns false if not
     */
    @Override
    public boolean createAccount(String name, String username, String password, String email) {
        if(!usernameToPerson.containsKey(username)) {
            Attendee newAtt = new Attendee(name, username, password, email);
            usernameToPerson.put(username, newAtt);
            allPersons.add(newAtt);
            return true;
        }
        return false;
    }

    /**
     * adds the given eventID to the desired Attendee's "list of event IDs that they are attending"
     * @param userID the ID of the user (not the same as a username)
     * @param eventID the ID of the event the user wants to sign up for
     * @return returns true if the eventID has successfully been added to the user's list of eventIDs, false if not
     */
    public boolean signupForEvent(String userID, String eventID) {
        Attendee curr = (Attendee)idToPerson.get(userID);
        if(!curr.getEventsSignedUp().contains(eventID)) {
            curr.signUp(eventID);
            return true;
        }
        return false;
    }

    /**
     * removes an eventID from the desired Attendee's list of eventIDs
     * @param userID the ID of the user
     * @param eventID the ID of the event the user wants to cancel their spot from
     * @return returns true if the desired eventID has been removed from the user's list of eventIDs; otherwise false
     */
    public boolean removeSpotFromEvents(String userID, String eventID) {
        Attendee curr = (Attendee)idToPerson.get(userID);
        if(curr.getEventsSignedUp().contains(eventID)) {
            curr.cancelSpot(eventID);
            return true;
        }
        return false;
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<String> getContactList(String userID) {
        return idToPerson.get(userID).getContactList();
    }

    public boolean checkForContact(String userID, String contactID) {
        return idToPerson.get(userID).getContactList().contains(contactID);
    }

    public boolean addContact(String userID, String contactID) {
        Attendee currAtt = (Attendee)idToPerson.get(userID);
        if(!currAtt.getContactList().contains(contactID)) {
            currAtt.addContact(contactID);
            return true;
        }
        return false;
    }

    public List<String> getSignedUpEvents(String userID) {
        return ((Attendee)idToPerson.get(userID)).getEventsSignedUp(); //gets the list from Attendee
    }

}

