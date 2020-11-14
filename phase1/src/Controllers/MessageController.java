package Controllers;

import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Presenter.MessageMenu;

import java.util.Scanner;

public class MessageController implements SubMenu {
    private int currentRequest;
    private PersonManager personManager;
    private MessageManager messageManager;
    private ChatManager chatManager;
    private MessageMenu presenter = new MessageMenu();
    Scanner input = new Scanner(System.in);

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
     * @return true iff new Chat was created and added to user's Chat list and contact's contactList
     */
    private boolean createChat(String contactUsername) {
        return true;
    }

    /**
     * Get's a list of contacts the user has Chats with
     * @return true iff the presenter printed a list of contacts the user has Chats with
     */
    public boolean getChats() {
        return true;
    }


    /**
     * Creates new Message for existing Chat
     * @param chatID The chatID of the Chat the current user want's to send a Message to
     * @param messageContent The contents of the message the current user wants to send
     * @return true iff new Message was created and added to Chat's messageList
     */
    public boolean addMessage(String chatID, String messageContent) {
        return true;
    }

    /**
     * Get's the current user's Chat messages between contactUsername
     * @param contactUsername The username of the current user's requested contact messages
     * @return true iff presenter was updated with a formatted list of Message.Message.Chat messages
     */
    public boolean getMessages(String contactUsername) {
        return true;
    }
}
