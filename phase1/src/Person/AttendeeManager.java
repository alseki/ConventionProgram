package Person;

import Events.Event;

import java.util.ArrayList;
import java.util.List;

public class AttendeeManager extends PersonManager {

    private List<Attendee> allAttendees;

    public AttendeeManager() {
        super();
    }

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





    public boolean findPerson(String username, String password) {

        // return true if desired Attendee obj has been found (aka !null). Otherwise, return false
        return searchPersonHelper(username, password, "") != null;
    }

    public String getPerson(String username, String password) {
        Attendee curr = searchPersonHelper(username, password, "");

        if(curr != null) {
            return curr.getID();
        }
        return null;

    }

    public ArrayList<String> getContactList(String userID) {
        Attendee curr = searchPersonHelper("", "", userID);

        if(curr != null) {
            return curr.getContactList();
        }
        return null;
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

