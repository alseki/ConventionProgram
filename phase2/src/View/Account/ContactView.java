package View.Account;

import Presenter.Central.SubMenu;
import Presenter.PersonController.ContactController;

import javax.swing.*;

public class ContactView extends AccountView {
    ContactController controller;

    public ContactView(SubMenu controller) {
        super();
        this.controller = (ContactController) controller;
    }
}
