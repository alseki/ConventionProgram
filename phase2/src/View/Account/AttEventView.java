package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.Central.SubMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AttEventView extends AccountView {
    AttEventController controller;

    public AttEventView(SubMenu controller) {
        super();
        this.controller = (AttEventController) controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
