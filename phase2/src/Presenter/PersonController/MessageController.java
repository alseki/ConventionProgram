package Presenter.PersonController;

import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.NoDataException;

import java.util.ArrayList;

// Programmer: Ran Yi, Sarah Kronenfeld
// Description: For current User to view chat and message, create chat and send message.
// Date Modified: 19/11/2020

public class MessageController extends SubMenu {
    protected String currentUserID;
    private MessageMenu presenter;
    protected int currentRequest;

    public MessageController (SubMenu subMenu, String currentUserID) {
        super(subMenu);
        presenter = new MessageMenu(personManager, messageManager, chatManager, eventManager, currentUserID);
        this.currentUserID = currentUserID;
    }

    /**
     * Sends a new Message
     */
    public String sendMessageChoice(String ID, String content) throws InvalidChoiceException {
        sendMessage(ID, content);
        return presenter.messageSent();
    }



    // Option 1

    /**
     * Show all the messages this user received in presenter, **sorted by datetime.
     */
    public String[] inBox() throws NoDataException {
        return presenter.getInBox();
    }

    // Option 2

    /**
     * Show all the messages this user sent in presenter, **sorted by datetime.
     */
    public String[] sentBox() throws NoDataException {
        return presenter.getOutBox();
    }

    // Option 3

    /**
     * Show all the chats this user sent in presenter, **sorted by datetime.
     */
    protected String[] seeChats() throws InvalidChoiceException {
        return presenter.getChats(personManager.getChats(currentUserID));
    }

    // Option 4

    /**
     * Show the messages in a chat by chatName.
     */
    protected String[] seeMessages(String chatName) throws InvalidChoiceException {
        return presenter.getChat(chatManager.findChatByName(chatName).getId());
    }

    // Option 5

    /**
     * Creates new Message for existing Chat (1 to 1 chat or group chat both use this.)
     * @param chatName The chatName of the Chat the current user want's to send a Message to
     * @param messageContent The contents of the message the current user wants to send
     */
    protected void sendMessage(String chatName, String messageContent) throws InvalidChoiceException {
        String chatID = chatManager.findChatByName(chatName).getId();
        if (chatManager.isEmpty()) {
            throw new NoDataException("chat");
        }
        if (chatManager.isChatIDNull(chatID)) {
            throw new InvalidChoiceException("chat");
        }
        for (String receiverID : chatManager.getPersonIds(chatID)){
            if (!receiverID.equals(currentUserID)){
                String messageID = messageManager.createMessage(currentUserID, receiverID, messageContent);
                chatManager.addMessageIds(chatID,messageID);
                presenter.printMessageSent();
            }
        }
    }

    /**
     * mark messages in a chat as read after OPTION 4 -- VIEW MESSAGES IN CHAT --
     */
    protected void chatViewed (String chatName) {
        for (String msgId : chatManager.findChatByName(chatName).getMessageIds()) {
            messageManager.changeStatusRead(msgId);
        }
    }

    //Currently remove the ID of this Chat from the chatList of all Person in Chat, let personIds list of this Chat be
    // new empty arraylist, and make the chatID become null.
    /**
     * Allows the user to exit from a Chat.
     * If the Chat contains <=2 people, the Chat disappears from both user's chatList, and Chat is permanently deleted.
     * If Chat contains 3 people, permanently delete Chat if there already exists private chat among rest of members, or
     * set the Chat type to Chat (not announcement Chat) otherwise.
     * If Chat contains more than 3 people, simply let the user exit Chat.
     * @param chatName name of the Chat the user wants to exit from
     */
    protected void deleteChat(String chatName){
        String chatId = chatManager.findChatByName(chatName).getId();
        ArrayList<String> personIds = chatManager.getUnknownTypeChat(chatId).getPersonIds();
        if (personIds.size() <= 2){
            for(String personId: personIds) {
                personManager.removeChat(personId, chatId);
            }
            chatManager.removeAllPersonIds(chatId);
            chatManager.nullifyChatID(chatId);
        } else if (personIds.size() == 3) {
            boolean existsPrivate = false;
            ArrayList <String> original = new ArrayList<>(personIds);
            original.remove(currentUserID);
            if (chatManager.findChat(original)!=null){existsPrivate = true;}
            personManager.removeChat(currentUserID, chatId);
            chatManager.removePersonIds(chatId, currentUserID);
            if (existsPrivate) {this.deleteChat(chatName);}
            else {chatManager.setChatTypeToChat(chatId);}
        } else {
            personManager.removeChat(currentUserID, chatId);
            chatManager.removePersonIds(chatId, currentUserID);
        }
    }

    /**
     * Add
     * @param chatName
     */



    protected void archiveChat(String chatName){
        String chatId = chatManager.findChatByName(chatName).getId();
        for (String cId : personManager.getChats(currentUserID)) {
            if (chatId.equals(cId)) {
                personManager.archiveChatByPersonId(currentUserID, chatId);
                personManager.removeChat(currentUserID, chatId);
            }
        }
    }

    protected void unArchiveChat(String chatName){
        String chatId = chatManager.findChatByName(chatName).getId();
        for (String cId : personManager.getCurrentArchivedChatList(currentUserID)) {
            if (chatId.equals(cId)) {
                personManager.removeArchiveChatByPersonId(currentUserID, chatId);
                personManager.addChat(currentUserID, chatId);
            }
        }
    }

    protected void archiveMessage(String msgId){
        personManager.addCurrentFavorites(currentUserID, msgId);
    }

    protected void unArchiveMessage(String msgId){
        personManager.removeCurrentFavorites(currentUserID, msgId);
    }

    /**
     * Gives chatNames of all Chats containing all usernames inputted
     * @param usernames Usernames contained in the target Chat that is being searched for
     * @return Arraylist of chatNames of all Chats containing usernames inputted
     */
    protected ArrayList<String> searchChatByUsernames(ArrayList<String> usernames){
        ArrayList<String> personIds = new ArrayList<>();
        for (String user: usernames){personIds.add(personManager.getCurrentUserID(user));}
        return chatManager.searchChatsContaining(personIds);}


    @Override
    public MessageMenu getPresenter() {
        return this.presenter;
    }
}
