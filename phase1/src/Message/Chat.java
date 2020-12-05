package Message;

// Programmer: Karyn Komatsu, Ran Yi
// Description: Chat Entity, has ID, the messageIDs of the Messages sent in this Chat. and the Users' ID.
// Date Modified: 18/11/2020

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Chat implements Serializable {
    protected String Id;
    protected String name;
    protected ArrayList<String> messageIds;
    protected ArrayList<String> personIds;
    protected String password;
    protected int announcementOrNot; // 1 is announcement which cannot reply and 0 for general chat.

    //Chat constructor. Input is arraylist of ID(s) of guest(s) that you (owner) want to form group chat with.
    public Chat(String ownerId, String guestId){
        messageIds = new ArrayList<>();
        personIds = new ArrayList<>();
        personIds.add(ownerId);
        personIds.add(guestId);
        this.Id = UUID.randomUUID().toString();
        this.password = UUID.randomUUID().toString(); //FIXME: fix the password things for these 3 constructors.
    }
    public Chat(String ownerId, ArrayList <String> guestIds, String name){
        messageIds = new ArrayList<>();
        personIds = new ArrayList<>();
        personIds.add(ownerId);
        personIds.addAll(guestIds);
        this.name = name;
        this.Id = UUID.randomUUID().toString();
        this.password = UUID.randomUUID().toString();
    }

    public Chat(String ownerId, String[] guestIds, String name) {
        messageIds = new ArrayList<>();
        personIds = new ArrayList<>();
        personIds.add(ownerId);
        Collections.addAll(personIds,guestIds);
        this.name = name;
        this.Id = UUID.randomUUID().toString();
        this.password = UUID.randomUUID().toString();
    }

    public Chat(String ownerId, ArrayList<String> guestIds) {
    }

    /**
     * Add the Id of the Message to messageIds list.
     * @param messageId - the Id of the Message object that we want to add.
     * @return True iff messageId was successfully added to messageIds.
     */
    public boolean addMessageIds(String messageId){
        this.messageIds.add(messageId);
        return true;
    }

    /**
     * Add the Id of the Message to messageIds list.
     * @param personId - the Id of the Message object that we want to add.
     * @return True iff messageId was successfully added to messageIds.
     *
     */
    public boolean addPersonIds(String personId) {
        this.personIds.add(personId);
        return true;
    }

    /**
     * Gets ID of Chat.
     * @return the Id of this Chat.
     */
    public String getId(){
        return this.Id;
    }

    /**
     * Gets all Message IDs that are stored in this Chat.
     * @return ArrayList of strings (Message IDs) stored in this Chat.
     */
    public ArrayList<String> getMessageIds() {
        return this.messageIds;
    }

    /**
     * Gets all Person IDs that are stored in this Chat.
     * @return ArrayList of strings (Person IDs) stored in this Chat.
     */
    public ArrayList<String> getPersonIds(){return this.personIds;}

    /**
     *  a getter for the password
     * @return a String representign the annoucement Chat's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if the password entered is correct
     * @param pass inputted password
     * @return true iff the inputted password is correct
     */
    private boolean checkPassword(String pass) {
        return pass.equals(password);
    }

    /**
     * get the type of this chat.
     * @return 1 for announcement 0 for others.
     */
    public int getAnnouncementOrNot() {
        return this.announcementOrNot;
    }

    /**
     * @return get the name of this chat.
     */
    public String getName() {
        return this.name;
    }
}
