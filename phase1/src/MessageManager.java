import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private List<Message> MessageList;

    MessageManager(){
        MessageList = new ArrayList<>();
    }

    /**
     * Create new Message and add it to the MessageList.
     *
     * @return true iff succeed.
     */
    public boolean createMessage(String senderId, String recipientId, String content){
        Message newMessage = new Message(senderId, recipientId, content);
        MessageList.add(newMessage);
        return true;
    }

    /**
     * Get Message by the Id.
     *
     * @return the Message.
     */
    public Message getMessageId(String MessageId){
        Message curMessage = null;
        for (Message m : MessageList){
            if (m.getMessageId().equals(MessageId)){
                curMessage = m;
            }
        }
        return curMessage;
    }
}
