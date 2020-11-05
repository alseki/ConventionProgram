import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Chat {
    private String Id;
    private ArrayList<String> messageIds;
    //private ArrayList<Person> senders;
    //NOTE!! I commented out old codes containing Person and wrote a code for String personId instead.
    //We can ask TA today if entities can contain entities as parameters, then decide which codes to use. -Karyn
    private ArrayList<String> personIds;

    Chat(){
        this.Id = UUID.randomUUID().toString();
    }


    /**
     * Add the Message into this Chat by adding the Id of the Message.
     *
     * @param messageId - the Id of this new Message.
     */
    public void addMessageIds(String messageId){
        this.messageIds.add(messageId);
    }

    /**
     * Add a Person into this Chat by adding the Person instance into the senders ArrayList.
     *
     *
     */
    //public void addPerson(Person newPerson){
    //    this.senders.add(newPerson);
    //}

    public void addPersonIds(String personId) {this.personIds.add(personId);}
    /**
     * This gives access to the Id of this Chat.
     *
     * @return the Id of this Chat.
     */
    public String getId(){
        return this.Id;
    }

    /**
     * This gives access to the Id of all the Messages in this Chat.
     *
     * @return the Ids of Messages in this Chat.
     */
    public ArrayList<String> getMessageIds() {
        return this.messageIds;
    }

    /**
     * This gives access to the senders(Persons) in this Chat.
     *
     * @return the Persons in this Chat.
     */
    //public ArrayList<Person> getSenders() {
    //    return this.senders;
    //}
    public ArrayList<String> getPersonIds(){return this.personIds;}
}
