package Person;

import Events.Event;

import java.util.*;


public abstract class PersonManager {

    // This is a list of all Person objects that will be added to by the Attendee/Organizer/Speaker Managers
    // when a user objects is created
    protected static List<Person> allPersons;

    // This is a dictionary that will pair username to Person object. This dictionary is used by methods below
    protected Map<String, Person> usernameToPerson = new HashMap<String, Person>();

    // This is a dictionary that will pair username to Person object. This dictionary is used by methods below
    protected Map<String, Person> idToPerson = new HashMap<String, Person>();


    public PersonManager() {   // constructor for this Manager that creates the list and the two dictionaries

        allPersons = new ArrayList<>();
        usernameToPerson = new HashMap<>();
        idToPerson = new HashMap<>();

    }

    /**
     *
     * @param name
     * @param username
     * @param password
     * @param email
     * @return This is an abstract constructor for the Person object to be implemented
     *         in the other User-specific managers
     */
    public abstract boolean createAccount(String name, String username, String password, String email);


    /**
     *
     * @param id
     * returns the contact list of the user
     * @return ArrayList<String> contactList the contacts with people that user has gotten
    */
    public ArrayList getContactList(String id) {
        return getPerson(id).getContactList();
    }

    /**
     *
     * @param currentUserID
     * 1 of the 2 overloaded methods under this method name
     * returns the Person object corresponding to currentUserId
     * @return Person
     */
    public Person getPerson(String currentUserID) {
        if (idToPerson.containsKey(currentUserID)) {
            return idToPerson.get(currentUserID);
        }
        return null;
    }

    /**
     *
     * @param username
     * @param password
     * the second of the 2 overloaded methods under this method name
     * returns the Person object corresponding to username and password
     * @return Person, which will be used in the findPerson method, which is for authentication
     */
    public Person getPerson(String username, String password) {
        if (usernameToPerson.containsKey(username)) {
            if (usernameToPerson.get(username).getPassword().equals(password)) {
                return usernameToPerson.get(username);
            }
            return usernameToPerson.get(username); // shouldn't this be a return statement?
        }
        return null;
    }

    /**
     *
     * @param username
     * returns the Person object corresponding to username
     * @return Person; this is for methods in PersonController that which to get Person using username only
     */
    private Person getPersonByUsername(String username) {
        if (usernameToPerson.containsKey(username)) {
            return usernameToPerson.get(username);
        }
        return null;
    }

    /**
     *
     * @param username
     * returns CurrentUser ID
     * @return String; this is for methods that need CurrentUserID, but only which to get CurrentUserID
     * using search by username only. Ex. public boolean addContract; see below
     */
    public String getCurrentUserID(String username) {
        if (usernameToPerson.containsKey(username)) {
            return usernameToPerson.get(username).getID();
        }
        return null;
    }

    /**
     *
     * @param username
     * returns a value that allows for account verificaton; whether accountUser exists or not
     * @return boolean, which allow user to login or not
     */
    public boolean findPerson(String username) {
        return idToPerson.containsKey(username);
    }

    /**
     *
     * @param currentUserID
     * @param contactUsername
     * returns the result (true/false) whether adding a new contact to User's list was successful
     * @return boolean, which indicates after checking for double contact (new or old contact)
     * whether adding contact exceuted or not
     */
    public boolean addContactToPerson(String currentUserID, String contactUsername) {
        String contactID = getCurrentUserID(contactUsername);
        if (!(doubleContact(currentUserID, contactID))) {
            getPerson(currentUserID).addContact(contactUsername);
            return true;
        }
        return false;
    }

    /**
     *
     * @param currentID
     * @param chatID
     * @ return void, this method does not return a boolean, because there is no "false" message per se.
     *          Any message object can be sent
     */
    public void addChat(String currentID, String chatID) {
        getPerson(currentID).addChat(chatID);
        // true;
    }

    /**
     *
     * @param currentUserID
     * @return ArrayList<String>,of chatID of messages receieved by the User
     */

    public List getChats(String currentUserID) {
        return getPerson(currentUserID).getChatList();

    }

    /**
     * This checks that the user has not already booked the same event. Attendees cannot book
     * 2 spots at a given event.
     *
     * @param username
     * @param eventSession
     * @return boolean, as the whether the user has already signed up for a given event
     */

    public boolean doubleBooking(String username, Event eventSession) {
        Person person = getPersonByUsername(username);
        String eventSessionID = eventSession.getID();
        return person.getEventList().contains(eventSessionID);
    }

    /**
     * This checks that the user does not already have this contact in contactList
     *
     * @param currentId
     * @param contactUsername
     * @return boolean, as the whether the user already has the contact in the contactList
     */
    public boolean doubleContact(String currentId, String contactUsername) {

        Person person =  getPerson(currentId);
        String contactID = getCurrentUserID(contactUsername);
        return person.getContactList().contains(contactID);
    }



}









