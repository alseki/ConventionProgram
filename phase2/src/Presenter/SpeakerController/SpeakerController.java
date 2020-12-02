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

public class SpeakerController extends PersonController {
    private String currentUserID;
    private SpeakerManager manager;
    String[] arrMenuOptions;

    public SpeakerController(SpeakerManager manager, RoomManager rooms, EventManager events,
                             MessageManager messages, ChatManager chats, RequestManager requests) {
        super(manager, rooms, events, messages, chats, requests, 3);
        this.manager = manager;

        menuOptions.add("Event Menu"); // SpeEventController

        arrMenuOptions = new String[menuOptions.size()];
        for (int i = 0; i < menuOptions.size(); i++) {
            arrMenuOptions[i] = menuOptions.get(i);
        }
    }

    public void SpeEventMenu() {

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
