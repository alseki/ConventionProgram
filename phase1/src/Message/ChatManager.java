package Message;

// Programmer: Karyn Komatsu, Ran Yi
// Description: For current User to
// Date Modified: 18/11/2020

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatManager {
    private ArrayList<Chat> chatsList; // list for storing a collection of all Message.Message.Chat objects
    private ArrayList<AnnouncementChat> aChatsList; // list for storing a collection of all AnnouncementChat objects
    public ChatManager(){
        chatsList = new ArrayList<>();
        aChatsList = new ArrayList<>();
    }

    /**
     * Tells the user whether or not there exist chats in the system
     * @return Whether there exist chats in the system
     */
    public boolean isEmpty() {
        return chatsList.isEmpty();
    }

    /**
     * Create new Message.Message.Chat object between user and a contact, and add to the ChatsList
     * @param ownerId ID of the user owning this Chat object
     * @param guestId IDs of guest
     * @return the chatID iff new Message.Message.Chat object was successfully created and added to ChatList
     */
    public String createChat(String ownerId, String guestId){
        Chat newC = new Chat(ownerId, guestId);
        chatsList.add(newC);
        return newC.getId();
    }

    /**
     * Create new Message.Message.Chat object among user and a group of friends, and add to the ChatsList
     * @param ownerId ID of the user owning this Chat object
     * @param guestIds Collection of IDs of (one or more) guests
     * @return the chatID iff new Message.Message.Chat object was successfully created and added to ChatList
     */
    public String createChat(String ownerId, ArrayList <String> guestIds){
        Chat newC = new Chat(ownerId, guestIds);
        chatsList.add(newC);
        return newC.getId();
    }

    /**
     * creates and returns an annoucment chat with eventid and attendeeids
     * @param eventid id representign the event for the annoucement
     * @param attendeeids the id's of the attendess
     * @return the chatID of AnnocuementChat made
     */
    public String createAnnouncementChat(String eventid, ArrayList<String> attendeeids){
        AnnouncementChat ac = new AnnouncementChat(eventid, attendeeids);
        aChatsList.add(ac);
        return ac.getId();
    }


    /**
     * Add Message ID to Chat, where Chat is referred by the Chat ID
     * @param chatId of the Chat object that we want to add the messageID to
     * @param messageId of the Message object to be added to the list in Chat object
     * @return true iff the message ID was added to the Chat
     *         false iff no Chat in ChatList has the inputted chatId
     */
    public boolean addMessage(String chatId, String messageId){
        for(Chat chat: chatsList) {
            if (chat.getId().equals(chatId)){
                chat.addMessageIds(messageId);
                return true;
            }
        }
        for(AnnouncementChat aChat: aChatsList) {
            if (aChat.getId().equals(chatId)){
                aChat.addMessageIds(messageId, aChat.getPassword());
                return true;
            }
        }
        return false;
    }

    /**
     * Get ArrayList of messageIDs of the Messages storedin the Chat or AnnouncementChat object
     * represented by the inputted chatId
     * @param chatId of Chat or Announcement Chat object
     * @return ArrayList of messageIDs stored in the Message.Message.Chat with inputted chatID
     *         null iff no Message.Message.Chat in ChatList has the inputted chatId
     */
    public ArrayList<String> getMessages(String chatId){
        for(Chat c: chatsList){
            if (c.getId().equals(chatId)){
                return c.getMessageIds();}
        }
        for(AnnouncementChat ac: aChatsList){
            if (ac.getId().equals(chatId)){
                return ac.getMessageIds();}
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
        for(Chat c: chatsList){
            if (c.getId().equals(chatId)){
                c.addPersonIds(personId);
                return true;}
        }
        for(AnnouncementChat ac: aChatsList){
            if (ac.getId().equals(chatId)){
                ac.addPersonIds(personId);
                return true;}
        }
        return false;
    }

    /**
     * Return collection of all Chats where the inputted person ID is part of the member.
     * @param personId the ID of the person
     * @return ArrayList of Chats containing the inputted person ID
     */
    public ArrayList <Chat> searchChatsContaining(String personId){
        ArrayList <Chat> chats = new ArrayList<>();
        for (Chat c: chatsList){
            if (c.getPersonIds().contains(personId)){
                chats.add(c);
            }
        }
        return chats;
    }

    public ArrayList<Chat> getChatsList(){
        return this.chatsList;
    }

    public ArrayList<AnnouncementChat> getAnChatsList(){
        return this.aChatsList;
    }

    /**
     * Finds the Message.Message.Chat object with input Message.Message.Chat ID
     * @param chatId of the Message.Message.Chat object we are trying to find
     * @return Message.Message.Chat object corresponding to the Message.Message.Chat ID inputted
     *         null if ChatID invalid
     */
    private Chat getChat(String chatId){
        for(Chat c: chatsList){
            if (c.getId().equals(chatId)){
                return c;
            }
        }
        return null;
    }

    /**
     * Finds the AnnouncementChat object with input aChatId
     * @param aChatId of the AnnouncementChat object we are trying to find
     * @return AnnouncementChat object corresponding to the aChatId inputted
     *         null if aChatId invalid
     */
    private AnnouncementChat getAnChat(String aChatId){
        for(AnnouncementChat ac: aChatsList){
            if (ac.getId().equals(aChatId)){
                return ac;
            }
        }
        return null;
    }

    /**
     * @return the list of IDs of the chats stored in this ChatManager.
     */
    public ArrayList<String> getChatIDs(){
        ArrayList<String> chatIDs = new ArrayList<>();
        for (Chat c : chatsList){
            chatIDs.add(c.getId());
        }
        return chatIDs;
    }

    /**
     * @return the list of IDs of the AnnouncementChats stored in this ChatManager.
     */
    public ArrayList<String> getAnnouncementChatIDs(){
        ArrayList<String> aChatIDs = new ArrayList<>();
        for (Chat c : aChatsList){
            aChatIDs.add(c.getId());
        }
        return aChatIDs;
    }

    /**
     * Checks if there already exists a Chat object with same group members inputted
     * @param currentId ID of the user
     * @param guestId ID of the other member of the Chat
     * @return True iff there exists a Chat with the exact same group members inputted
     *         False iff there does not exist a Chat with the exact same group members inputted
     */
    public boolean existChat(String currentId, String guestId){
        ArrayList<String> personIds = new ArrayList<>();
        personIds.add(currentId);
        personIds.add(guestId);
        Collections.sort(personIds);
        for(Chat c: chatsList) {
            ArrayList <String> members = c.getPersonIds();
            Collections.sort(members);
            if (members.equals(personIds)){return true;}
        }
        return false;
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
        for(Chat c: chatsList) {
            ArrayList <String> members = c.getPersonIds();
            Collections.sort(members);
            if (members.equals(guestsId)){
                return true;}
        }
        return false;
    }

    /**
     * Find and return the chat ID of the Chat that has the exact same chat members inputted
     * @param currentId ID of the user
     * @param guestId ID of the chat member of the Chat
     * @return chat ID of the Chat with exact same group members inputted.
     *         null otherwise.
     */
    public String findChat(String currentId, String guestId) {
        ArrayList<String> personIds = new ArrayList<>();
        personIds.add(currentId);
        personIds.add(guestId);
        Collections.sort(personIds);
        for (Chat c : chatsList) {
            ArrayList<String> members = c.getPersonIds();
            Collections.sort(members);
            if (members.equals(personIds)) {
                return c.getId();
            }
        }
        return null;
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
        for(Chat c: chatsList) {
            ArrayList <String> members = c.getPersonIds();
            Collections.sort(members);
            if (members.equals(guestsId)){
                return c.getId();
            }
        }
        return null;
    }

    /**
     * Get chat formatted as: "[ID]: [ID of the chat]\new line
     *                            [Participants]: [ID of the Participants]\newline
     * @param chatID of the message that is to be formatted.
     * @return Formatted string representation of the chat.
     */
    public String getFormattedChat(String chatID){
        StringBuilder participants = new StringBuilder();
        for (String participantID : this.getChat(chatID).getPersonIds()){
            participants.append(participantID).append("\n");
        }
        return "ChatID: " + chatID + "\n" + "Participants: " + "\n" + participants + "\n";
    }

    /**
     * Get AnnouncementChat formatted as: "[ID]: [ID of the chat]\newline
     *                                     [SenderID]: [Sender's ID]\newline
     *                                     [Participants]: [ID of the Participants]\newline
     * @param aChatID of the message that is to be formatted.
     * @return Formatted string representation of the chat.
     */
    public String getFormattedAnChat(String aChatID){
        StringBuilder participants = new StringBuilder();
        for (String participantID : this.getAnChat(aChatID).getPersonIds()){
            participants.append(participantID).append("\n");
        }
        return "AnnouncementChatID: " + aChatID + "\n" + "SenderID: " +
                this.getAnChat(aChatID).getOwnerId() + "\n" + "Participants: "
                + "\n" + participants + "\n";}
}

// CRC Card Definition
// Stores a collection of all Message.Message.Chat objects - DONE: ChatsList
//Creates new Message.Message.Chat objects - DONE: createChat method
//Can add Message.Message IDs to Chats - DONE: addMessage
//Must update Person.Person list if new Person.Person is added to Message.Message.Chat - DONE: addPerson
//Get ArrayList of all Chats containing Person.Person ID - DONE: searchChats
//Getter for Message.Message list by Message.Message.Chat ID (Add to CRC Card maybe?) - DONE: getMessages

