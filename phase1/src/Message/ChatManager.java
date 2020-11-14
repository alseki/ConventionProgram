package Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatManager {
    private List<Chat> ChatsList; // list for storing a collection of all Message.Message.Chat objects

    public ChatManager(){
        ChatsList = new ArrayList<>();
    }

    /**
     * Create new Message.Message.Chat object among user and a group of friends, and add to the ChatsList
     * @param ownerId ID of the user owning this Chat object
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

    /**
     * @param chatId of Message.Message.Chat object
     * @return ArrayList of messageIDs stored in the Message.Message.Chat with inputted chatID
     *         null iff no Message.Message.Chat in ChatList has the inputted chatId
     */
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
     * Return collection of all Chats where the inputted person ID is part of the member.
     * @param personId the ID of the person
     * @return ArrayList of Chats containing the inputted person ID
     */
    public ArrayList <Chat> searchChats(String personId){
        ArrayList <Chat> chats = new ArrayList<>();
        for (Chat c: ChatsList){
            if (c.getPersonIds().contains(personId)){
                chats.add(c);
            }
        }
        return chats;
    }

    /**
     * Finds the Message.Message.Chat object with input Message.Message.Chat ID
     * @param chatId of the Message.Message.Chat object we are trying to find
     * @return Message.Message.Chat object corresponding to the Message.Message.Chat ID inputted
     *         null if ChatID invalid
     */
    public Chat getChat(String chatId){
        Chat chat = null;
        for(Chat c: ChatsList){
            if (c.getId().equals(chatId)){
                chat = c;
            }
        }
        return chat;
    }

    /**
     * Checks if there already exists a Chat object with same group members inputted
     * @param currentId ID of the user
     * @param guestsId ID of the chat members of the Chat
     * @return True iff there exists a Chat with the exact same group members inputted
     *         False iff there does not exist a Chat with the exact same group members inputted
     */
    public boolean existChat(String currentId, ArrayList <String> guestsId){
        guestsId.add(currentId);
        Collections.sort(guestsId);
        for(Chat c: ChatsList) {
            ArrayList <String> members = c.getPersonIds();
            Collections.sort(members);
            if (members.equals(guestsId)){return true;}
        }
        return false;
    }

    /**
     * Find and return the chat ID of the Chat that has the exact same chat members inputted
     * @param currentId ID of the user
     * @param guestsId ID of the chat members of the Chat
     * @return chat ID of the Chat with exact same group members inputted.
     *         null otherwise.
     */
    public String findChat(String currentId, ArrayList <String> guestsId){
        guestsId.add(currentId);
        Collections.sort(guestsId);
        for(Chat c: ChatsList) {
            ArrayList <String> members = c.getPersonIds();
            Collections.sort(members);
            if (members.equals(guestsId)){return c.getId();}
        }
        return null;}
}

// CRC Card Definition
// Stores a collection of all Message.Message.Chat objects - DONE: ChatsList
//Creates new Message.Message.Chat objects - DONE: createChat method
//Can add Message.Message IDs to Chats - DONE: addMessage
//Must update Person.Person list if new Person.Person is added to Message.Message.Chat - DONE: addPerson
//Get ArrayList of all Chats containing Person.Person ID - DONE: searchChats
//Getter for Message.Message list by Message.Message.Chat ID (Add to CRC Card maybe?) - DONE: getMessages

