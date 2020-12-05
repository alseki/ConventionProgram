package View.Account;

import Presenter.AttendeeController.AttMessageController;
import Presenter.Central.SubMenu;

import javax.swing.*;

public class AttMessageView extends AccountView{
    AttMessageController controller;

    public AttMessageView(SubMenu controller) {
        super();
        this.controller = (AttMessageController) controller;
    }
}
