package View.Account;

import Presenter.Central.SubMenu;
import Presenter.PersonController.MessageController;

import javax.swing.*;

public class MessageView extends AccountView {
    MessageController controller;

    public MessageView(SubMenu controller) {
        super();
        this.controller = (MessageController) controller;
    }
}
