package Controllers;

// Programmer: Cara McNeil
// Description: abstract main menu for other controllers to inherit from
// Date Created: 01/11/2020
// Date Modified: 11/11/2020

import Person.PersonManager;

import java.util.Scanner;

abstract public class PersonController {
    Scanner input = new Scanner(System.in);
    private String username;
    private String password;
    private String currentUserID;
    private PersonManager manager;


    public PersonController(PersonManager manager) {
        this.manager = manager;
    }

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */

    abstract void run();
    // will use currentRequest to determine what methods to call
    // if any classes return false, needs to update the presenter accordingly

    /**
     * Prompts user to input username and password.
     *
     * @return true iff login info corresponds with an existing Person.Person account.
     */

    public boolean login() {
        //if (manager.findPerson(username, password)) {
         //   currentUserID = PersonManager.getPerson(username, password).id;
        //}
        return false;
    }

    /**
     * Prompts user for relevant information and uses it to create a new Person.Person account.
     *
     * @return true iff new account has been created
     */
    public boolean createAccount() {
        //manager.createAccount(name, username, password, email);
        return false;
    }

    /**
     * Get's the Person.Person user's contactList
     *
     * @return true iff the presenter printed a formatted contactList
     */
    public boolean getContactList() {
        //manager.getPerson(currentUserID).getContactList();
        // format list ? to what end ?
        // send the Presenter the formatted contactList to print (if empty, say so)
        return false;

    }

    /**
     * Add a contact to the Person.Person user's contactList
     *
     * @param contactUsername
     * @return true iff the presenter printed a formatted contactList
     */
    public boolean addContact(String contactUsername) {
        //String contactID = manager.getCurrentUserID(contactUsername);
        //if ((manager.addContact(currentUserID, contactID) && manager.addContact(contactID, currentUserID))) {
            // update presenter to say contact was added
          //  return true;
        //}
        return false;
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
