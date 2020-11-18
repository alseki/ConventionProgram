package Controllers;

// Programmer: Cara McNeil,
// Description: Main account page for Speaker users.
// Date Created: 01/11/2020
// Date Modified: 16/11/2020


import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.AttendeeManager;
import Person.SpeakerManager;
import Presenter.MainMenu;

import java.util.Scanner;

public class SpeakerController extends PersonController {
    private ChatManager chatManager = new ChatManager();
    private MainMenu mainMenu = new MainMenu();
    private String currentUserID;
    private SpeakerManager manager;
    private int currentRequest;
    Scanner input = new Scanner(System.in);

    public SpeakerController(SpeakerManager manager, RoomManager rooms, MessageManager messages) {
        super(manager, rooms, messages);
    }


    @Override
    public void run() {
        this.currentUserID = super.currentUserID;
        if (super.loggedIn) {
            super.run();
            do {
                mainMenu.printSpeakerMM();
                currentRequest = SubMenu.readInteger(input);

                switch (currentRequest) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(currentUserID, manager);
                        contactController.menuChoice();
                        break;
                    case 2:
                        MessageController messageController = new MessageController(currentUserID, manager, messageManager,
                                chatManager);
                        messageController.menuChoice();
                        break;
                    case 3:
                        //SpeEventController speEventController = new SpeEventController(currentUserID, manager,
                        //        messageManager, chatManager);
                        //speEventController.menuChoice();
                        break;
                }
            }
            while (currentRequest != 0);
        }
    }

}
