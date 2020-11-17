package Controllers;

import Message.Chat;
import Message.ChatManager;
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
        for (String messageID: this.messageManager.getMessageIDs()){
            String formattedMessage = this.messageManager.getFormattedMessage(messageID);
            sentMessages.add(formattedMessage);
        }
        return sentMessages;
        // Let presenter show the sent messages.
    }

    /**
     * Show the chatList with its ID and participants' IDs.
     * @return ArrayList of formatted chats
     *              [ID]: [ID of the chat]\new line
     *              [Participants]: [ID of the Participants]\newline
     *              ...
     */
    private ArrayList<String> viewChats(){
        ArrayList<String> chats = new ArrayList<>();
        for (String chatID: this.chatManager.getChatIDs()){
            String formattedChat = this.chatManager.getFormattedChat(chatID);
            chats.add(formattedChat);
        }
        return chats;
        // Let presenter show the chats.
    }

    /**
     * Show the messages in one chat by chatID.
     */
    // TODO: we only have messageIDs in chat. How to get the Message????
    private String viewMessageByChat(String chatID){

        // Let presenter show the chat info.
        return null;
    }


}
