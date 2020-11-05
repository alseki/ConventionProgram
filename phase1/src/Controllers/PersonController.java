package Controllers;// Programmer: Cara McNeil
// Description: abstract main menu for other controllers to inherit from
// Date Created: 01/11/2020
// Date Modified: 04/11/2020

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

    // This should be moved to a Presenter class
    /**
     * Prints the options that are available to the current user.
     */
    /*public int mainMenu() {
        System.out.println("~Controllers.Main Menu~" + '\n');
        System.out.println("Please select from the following options:");
        System.out.println("To logout, Enter '1';");
        System.out.println("To view Contact List, Enter '2';");
        System.out.println("To add to Contact List, Enter '3';");
        System.out.println("To view Chat list, Enter '4';");
        return 0;
    }*/

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */
    
    abstract void run();
        // will use currentRequest to determine what methods to call
        // if any classes return false, needs to update the presenter accordingly

    // This should be moved to a Presenter class
    /**
     * Prints the options that are available to the current user. Implements and extends Controllers.PersonController method.
     */
    /*public int mainMenu() {
        super.mainMenu();
        System.out.println("To view a list of Convention Events, Enter '5'");
        System.out.println("To signup for an event, Enter '6'");
        System.out.println("");
    }*/

    /**
     * Prompts user to input username and password.
     * @return true iff login info corresponds with an existing Person account.
     */
    public boolean login() {
        System.out.println("Enter your username: ");
        username = input.nextLine();
        System.out.println("Enter your password: ");
        password = input.nextLine();
        // if manager.findPerson(username, password) == true
        // currentUserID = manager.getPerson(username, password)
        return false;
    }

    /**
     * Prompts user for relevant information and uses it to create a new Person account.
     * @return true iff new account has been created
     */
    public boolean createAccount() {
        System.out.println("Enter your full name: ");
        String name = input.nextLine();
        System.out.println("Enter a username for your account: ");
        String username = input.nextLine();
        System.out.println("Enter your password for your account: ");
        String password = input.nextLine();
        System.out.println("Enter your email: ");
        String email = input.nextLine();
        // manager.createAccount(name, username, password, email)
        return false;
    }

    /**
     * Get's the Person user's contactList
     * @return true iff the presenter printed a formatted contactList
     */
    public boolean getContactList() {
        // manager.getContactList(currentUserID);
        // format list
        // send the Presenter the formatted contactList to print (if empty, say so)
        return false;
    }

    /**
     * Add a contact to the Person user's contactList
     * @param contactUsername
     * @return true iff the presenter printed a formatted contactList
     */
    public boolean addContact(String contactUsername) {
        // String contactID = manager.getID(contactUsername)
        // if manager.addContact(currentUserID, contactID) and manager.addContact(contactID, currentUserID):
        // update presenter to say contact was added
        return false;
    }

    /**
     * Get's the Person user's Chats
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
     * Creates new Chat if contact is on contactList
     * @param contactUsername
     * @return true iff new Chat was created and added to user's Chat list and contact's contactList
     */
    public boolean createChat(String contactUsername) {
        // contactID = manager.getID(contactUsername)
        // if manager.checkContact(contactID)
        // String chatID = cManager.createChat(currentUserID, contactID)
        // if manager.addChat(currentUserID, chatID) and manager.addChat(contactID, chatID)
        // update presenter to say Chat was created
        return false;
    }
    
    /**
     * Creates new Message for existing Chat
     * @param chatID
     * @param messageContent
     * @return true iff new Message was created and added to Chat's messageList
     */
    public boolean addMessage(String chatID, String messageContent) {
        // String messageID = mManager.createMessage(currentUser, messageContent);
        // cManager.addMessage(chatID, messageID)
        // update presenter to say message has been sent
        return false;
    }

    /**
     * Get's the Person user's Chat messages
     * @param contactUsername
     * @return true iff presenter was updated with a formatted list of Chat messages
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
