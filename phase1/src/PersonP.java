import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonP {

    protected String userName;
    protected String password;
    protected String emailAddress;
    protected String fullName;

    // This ID is an identification number for the user, and the information contained in the user's
    // profile will be stored in a Person object, but this Person object will not join a personList
    // object of sorts. Repeat: the ID serves to get information from the Person object which is not stored
    // in a PersonList object. This is so that one entity object does not store other entity objects.

    protected List chatIdList = new ArrayList();   // New message objects sent to others will have an ID referencing
    // a string from a list of strings
    protected List contactsIdList = new ArrayList();   // same reasons from directly above, but this is a list of
    // contacts from the conference, each with an ID
    protected List eventIdList = new ArrayList();  // Event object will fill this field

    Random rand = new Random();
    int upperbound = 1000000;
    public final int id = rand.nextInt(upperbound);

    PersonP(String userName, String password, String emailAddress, String fullName){
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.fullName = fullName;

    }

    public void addToContactsList(Person person){
        this.contactsIdList.add(person.getId());

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public int getID() {
        return id;
    }

    public List getChatIDList() {
        return chatIdList;
    }

    public List getContactsIDList() {
        return contactsIdList;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setChatIDList(List chatIDList) {
        this.chatIdList = chatIDList;
    }

    public void setContactsIDList(List contactsIDList) {
        this.contactsIdList = contactsIDList;
    }

//    public void addToChatList(Message message) {
//        this.chatIDList.add(message);
//    }


}
