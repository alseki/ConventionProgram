package Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Chat {
    private String Id;
    private ArrayList<String> messageIds;
    private ArrayList<String> personIds;

    public Chat(String currentId, String friendId){
        messageIds = new ArrayList<>();
        personIds = new ArrayList<>();
        personIds.add(currentId);
        personIds.add(friendId);
        this.Id = UUID.randomUUID().toString();
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
}
