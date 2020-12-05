package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.Central.SubMenu;

import javax.swing.*;

public class AttReqView extends AccountView {
    AttEventController controller;

    public AttReqView(SubMenu controller) {
        super();
        this.controller = (AttEventController) controller;
    }
}
