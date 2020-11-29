package Presenter.SpeakerController;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: Main account page for SpeakerController users.
// Date Created: 01/11/2020
// Date Modified: 19/11/2020


import Presenter.PersonController.ContactController;
import Presenter.PersonController.MessageController;
import Presenter.PersonController.PersonController;
import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.SpeakerManager;
import Request.RequestManager;
import View.Central.Account;

public class SpeakerController extends PersonController {
    private Account mainMenu = new Account();
    private String currentUserID;
    private SpeakerManager manager;

    public SpeakerController(SpeakerManager manager, RoomManager rooms, EventManager events,
                             MessageManager messages, ChatManager chats, RequestManager requests) {
        super(manager, rooms, events, messages, chats, requests, 3);
        this.manager = manager;
    }


    public void run() {
        this.currentUserID = super.currentUserID;
        if (super.loggedIn) {
            do {
                //currentRequest = SubMenu.readInteger(input);

                switch (1) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(this, currentUserID);
                        contactController.menuChoice();
                        break;
                    case 2:
                        MessageController messageController = new MessageController(this, currentUserID);
                        messageController.menuChoice();
                        break;
                    case 3:
                        SpeEventController speEventController = new SpeEventController(this, manager,
                                currentUserID);
                        speEventController.menuChoice();
                        break;
                }
            }
            while (true);
        }
    }

}
