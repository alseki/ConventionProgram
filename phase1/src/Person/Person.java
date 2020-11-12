package Person;

import java.util.ArrayList;

public abstract class Person {
    protected String username;
    public String id;
    protected String currentUserID;
    protected String password;
    protected String email;
    protected String fullName;

    protected ArrayList<String> contactList;
    protected ArrayList<String> chatList;
    protected ArrayList<String> eventList;

    /**
     * this allows for access to the persons; username
     *
     * @return the string of the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * This gives access to the person's ID
     *
     * @return returns the string of the ID
     */
    public String getID() {
        return this.id;
    }

    /**
     * this gives access to the person's email
     *
     * @return returns this person's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * this gives access to the person's full anme
     *
     * @return returns this person's full name
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * this gives access to the person's password
     *
     * @return returns this person's password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * this returns the names of the person's in the contact list
     *
     * @return this returns the names in the contact list will return and array list of string's
     */
    public ArrayList<String> getContactList() {
        return this.contactList;
    }
    public void addContactList(String username){
        this.contactList.add(username);
    }

    /**
     *allows for access to the list of Message.Message.Chat IDs
     * @return ArrayList returns the list of string corresponding to the chat Ids
     */
    public ArrayList<String> getChatList() {
        return chatList;
    }

    /**
     * allows for access to the list of events for Person.doubleBooking
     * @return ArrayList return the ist of strings corresponding to the event IDs
     */
    public ArrayList<String> getEventList() {
        return eventList;
    }

    /**
     * set's the email of the person. Should be in the format of __@__.com. The email should be a string
     *
     * @param email the email of the person
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * allows to set the person's username. Username should be a string
     *
     * @param username the username of the person
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * allows to set the person's password. The password should be a string.
     *
     * @param password the password of the person
     */
    public void setPassword(String password) {
        this.password = password;
    }


}

