package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.Central.SubMenu;

public class AttEventView implements AccountView {
    AttEventController controller;

    public AttEventView(SubMenu controller) {
        this.controller = (AttEventController) controller;
    }
}
