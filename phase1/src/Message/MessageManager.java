package Message;

import Message.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private ArrayList<Message> messageList;

    public MessageManager(){
        messageList = new ArrayList<>();
    }

    /**
     * Create new Message.Message and add it to the MessageList.
     *
     * @return the messageId iff succeed.
     */
    public String createMessage(String senderId, String recipientId, String content){
        Message newMessage = new Message(senderId, recipientId, content);
        messageList.add(newMessage);
        return newMessage.getMessageId();
    }

    /**
     * this method is used for event messages.
     * @param eventId an id representing the eventId
     * @param content an string reprsenting the content
     * @return ID of the newly created Message object
     */
    public String createMessage(String eventId, String content){
        Message newMessage = new Message(eventId, content);
        messageList.add(newMessage);
        return newMessage.getMessageId();

    }

    /**
     * Get Message.Message by the Id.
     * @param MessageId the ID of Message object that we are trying to retrieve.
     * @return the Message.Message.
     */
    private Message getMessage(String MessageId){
        Message curMessage = null;
        for (Message m : messageList){
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
