package View.Account;

import Presenter.AttendeeController.AttMessageController;
import Presenter.Central.SubMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AttMessageView extends AccountView{
    AttMessageController controller;

    public AttMessageView(SubMenu controller) {
        super();
        this.controller = (AttMessageController) controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
