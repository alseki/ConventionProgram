package View.Central.Account;

import Presenter.AttendeeController.AttMessageController;
import Presenter.Central.SubMenu;

public class AttMessageView implements AccountView{
    AttMessageController controller;

    public AttMessageView(SubMenu controller) {
        this.controller = (AttMessageController) controller;
    }
}
