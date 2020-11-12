package Controllers;// Programmer: Cara McNeil
// Description: Controllers.Main menu for Person.Speaker users.
// Date Created: 01/11/2020
// Date Modified: 04/11/2020


import Person.SpeakerManager;

import java.util.Scanner;

public class SpeakerController extends PersonController {
    // private EventManager eManager = new EventManager(); ??
    // private Message.Message.ChatManager cManager = new Message.Message.ChatManager();
    // private Message.MessageManager mManager = new Message.MessageManager();
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
