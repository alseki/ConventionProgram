package View.Account;

import Presenter.Central.SubMenu;
import Presenter.PersonController.ContactController;

public class ContactView implements AccountView {
    ContactController controller;

    public ContactView(SubMenu controller) {
        this.controller = (ContactController) controller;
    }
}
