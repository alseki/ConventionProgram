package Presenter.PersonController;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: abstract main account page for other controllers to inherit from
// Date Created: 01/11/2020
// Date Modified: 02/12/2020


import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Request.RequestManager;

abstract public class PersonController extends SubMenu implements SubMenuPrinter {
    protected String currentUserID;
    private int accountChoice;
    public boolean loggedIn =  false;
    public String[] options;


    public PersonController(PersonManager manager, RoomManager roomManager, EventManager eventManager,
                            MessageManager messageManager, ChatManager chatManager, RequestManager requestManager, int accountChoice) {
        super(roomManager, eventManager, manager, messageManager, chatManager, requestManager);
        this.accountChoice = accountChoice;
    }


    public PersonController(SubMenu submenu, int accountChoice) {
        super(submenu);
        this.accountChoice = accountChoice;
    }

    public LoginController getLogin() {
        return new LoginController(this, accountChoice);
    }

    public void logIn(String currentUserID) {
        if (!currentUserID.equals("0")) {
            this.currentUserID = currentUserID;
            loggedIn = true;
        }
    }

    public abstract SubMenu createController(String choice);

    @Override
    public String getMenuTitle() {
        return "----- Main Menu -----";
    }

    @Override
    public String[] getMenuOptions() {
        options  = new String[2];
        options[0] = "Contact Menu";
        options[1] = "Message Menu";
        return options;
    }
}
