package Message;

import Message.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private List<Message> MessageList;

    public MessageManager(){
        MessageList = new ArrayList<>();
    }

    /**
     * Create new Message.Message and add it to the MessageList.
     *
     * @return true iff succeed.
     */
    public boolean createMessage(String senderId, String recipientId, String content){
        Message newMessage = new Message(senderId, recipientId, content);
        MessageList.add(newMessage);
        return true;
    }

    /**
     * Get Message.Message by the Id.
     * @param MessageId the ID of Message object that we are trying to retrieve.
     * @return the Message.Message.
     */
    public Message getMessage(String MessageId){
        Message curMessage = null;
        for (Message m : MessageList){
            if (m.getMessageId().equals(MessageId)){
                curMessage = m;
            }
        }
        return curMessage;
    }

    /**
     * Get message formatted as: "[From]: [ID of the sender]\new line
     *                            [To]: [ID of the recipient]\newline
     *                            [Time Sent]: [time that was sent]\newline
     *                            [Message]: [the content of the message]\newline"
     * @param messageId of the message that is to be formatted.
     * @return Formatted string representation of the message.
     */
    public String getFormattedMessage(String messageId){
        String sender = getMessage(messageId).getSenderId();
        String recipient = getMessage(messageId).getRecipientId();
        LocalDateTime time = getMessage(messageId).getDateTime();
        String message = getMessage(messageId).getContent();
        return "From: " + sender + "\n" + "To: " + recipient + "\n" + "Time sent: " + time + "\n"
                + "Message: " + message + "\n";}

}
