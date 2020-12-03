package View.Central.Account;

import Presenter.Central.SubMenu;
import Presenter.PersonController.MessageController;

public class MessageView implements AccountView {
    MessageController controller;

    public MessageView(SubMenu controller) {
        this.controller = (MessageController) controller;
    }
}
