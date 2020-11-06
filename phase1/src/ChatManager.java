import java.util.ArrayList;
import java.util.List;

public class ChatManager {
    private List<Chat> ChatsList; // list for storing a collection of all Chat objects

    public ChatManager(){
        ChatsList = new ArrayList<>();
    }

    /**
     * Create new Chat object and add to the ChatsList
     * @return true iff new Chat object was successfully created and added to ChatList
     */
    public boolean createChat(){
        Chat newC = new Chat();
        ChatsList.add(newC);
        return true;
    }

    /**
     * Add Message ID to Chat, where Chat is referred by the Chat ID
     * @param chatId of the Chat object that we want to add the messageID to
     * @param messageId of the Message object to be added to the list in Chat object
     * @return true iff the message ID was added to the Chat
     *         false iff no Chat in ChatList has the inputted chatId
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

    //NOTE: Below is same method, except that it takes Chat object instead of its ID.
    // I sort of got confused whether ChatManager should take in the actual Chat object or only its ID...
    //public boolean addMessageIds(Chat chat, String messageId){
    //    chat.addMessageIds(messageId);
    //    return True;
    //}

    /**
     * @param chatId of Chat object
     * @return ArrayList of messageIDs stored in the Chat with inputted chatID
     *         null iff no Chat in ChatList has the inputted chatId
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
     * Update senders (personIds) list of Chat when a new Person is added
     * @param chatId the ID of the Chat that is adding new Person
     * @param personId the ID of the new Person being added to the Chat
     * @return true iff senders (personIds) list of Chat is successfully updated
     */
    //The senders list and related methods in Chat class must be fixed so that it contains ID instead of Person
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
     * @return Chat corresponding to the Person ID
     */
    //I think Person class needs to be fixed before coding this method.
    //-> Person class shouldn't contain list of Chat as a private variable.
    //public Chat getChats(String personId){}

    //WARNING: Will ask group member code below would be helpful to avoid redundant code.
    /**
     * Finds the Chat object with input Chat ID
     * @param chatId of the Chat object we are trying to find
     * @return Chat object corresponding to the Chat ID inputted
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
// Stores a collection of all Chat objects - DONE: ChatsList
//Creates new Chat objects - DONE: createChat method
//Can add Message IDs to Chats - DONE: addMessage
//Must update Person list if new Person is added to Chat - DONE: addPerson
//Getter for Chat by Person ID - FIX BIT OF CODE IN PERSON CLASS BEFORE IMPLEMENTING THIS: getChats
//Getter for Message list by Chat ID (Add to CRC Card maybe?) - DONE: getMessages

