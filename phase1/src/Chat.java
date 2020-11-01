import java.util.ArrayList;
import java.util.UUID;

public class Chat {
    private String Id;
    private ArrayList<String> messageIds;
    private ArrayList<Person> senders;

    Chat(){
        this.Id = UUID.randomUUID().toString();
    }


    /**
     * Add the Message into this Chat by adding the Id of the Message.
     *
     * @param messageId - the Id of this new Message.
     */
    private void addMessageIds(String messageId){
        this.messageIds.add(messageId);
    }

    /**
     * Add a Person into this Chat by adding the Person instance into the senders ArrayList.
     *
     * @param newPerson - the new Person.
     */
    private void addPerson(Person newPerson){
        this.senders.add(newPerson);
    }

    /**
     * This gives access to the Id of this Chat.
     *
     * @return the Id of this Chat.
     */
    private String getId(){
        return this.Id;
    }

    /**
     * This gives access to the Id of all the Messages in this Chat.
     *
     * @return the Ids of Messages in this Chat.
     */
    private ArrayList<String> getMessageIds() {
        return this.messageIds;
    }

    /**
     * This gives access to the senders(Persons) in this Chat.
     *
     * @return the Persons in this Chat.
     */
    private ArrayList<Person> getSenders() {
        return this.senders;
    }
}
