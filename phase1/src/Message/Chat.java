package Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Chat {
    private String Id;
    private ArrayList<String> messageIds;
    private ArrayList<String> personIds;

    Chat(){
        messageIds = new ArrayList<>();
        personIds = new ArrayList<>();
        this.Id = UUID.randomUUID().toString();
    }


    /**
     * Add the Message.Message into this Message.Message.Chat by adding the Id of the Message.Message.
     *
     * @param messageId - the Id of this new Message.Message.
     */
    public void addMessageIds(String messageId){
        this.messageIds.add(messageId);
    }

    /**
     * Add a Person.Person into this Message.Message.Chat by adding the Person.Person instance into the senders ArrayList.
     *
     *
     */


    public void addPersonIds(String personId) {this.personIds.add(personId);}
    /**
     * This gives access to the Id of this Message.Message.Chat.
     *
     * @return the Id of this Message.Message.Chat.
     */
    public String getId(){
        return this.Id;
    }

    /**
     * This gives access to the Id of all the Messages in this Message.Message.Chat.
     *
     * @return the Ids of Messages in this Message.Message.Chat.
     */
    public ArrayList<String> getMessageIds() {
        return this.messageIds;
    }

    /**
     * This gives access to the senders(Persons) in this Message.Message.Chat.
     *
     * @return the Persons in this Message.Message.Chat.
     */

    public ArrayList<String> getPersonIds(){return this.personIds;}
}
