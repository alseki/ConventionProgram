package Presenter.PersonController;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: abstract main account page for other controllers to inherit from
// Date Created: 01/11/2020
// Date Modified: 29/11/2020


import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Presenter.AttendeeController.AttendeeController;
import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrganizerController;
import Request.RequestManager;

abstract public class PersonController extends SubMenu {
    public String currentUserID;
    private PersonManager manager;
    private int accountChoice;
    public boolean loggedIn =  false;


    public PersonController(PersonManager manager, RoomManager roomManager, EventManager eventManager,
                            MessageManager messageManager, ChatManager chatManager, RequestManager requestManager, int accountChoice) {
        super(roomManager, eventManager, manager, messageManager, chatManager, requestManager);
        this.manager = manager;
        this.accountChoice = accountChoice;
    }


    public PersonController(SubMenu submenu, int accountChoice) {
        super(submenu);
        this.manager = personManager;
        this.accountChoice = accountChoice;
    }

    public LoginController logIn() {
        return new LoginController(this, accountChoice);
    }
}
