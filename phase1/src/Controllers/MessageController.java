package Controllers;

// Programmer: Ran Yi, Sarah Kronenfeld
// Description: For current User to view chat and message, create chat and send message.
// Date Modified: 18/11/2020

import Message.Chat;
import Message.AnnouncementChat;
import Message.ChatManager;
import Message.Message;
import Message.MessageManager;
import Person.PersonManager;
import Presenter.MessageMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MessageController implements SubMenu {
    private int currentRequest;
    private String currentUserID;
    private PersonManager personManager;
    private MessageManager messageManager;
    private ChatManager chatManager;
    private MessageMenu presenter;
    Scanner input = new Scanner(System.in);

    public MessageController(String currentUserID, PersonManager pManager, MessageManager mManager,
                             ChatManager cManager) {
        this.currentUserID = currentUserID;
        this.personManager = pManager;
        this.messageManager = mManager;
        this.chatManager = cManager;
        presenter = new MessageMenu(pManager, mManager, cManager);
    }


    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     */
    @Override
    public void menuOptions() {
        presenter.printMenuOptions();
        currentRequest = SubMenu.readInteger(input);
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     */
    @Override
    public void menuChoice() {
        do {
            menuOptions();
            switch (currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1: //Check your inbox
                    inBox();
                    break;
                case 2: //Check your sent box
                    sentBox();
                    break;
                case 3: //View the chat list
                    viewChats(chatManager.getChatIDs(currentUserID));
                    break;
                case 4: //View the announcement chat list
                    viewChats(chatManager.getAnnouncementChatIDs(currentUserID));
                    break;
                case 5: //View the messages in a chat
                    displayChat("");
                    break;
                case 6: //View the announcements in an announcement chat
                    displayChat("Announcement");
                case 7: //Create a chat
                    createChatChoice();
                    break;
                case 8: //Create a group chat
                    createGroupchatChoice();
                    break;
                case 9: //Send a message
                    sendMessageChoice();
                    break;
            }
        }
        while (currentRequest != 0);
    }

    /**
     * Displays a chat
     * @param type The type of chat it is (e.g. "" for a normal chat, "Announcement" for an AnnouncementChat)
     */
    private void displayChat(String type) {
        presenter.printChatIdPrompt(type);
        try {
            presenter.printChat(SubMenu.readInput(input));
        } catch (InvalidChoiceException e) {
            presenter.printException(e);
        }
    }

    /**
     * Creates a new Chat
     */
    private void createChatChoice() {
        presenter.printContactUsernamePrompt();
        try {
            String chatID = createChat(SubMenu.readInput(input));
            presenter.printChatCreated(chatID);
            presenter.printJobDone();
        } catch (InvalidChoiceException e) {
            presenter.printException(e);
        }
    }


    /**
     * Creates new Chat if contact is on contactList
     * @param contactUsername the username of the contact the current user wants create a Chat with
     * @return chatID iff new Chat was created and added to user's Chat list and contact's contactList
     */

    private String createChat(String contactUsername) throws InvalidChoiceException {
        String contactID = personManager.getCurrentUserID(contactUsername);
        if (contactID == null) {
            throw new InvalidChoiceException("user");
        }
        if (chatManager.existChat(currentUserID, contactID)){
            String chatID = chatManager.findChat(currentUserID, contactID);
            // presenter: the chat already exists
            return chatID;
        } else {
            String chatID = chatManager.createChat(currentUserID, contactID);
            return chatID;
        }
    }

    /**
     * Creates a new GroupChat
     */
    private void createGroupchatChoice() {
        presenter.printContactUsernamesPrompt();
        String contacts = SubMenu.readInput(input);
        String[] a = contacts.split(",");
        List<String> b = Arrays.asList(a);
        ArrayList<String> contactlist = new ArrayList<>(b);
        try {
            String groupChatID = createGroupChat(contactlist);
            presenter.printChatCreated(groupChatID);
            presenter.printJobDone();
        } catch (InvalidChoiceException e) {
            presenter.printException(e);
        }
    }

    /**
     * Create a new group chat if contacts are in this user's contactlist.
     * @param contactsUsernames the ArrayList of contacts' usernames.
     * @return the chatID.
     */
    private String createGroupChat(ArrayList<String> contactsUsernames) throws InvalidChoiceException {
        ArrayList<String> contactIDs = new ArrayList<>();
        for (String receiver : contactsUsernames){
            String contactID = personManager.getCurrentUserID(receiver);
            if (contactID == null) {
                throw new InvalidChoiceException("user");
            }
            contactIDs.add(contactID);
        }
        if (this.chatManager.existChat(currentUserID, contactIDs)){
            String chatID = chatManager.findChat(currentUserID, contactIDs);
            // presenter: the chat already exists.
            return chatID;
        } else {
            String chatID = chatManager.createChat(currentUserID, contactIDs);
            return chatID;
        }
    }


    /**
     * Sends a new Message
     */
    private void sendMessageChoice() {
        presenter.printChatIdMessagePrompt();
        String chatId = SubMenu.readInput(input);
        presenter.printContentPrompt();
        String content = SubMenu.readInput(input);
        try {
            sendMessage(chatId, content);
            presenter.printJobDone();
        } catch (InvalidChoiceException e) {
            presenter.printException(e);
        }
    }

    /**
     * Creates new Message for existing Chat (1 to 1 chat or group chat both use this.)
     * @param chatID The chatID of the Chat the current user want's to send a Message to
     * @param messageContent The contents of the message the current user wants to send
     */
    public void sendMessage(String chatID, String messageContent) throws InvalidChoiceException {
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

    /**
     * Show all the messages this user sent in presenter, **sorted by datetime.
     */
    private void sentBox(){
        try {
            ArrayList<String> sentMessages = new ArrayList<>();
            for (String message: messageManager.getMessageIDs()){
                if (messageManager.getSenderID(message).equals(currentUserID)){
                    sentMessages.add(message);
                }
            }

            presenter.printList(presenter.formatMessages(sentMessages), "message");
        } catch (NoDataException e) {
            presenter.printException(e);
        }
    }

    /**
     * Show all the messages this user received in presenter, **sorted by datetime.
     */
    private void inBox(){
        try {
            ArrayList<String> receivedMessages = new ArrayList<>();
            for (String message: messageManager.getMessageIDs()){
                if (messageManager.getRecipientId(message).equals(currentUserID)){
                    receivedMessages.add(message);
                }
            }

            presenter.printList(presenter.formatMessages(receivedMessages), "message");
        } catch (NoDataException e) {
            presenter.printException(e);
        }
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
    private void viewChats(ArrayList<String> chatIDs) {
        try {
            presenter.printFormattedChatList(chatIDs);

        } catch (NoDataException e) {
            presenter.printException(e);
        }
    }


}
