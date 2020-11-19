package Message;

// Programmer: Ran Yi, Karyn Komatsu
// Description: For current User to
// Date Modified: 18/11/2020

import Message.Message;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageManager implements Serializable {
    private ArrayList<Message> messageList;

    public MessageManager() {
        messageList = new ArrayList<>();
    }

    /**
     * Create new Message.Message and add it to the MessageList.
     *
     * @return the messageId iff succeed.
     */
    public String createMessage(String senderId, String recipientId, String content) {
        Message newMessage = new Message(senderId, recipientId, content);
        messageList.add(newMessage);
        return newMessage.getMessageId();
    }

    /**
     * this method is used for event messages.
     *
     * @param eventId an id representing the eventId
     * @param content an string reprsenting the content
     * @return ID of the newly created Message object
     */
    public String createMessage(String eventId, String content) {
        Message newMessage = new Message(eventId, content);
        messageList.add(newMessage);
        return newMessage.getMessageId();

    }

    /**
     * Get all the messages stored in this MessageManager.
     *
     * @return the Message.Message.messageList.
     */
    public ArrayList<Message> getMessageList() {
        return this.messageList;
    }

    /**
     * Get Message.Message by the Id.
     *
     * @param MessageId the ID of Message object that we are trying to retrieve.
     * @return the Message.Message.
     */
    private Message getMessage(String MessageId) {
        Message curMessage = null;
        for (Message m : messageList) {
            if (m.getMessageId().equals(MessageId)) {
                curMessage = m;
            }
        }
        return curMessage;
    }

    /**
     * @return the list of IDs of the messages stored in this MessageManager.
     */
    public ArrayList<String> getMessageIDs() {
        ArrayList<String> messageIDs = new ArrayList<>();
        for (Message m : messageList) {
            messageIDs.add(m.getMessageId());
        }
        return messageIDs;
    }
}
