package Controllers;

import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;

public class MessageController implements SubMenu{
    private PersonManager personManager;
    private MessageManager messageManager;
    private ChatManager chatManager;

    public MessageController(PersonManager pManager, MessageManager mManager, ChatManager cManager) {
        this.personManager = pManager;
        this.messageManager = mManager;
        this.chatManager = cManager;
    }


    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     * @return true iff choice was inputted
     */
    @Override
    public boolean menuOptions() {
        // MessageMenu.menuOptions()
        // choice = input.NextLine()

        return true;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        // menuOptions();
        // while (choice != 0)
        // do {
        // switch statement to decide method
        // }
        return true;
    }

    /**
     * Creates new Message.Message.Chat if contact is on contactList
     * @param contactUsername
     * @return true iff new Message.Message.Chat was created and added to user's Message.Message.Chat list and contact's contactList
     */
    public boolean createChat(String contactUsername) {
        //String contactID = manager.getCurrentUserID(contactUsername);
        //if (manager.doubleContact(username, contactID)) {
        // String chatID = cManager.createChat(currentUserID, contactID)
        //if (manager.addChat(currentUserID, chatID) && manager.addChat(contactID, chatID)) {
        // update presenter to say Message.Message.Chat was created
        //return true;
        //}
        //}
        return false;
    }

    /**
     * Get's the Person.Person user's Chats
     * @return true iff the presenter printed a formatted list of Chats
     */
    public boolean getChats() {
        // List Chats = manager.getChatIDs(currentUserID);
        // List formattedChats;
        // for item in Chats:
        // formattedChats.add(cManager.format(chatID))
        // update presenter with the formatted Chats, i.e. contact usernames they have Chats with, to print
        // (if empty, say so)
        return false;
    }


    /**
     * Creates new Message.Message for existing Message.Message.Chat
     * @param chatID The chatID of the Chat the current user want's to send a Message to
     * @param messageContent The contents of the message the current user wants to send
     * @return true iff new Message.Message was created and added to Message.Message.Chat's messageList
     */
    public boolean addMessage(String chatID, String messageContent) {
        // String messageID = mManager.createMessage(currentUser, messageContent);
        // cManager.addMessage(chatID, messageID)
        // update presenter to say message has been sent
        return false;
    }

    /**
     * Get's the Person.Person user's Message.Message.Chat messages
     * @param contactUsername The username of the current user's requested contact messages
     * @return true iff presenter was updated with a formatted list of Message.Message.Chat messages
     */
    public boolean getMessages(String contactUsername) {
        // chatID = cManager.getChatID(currentUserID)
        // if chatID in manager.getChats(currentUserID)
        // List messageIDs = cManager.getMessages(chatID);
        // List chatMessages;
        // for item in messageIDs:
        // chatMessages.add(mManager.getFormattedMessage(item))
        // send the Presenter the formatted chatMessages to print
        return false;
    }
}
