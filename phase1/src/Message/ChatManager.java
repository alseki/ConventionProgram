package Message;

import java.util.ArrayList;
import java.util.List;

public class ChatManager {
    private List<Chat> ChatsList; // list for storing a collection of all Message.Message.Chat objects

    public ChatManager(){
        ChatsList = new ArrayList<>();
    }

    /**
     * Create new Message.Message.Chat object among user and a group of friends, and add to the ChatsList
     * @param ownerId ID of the user owning this Message
     * @param guestIds Collection of IDs of (one or more) guests
     * @return true iff new Message.Message.Chat object was successfully created and added to ChatList
     */
    public boolean createChat(String ownerId, ArrayList <String> guestIds){
        Chat newC = new Chat(ownerId, guestIds);
        ChatsList.add(newC);
        return true;
    }

    /**
     * Add Message.Message ID to Message.Message.Chat, where Message.Message.Chat is referred by the Message.Message.Chat ID
     * @param chatId of the Message.Message.Chat object that we want to add the messageID to
     * @param messageId of the Message.Message object to be added to the list in Message.Message.Chat object
     * @return true iff the message ID was added to the Message.Message.Chat
     *         false iff no Message.Message.Chat in ChatList has the inputted chatId
     */
    public boolean addMessage(String chatId, String messageId){
        for(Chat chat: ChatsList) {
            if (chat.getId().equals(chatId)){
                chat.addMessageIds(messageId);
                return true;
            }
        }
        return false;
    }

    //NOTE: Below is same method, except that it takes Message.Message.Chat object instead of its ID.
    // I sort of got confused whether Message.Message.ChatManager should take in the actual Message.Message.Chat object or only its ID...
    //public boolean addMessageIds(Message.Message.Chat chat, String messageId){
    //    chat.addMessageIds(messageId);
    //    return True;
    //}

    /**
     * @param chatId of Message.Message.Chat object
     * @return ArrayList of messageIDs stored in the Message.Message.Chat with inputted chatID
     *         null iff no Message.Message.Chat in ChatList has the inputted chatId
     */
    //Will ask TA if it is the best option return null when an invalid chatId is inputted...
    public ArrayList<String> getMessages(String chatId){
        for(Chat c: ChatsList){
            if (c.getId().equals(chatId)){
                return c.getMessageIds();
            }
        }
        return null;
    }

    /**
     * Update senders (personIds) list of Message.Message.Chat when a new Person.Person is added
     * @param chatId the ID of the Message.Message.Chat that is adding new Person.Person
     * @param personId the ID of the new Person.Person being added to the Message.Message.Chat
     * @return true iff senders (personIds) list of Message.Message.Chat is successfully updated
     */
    //The senders list and related methods in Message.Message.Chat class must be fixed so that it contains ID instead of Person.Person
    public boolean addPerson(String chatId, String personId){
        for(Chat c: ChatsList){
            if (c.getId().equals(chatId)){
                c.addPersonIds(personId);
                return true;
            }
        }
        return false;
    }

    /**
     * @getter
     * @param personId
     * @return Message.Message.Chat corresponding to the Person.Person ID
     */
    //I think Person.Person class needs to be fixed before coding this method.
    //-> Person.Person class shouldn't contain list of Message.Message.Chat as a private variable.
    //public Message.Message.Chat getChats(String personId){}

    //WARNING: Will ask group member code below would be helpful to avoid redundant code.
    /**
     * Finds the Message.Message.Chat object with input Message.Message.Chat ID
     * @param chatId of the Message.Message.Chat object we are trying to find
     * @return Message.Message.Chat object corresponding to the Message.Message.Chat ID inputted
     *         null if ChatID invalid
     */
    public Chat findChat(String chatId){
        Chat chat = null;
        for(Chat c: ChatsList){
            if (c.getId().equals(chatId)){
                chat = c;
            }
        }
        return chat;
    }
}

// CRC Card Definition
// Stores a collection of all Message.Message.Chat objects - DONE: ChatsList
//Creates new Message.Message.Chat objects - DONE: createChat method
//Can add Message.Message IDs to Chats - DONE: addMessage
//Must update Person.Person list if new Person.Person is added to Message.Message.Chat - DONE: addPerson
//Getter for Message.Message.Chat by Person.Person ID - FIX BIT OF CODE IN PERSON CLASS BEFORE IMPLEMENTING THIS: getChats
//Getter for Message.Message list by Message.Message.Chat ID (Add to CRC Card maybe?) - DONE: getMessages

