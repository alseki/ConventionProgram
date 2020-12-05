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


    // Option 5

    /**
     * Creates new Message for existing Chat (1 to 1 chat or group chat both use this.)
     * @param chatID The chatID of the Chat the current user want's to send a Message to
     * @param messageContent The contents of the message the current user wants to send
     */
    protected void sendMessage(String chatID, String messageContent) throws InvalidChoiceException {
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
            }
        }
    }

    @Override
    public MessageMenu getPresenter() {
        return this.presenter;
    }
}
