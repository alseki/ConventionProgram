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
    protected String sendMessageChoice(String ID, String content) throws InvalidChoiceException {
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
//TODO double check with Ran and other regarding what to do when the group is equal to 2 ppl or less
    //Currently remove the ID of this Chat from the chatList of all Person in Chat, let personIds list of this Chat be
    // new empty arraylist, and make the chatID become null.
    /**
     * Allows the user to exit from a Chat. If the Chat only contains two members,
     * @param chatID ID of the Chat the user wants to exit from
     */
    protected void deleteChat(String chatID){
        // FIXME
        /*if (chatManager.getChatSize(chatID) <= 2){
            for(String personId: chatManager.getPersonIds(chatID)){
                personManager.removeChat(personId, chatID);}
            chatManager.removeAllPersonIds(chatID);
            chatManager.nullifyChatID(chatID);
        }
        else {personManager.removeChat(currentUserID, chatID);
        chatManager.removePersonIds(chatID, currentUserID);}*/
    }


    protected boolean archiveChat(String chatID){
        // FIXME
        // return chatManager.archiveChat(chatID);
        return false; // TODO delete this line after above is fixed
    }

    protected boolean dearchiveChat(String chatID){
        // FIXME
        // return chatManager.dearchiveChat(chatID);
        return false; // TODO delete this line after above is fixed
    }

    @Override
    public MessageMenu getPresenter() {
        return this.presenter;
    }
}
