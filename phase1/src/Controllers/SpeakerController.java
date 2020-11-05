package Controllers;// Programmer: Cara McNeil
// Description: Controllers.Main menu for Speaker users.
// Date Created: 01/11/2020
// Date Modified: 04/11/2020


import java.util.Scanner;

public class SpeakerController extends PersonController {
    // private EventManager eManager = new EventManager(); ??
    // private ChatManager cManager = new ChatManager();
    // private MessageManager mManager = new MessageManager();
    Scanner input = new Scanner(System.in);
    int currentRequest;
    private String username;
    private String password;
    private String currentUserID;

    public SpeakerController(SpeakerManager manager) {
        super(manager);
    }

    // login()
    // createAccount()
    // getContactList()
    // addContact(String contactUsername)
    // getChats()
    // createChat(String contactUsername)
    // addMessage(String chatID, String messageContent)
    // getMessages(String contactUsername)

    @Override
    void run() {

    }

}
