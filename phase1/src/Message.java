import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Message {
    private String Id;
    private String senderId;
    private String recipientId;
    private LocalDateTime dateTime;
    private String content;

    Message(String senderId, String recipientId, String content){
        this.senderId = senderId;
        this.recipientId = recipientId;
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
    public String getSenderId(){
        return this.senderId;
    }

    /**
     * This gives access to the recipient of this Message.
     *
     * @return the recipient of this Message.
     */
    public String getRecipientId(){
        return this.recipientId;
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
