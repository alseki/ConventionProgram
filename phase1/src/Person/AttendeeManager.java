package Person;

import Events.Event;

import java.util.ArrayList;
import java.util.List;

public class AttendeeManager extends PersonManager {

    private List<Attendee> allAttendees;

    public AttendeeManager() {
        allAttendees = new ArrayList<>();
    }

    public boolean createAccount(String name, String username, String password, String email) {
        if(!findPerson(username, password)) {
            Attendee newAtt = new Attendee(name, username, password, email);
            allAttendees.add(newAtt);
            return true;
        }
        return false;
    }


    // a helper method that returns the desired Attendee object (when searching)
    // the commented-out code in each method is what the method contained before this helper method was created
    private Attendee searchPersonHelper(String username, String password, String userID) {
        for(Attendee a: allAttendees) {
            if(((a.getUsername()).equals(username) && (a.getPassword()).equals(password)) || (a.getID()).equals(userID)) {
                return a;
            }
        }
        return null;
    }

    public boolean findPerson(String username, String password) {

        // return true if desired Attendee obj has been found (aka !null). Otherwise, return false
        return searchPersonHelper(username, password, "") != null;

        /*
        for(Attendee a: allAttendees) {
            if((a.username).equals(username) && (a.password).equals(password)) {
                return true;
            }
        }
        return false;
         */
    }

    public String getPerson(String username, String password) {
        Attendee curr = searchPersonHelper(username, password, "");

        if(curr != null) {
            return curr.getID();
        }
        return null;

        /*
        for(Attendee a: allAttendees) {
            if((a.username).equals(username) && (a.password).equals(password)) {
                return a.getID();
            }
        }
         */
    }

    public ArrayList<String> getContactList(String userID) {
        Attendee curr = searchPersonHelper("", "", userID);

        if(curr != null) {
            return curr.getContactList();
        }
        return null;

        /*
        for(Attendee a: allAttendees) {
            if((a.id).equals(userID)) {
                return a.getContactList();
            }
        }
        return null;
         */
    }

    public boolean checkForContact(String contactID, String userID) {
        Attendee curr = searchPersonHelper("", "", userID);
        if(curr != null) {
            return curr.getContactList().contains(contactID);
        }
        return false;
    }

    public ArrayList<String> getChats(String userID) {
        Attendee curr = searchPersonHelper("", "", userID);
        if(curr != null) {
            return curr.getChatList();
        }
        return null;
    }

    public boolean addEventIDToPersonEventList(String userID, String eventID) {
        Attendee curr = searchPersonHelper("", "", userID);
        if(curr != null) {
            if(!(curr.getEventsSignedUp().contains(eventID))) {
                curr.getEventsSignedUp().add(eventID);
                return true;
            }
            return false;
        }
        return false;
    }

    public ArrayList<String> getEventsSignedUpFor(String userID) {
        Attendee curr = searchPersonHelper("", "", userID);

        if(curr != null) {
            return curr.getEventsSignedUp();
        }
        return null;

        /*
        for(Attendee a: allAttendees) {
            if((a.id).equals(userID)) {
                return a.getEventsSignedUp();
            }
        }
        return null;
         */
    }

}
