package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.Central.SubMenu;

import javax.swing.*;

public class AttEventView extends AccountView {
    AttEventController controller;

    public AttEventView(SubMenu controller) {
        super();
        this.controller = (AttEventController) controller;
    }
}
