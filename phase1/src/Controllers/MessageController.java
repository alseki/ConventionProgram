package Controllers;

// Programmer: Ran Yi
// Description: For current User to
// Date Modified: 18/11/2020

import Message.Chat;
import Message.ChatManager;
import Message.Message;
import Message.MessageManager;
import Person.PersonManager;
import Presenter.MessageMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class MessageController implements SubMenu {
    private int currentRequest;
    private String currentUserID;
    private PersonManager personManager;
    private MessageManager messageManager;
    private ChatManager chatManager;
    private MessageMenu presenter = new MessageMenu();
    Scanner input = new Scanner(System.in);

    public MessageController(String currentUserID, PersonManager pManager, MessageManager mManager,
                             ChatManager cManager) {
        this.currentUserID = currentUserID;
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
        presenter.printMenuOptions();
        // TODO update presenter class with a print statement for each option
        currentRequest = input.nextInt();
        return true;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        do {
            menuOptions();
            // TODO add switch statement to call the methods that correspond with currentRequest
            switch (currentRequest) {
                case 0:
                    return false; // The user has inputted 0
                case 1:
                    presenter.printChat(this.viewChats());
                case 2:

            }
        }
        while (currentRequest != 0);
        return true;
    }

    // TODO change, delete and/or add to the methods below

    /**
     * Creates new Chat if contact is on contactList
     * @param contactUsername the username of the contact the current user wants create a Chat with
     * @return chatID iff new Chat was created and added to user's Chat list and contact's contactList
     */
    // TODO: here we make this in two steps: 1. check existence return boolean 2. find the chat return String
    // TODO: 2 steps have duplicated bodies
    // TODO: "if contact is on contactlist."

    private String createChat(String contactUsername) {
        String contactID = this.personManager.getCurrentUserID(contactUsername);
        if (this.chatManager.existChat(currentUserID, contactID)){
            String chatID = this.chatManager.findChat(currentUserID, contactID);
            // presenter: the chat is already exist. chatID is:...
            return chatID;
        } else {
            String chatID = this.chatManager.createChat(currentUserID, contactID);
            // presenter: the chat is created. chatID is:...
            return chatID;
        }
    }

    /**
     * Create a new group chat if contacts are in this user's contactlist.
     * @param contactsUsernames the ArrayList of contacts' usernames.
     * @return the chatID.
     */
    private String createGroupChat(ArrayList<String> contactsUsernames){
        ArrayList<String> contactIDs = new ArrayList<String>();
        for (String receiver : contactsUsernames){
            String contactID = this.personManager.getCurrentUserID(receiver);
            contactIDs.add(contactID);
        }
        if (this.chatManager.existChat(currentUserID, contactIDs)){
            String chatID = this.chatManager.findChat(currentUserID, contactIDs);
            // presenter: the chat is already exist. chatID is:...
            return chatID;
        } else {
            String chatID = this.chatManager.createChat(currentUserID, contactIDs);
            // presenter: the chat is created. chatID is:...
            return chatID;
        }
    }


    /**
     * Creates new Message for existing Chat (1 to 1 chat or group chat both use this.)
     * @param chatID The chatID of the Chat the current user want's to send a Message to
     * @param messageContent The contents of the message the current user wants to send
     * @return true iff new Message was created and added to Chat's messageList
     */
    public boolean sendMessage(String chatID, String messageContent) {
        Chat currentChat = this.chatManager.getChat(chatID);
        for (String receiverID : currentChat.getPersonIds()){
            if (!receiverID.equals(currentUserID)){
                String messageID = this.messageManager.createMessage(currentUserID, receiverID, messageContent);
                currentChat.addMessageIds(messageID);
            }
        }
        // presenter: message sent in chatID.
        return true;
    }

    /**
     * Show all the messages this user sent in presenter, **sorted by datetime.
     * @return ArrayList of formatted messages.
     */
    private ArrayList<String> sentBox(){
        ArrayList<String> sentMessages = new ArrayList<>();
        for (Message message: this.messageManager.getMessageList()){
            if (message.getSenderId().equals(currentUserID)){
                String formattedMessage = this.messageManager.getFormattedMessage(message.getSenderId());
                sentMessages.add(formattedMessage);
            }
        }
        return sentMessages;
        // Let presenter show the sent messages.
    }

    /**
     * Show all the messages this user received in presenter, **sorted by datetime.
     * @return ArrayList of formatted messages.
     */
    private ArrayList<String> inBox(){
        ArrayList<String> receivedMessages = new ArrayList<>();
        for (Message message: this.messageManager.getMessageList()){
            if (message.getRecipientId().equals(currentUserID)){
                String formattedMessage = this.messageManager.getFormattedMessage(message.getSenderId());
                receivedMessages.add(formattedMessage);
            }
        }
        return receivedMessages;
        // Let presenter show the sent messages.
    }

    /**
     * Show the chatList with this User inside with its ID and participants' IDs.
     * @return ArrayList of formatted chats
     *              [ID]: [ID of the chat]\new line
     *              [Participants]: [ID of the Participants]\newline
     *              [ID]: [ID of the chat]\new line
     *              [Participants]: [ID of the Participants]\newline
     *              ...
     */
    private ArrayList<String> viewChats(){
        ArrayList<String> chats = new ArrayList<>();
        for (Chat c : this.chatManager.getChatsList()) {
            for (String personID : c.getPersonIds()) {
                if (personID.equals(currentUserID)) {
                    String formattedAnChat = this.chatManager.getFormattedChat(c.getId());
                    chats.add(formattedAnChat);
                }
            }
        }
        return chats;
        // Let presenter show the chats.
    }

    /**
     * Show the messages in one chat by chatID.
     */
    private ArrayList<String> viewMessageByChat(String chatID){
        ArrayList<String> messageIDs = this.chatManager.getChat(chatID).getMessageIds();
        ArrayList<String> messageInChat = new ArrayList<>();
        for (String mID : messageIDs) {
            for (Message m : this.messageManager.getMessageList()){
                if (mID.equals(m.getMessageId())) {
                    messageInChat.add(this.messageManager.getFormattedMessage(mID));
                }
            }
        }
        // Let presenter show the formatted messages in this chat.
        return messageInChat;
    }

    /**
     * View announcement chats in formatted.
     *  Show the chatList with its ID and participants' IDs.
     * @return ArrayList of formatted chats
     *                   [ID]: [ID of the chat]\new line
     *                   [Participants]: [ID of the Participants]\newline
     *                   [ID]: [ID of the chat]\new line
     *                   [Participants]: [ID of the Participants]\newline
     */
    private ArrayList<String> viewAnnouncementChat(){
        ArrayList<String> aChats = new ArrayList<>();
        for (Chat ac : this.chatManager.getAnChatsList()) {
            for (String personID : ac.getPersonIds()) {
                if (personID.equals(currentUserID)) {
                    String formattedAnChat = this.chatManager.getFormattedAnChat(ac.getId());
                    aChats.add(formattedAnChat);
                }
            }
        }
        return aChats;
        // Let presenter show the chats.
    }


}
