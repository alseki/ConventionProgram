package Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpeakerManager extends PersonManager {

    protected Map<String, ArrayList<String>> allTalksBySpeaker = new HashMap<String, ArrayList<String>>();

    // This is a map of speaker to speaker's talk event: a map of string personID to eventID


    public SpeakerManager(Map<String, Person> usernameToPerson, Map<String, Person> idToPerson) {
        super(usernameToPerson, idToPerson);
    }

    @Override
    public boolean createAccount(String name, String username, String password, String email) {
        if (!usernameToPerson.containsKey(username)) {
            Speaker newSpeaker = new Speaker(name, username, password, email);
            usernameToPerson.put(username, newSpeaker);
            idToPerson.put(newSpeaker.getID(), newSpeaker);
            return true;
        }
        return false;
    }



    /**
     * accesses an Speaker object in order to retrieve a list of events/talks (eventIDs) that the speaker has been signed up for
     * @param personID of the speaker with list of events/talks
     * @return returns the list of eventIDs that represent the events/talks the Speaker has been signed up for
     */
    public ArrayList<String> getAllTalksSignedUp(String personID) {
        return ((Speaker)idToPerson.get(personID)).getAllTalks(); //gets the list from Speaker
    }



    /**
     *
     * @param userID
     * @param eventID
     * @return boolean; takes eventId created in OrgEventController method createEvent and adds it to Speaker's allTalksId list
     */

    public boolean addTalkId(String userID, String eventID) {
        if ((idToPerson).containsKey(userID)) {
            Speaker individual = (Speaker) getPerson(userID);
            individual.getAllTalks().add(eventID);
            return true;
        }
        return false;
    }

    /**
     *
     * @param userID
     * @param eventID
     * @param eventName
     * @return boolean; takes eventId (created in method aboe), eventName (parameter in same method) and adds to Speaker's
     *                  dictionary allTalksDictionary
     */


    public boolean addTalkIdToDictionary(String userID, String eventID, String eventName) {
        Speaker individual = (Speaker) idToPerson.get(userID);
        if (!(individual.getAllTalksDictionary().containsKey(eventID))) {
            individual.getAllTalksDictionary().put(eventID, eventName);
            return true;
        }
        return false;
    }

    /**
     *
     * @param personId
     * @param acId
     * @return boolean; this function is for adding announcement messages for events created by Organizer, and then putting the chatId in
     *                  Speaker's announcementChatIds list
     */
    public boolean addAnnouncementChats(String personId, String acId) {
        Speaker individual = (Speaker) idToPerson.get(personId);
        if(individual.getAnnouncementChats() == null) {
            return false;
        }
        if(!individual.getAnnouncementChats().contains(acId)) {
            individual.announcementChatIds.add(acId);
            return true;
        }
        return false;
    }

    /**
     * gets the Speaker's list of contacts.
     * @param personId the Person Id of the speaker whose contact list we are retrieving
     * @return returns a list of other people's personIds (Strings) if the desired user is found
     */
    public ArrayList<String> getContactList(String personId) {
        return idToPerson.get(personId).getContactList();
    }

    /**
     * checks a person's contact list to see if the new contact is already in the contactList
     * @param userID the user Id of the person for getting this person's contact list
     * @param contactID the Id of the new contact to double-check if already in the contact list
     * @return returns true if the contact is found inside the person's contact list
     */
    public boolean checkForContact(String userID, String contactID) {
        return idToPerson.get(userID).getContactList().contains(contactID);
    }

    /**
     * adds a contact to a person's list of contacts by Id
     * @param personId the person Id of the person whose contact list the new contact will be added
     * @param contactId the Id of the new contact to be added to the person's contact list
     * @return returns true if the contact has been added to contact list
     * the user's list of contacts
     */
    public boolean addContact(String personId, String contactId) {
        Speaker individual = (Speaker) idToPerson.get(personId);
        if(!(individual.getContactList().contains(contactId))) {
            individual.addContact(contactId);
            return true;
        }
        return false;
    }

    }

















