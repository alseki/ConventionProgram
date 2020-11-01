import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Message {
    private String Id;
    private Person sender;
    private Person recipient;
    private LocalDateTime dateTime;
    private String content;

    Message(Person sender, Person recipient, String content){
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.Id = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
    }

    /**
     * This gives access to the Id of this Message.
     *
     * @return the Id of this Message.
     */
    public String getMessageId(){
        return this.Id;
    }

    /**
     * This gives access to the sender of this Message.
     *
     * @return the sender of this Message.
     */
    public Person getSender(){
        return this.sender;
    }

    /**
     * This gives access to the recipient of this Message.
     *
     * @return the recipient of this Message.
     */
    public Person getRecipient(){
        return this.recipient;
    }

    /**
     * This gives access to the datetime of this Message.
     *
     * In case we need the function of searching messages by datetime in the future.
     *
     * @return the datetime of this Message.
     */
    public LocalDateTime getDateTime(){
        return this.dateTime;
    }

    /**
     * This gives access to the content of this Message.
     *
     * In case we need the function of searching messages by keywords in the future.
     *
     * @return the content of this Message.
     */
    public String getContent(){
        return this.content;
    }
}
