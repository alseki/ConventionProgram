package View.Central.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.Central.SubMenu;

public class AttReqView implements AccountView {
    AttEventController controller;

    public AttReqView(SubMenu controller) {
        this.controller = (AttEventController) controller;
    }
}
