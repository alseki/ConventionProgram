package Person;

import Events.Event;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonManager {

    protected static List<Person> allPersons;


    public PersonManager() {   // constructor that creates the list
        allPersons = new ArrayList<>();

    }

    public abstract boolean createAccount(String name, String username, String password, String email);


// manager.getContactList(currentUserID);
    // format list
    // send the Presenter the formatted contactList to print (if empty, say so)

    public abstract ArrayList getContactList(String id);


    public boolean addContact(String currentUserID, String contactUsername) {
        String contactID = getCurrentUserID(contactUsername);
        if (doubleContact(currentUserID, contactID)) {
            getPersonById(currentUserID).getContactList().add(contactUsername);
            return true;
        }
        return false;
    }

    public void addChat(String currentId, String chatID) {
        getPersonById(currentId).getChatList().add(chatID);

    }

    public static Person getPerson(String currentUserId) {
        for (Person person : allPersons) {
            if ((person.id == currentUserId)) {
                return person;
            }
        }
        return null;
    }

    public List getChatIDs(String currentUserID) {
        return getPersonById(currentUserID).getChatList();

    }



    /**
     * @param username
     * @param password
     * @return returns Person object so that other methods can retrieve more features from it; also used by findPerson()
     */
    public static Person getPerson(String username, String password) {
        for (Person person : allPersons) {
            if ((person.username == username) && (person.password == password)) {
                return person;
            }
        }
        return null;
    }


    public static Person getPersonByUsername(String username) {
        for (Person person : allPersons) {
            if (person.username == username) {
                return person;
            }
        }
        return null;
    }


    /**
     *
     * @param currentUserID
     * @return return boolean value as to whether a Person object (with correct username, password) exists
     */


    /**
     * @param currentUserID
     * @return returns Person object for methods that are only inputting currentUserId, such as getContactList() in Controller
     */
    public static Person getPersonById(String currentUserID) {
        for (Person person : allPersons) {
            if ((person.currentUserID) == currentUserID) {
                return person;
            }
        }
        return null;
    }

    /**
     * @param username
     * @return
     */

    public static String getCurrentUserID(String username) {
        for (Person person : allPersons) {
            if (person.username == username) {
                return person.getId();
            }
        }
        return null;

    }

    public boolean findPerson(String username, String password) {
        Person findUser = getPerson(username, password);
        if (findUser != null) {
            return true;
        }
        return false;
    }

    public boolean doubleBooking(String username, Event eventSession) {
        Person person = getPersonByUsername(username);
        String eventSessionID = eventSession.getID();
        return person.getEventList().contains(eventSessionID);
    }



    /**
     * This checks that the user does not already have this contact in contactList
     *
     * @param id
     * @param userName
     * @param password
     * @return a boolean value with respect to whether the user has a login name and password. Being called by a
     * controller class, this will tell the controller to sign the user in
     *
     */
    public boolean doubleContact(String currentId, String contactUsername) {

        Person person =  getPersonById(currentId);
        String contactID = getCurrentUserID(contactUsername);
        return person.getContactList().contains(contactID);
    }


}






