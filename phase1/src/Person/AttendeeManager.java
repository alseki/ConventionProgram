package Person;

import Events.Event;

import java.util.ArrayList;
import java.util.List;

public class AttendeeManager extends PersonManager {

    private List<Attendee> allAttendees;

    public AttendeeManager() {
        allAttendees = new ArrayList<>();
    }

    /**
     * a method that creates and adds an Attendee account to the list of Attendees (if it doesn't already exist)
     * @param name the real name of the person who wants to create an account
     * @param username the username they would like to use for their account
     * @param password the password they would like to use for their account
     * @param email the email address they would like to associate with their account
     * @return returns true if account has been successfully created, otherwise return false
     */
    public boolean createAccount(String name, String username, String password, String email) {
        if(!findPerson(username, password)) {
            Attendee newAtt = new Attendee(name, username, password, email);
            allAttendees.add(newAtt);
            return true;
        }
        return false;
    }


    // the commented-out code in each method is what the method contained before this helper method was created

    /**
     * a helper method that searches for an Attendee object in the list of Attendees
     * NOTE: matching username and password can help search for an account, as well as by userID
     * @param username the username of the wanted Attendee
     * @param password the password of the wanted Attendee
     * @param userID the ID of the wanted Attendee
     * @return returns the desired Attendee object itself (if found). If not found, return null
     */
    private Attendee searchPersonHelper(String username, String password, String userID) {
        for(Attendee a: allAttendees) {
            if(((a.getUsername()).equals(username) && (a.getPassword()).equals(password)) || (a.getID()).equals(userID)) {
                return a;
            }
        }
        return null;

        //ok so the one problem here is when we want to create an account, it should check if there's another
        //account with the same username, NOT (same username && same password)...
        //note-to-self: come up with a solution to this
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

    public boolean addChat(String userID, String chatID) {
        Attendee curr = searchPersonHelper("", "", userID);
        if(curr != null) {
            if(!(curr.getChatList().contains(chatID))) {
                curr.getChatList().add(chatID);
                return true;
            }
        }
        return false;
    }

    public boolean removeChat(String userID, String chatID) {
        Attendee curr = searchPersonHelper("", "", userID);
        if(curr != null) {
            if(curr.getChatList().contains(chatID)) {
                curr.getChatList().remove(chatID);
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getSignedUpEvents(String userID) {
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

    public boolean signupForEvent(String userID, String eventID) {
        Attendee curr = searchPersonHelper("", "", userID);
        if(curr != null) {
            if(!(curr.getEventsSignedUp().contains(eventID))) {
                curr.getEventsSignedUp().add(eventID);
                return true;
            }
        }
        return false;
    }

    public boolean cancelSpotFromEvent(String userID, String eventID) {
        Attendee curr = searchPersonHelper("", "", userID);
        if(curr != null) {
            if(curr.getEventsSignedUp().contains(eventID)) {
                curr.getEventsSignedUp().remove(eventID);
                return true;
            }
        }
        return false;
    }

}
