package View.Account;

import Presenter.Central.SubMenu;
import Presenter.PersonController.ContactController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ContactView extends AccountView {
    ContactController controller;

    public ContactView(SubMenu controller) {
        super();
        this.controller = (ContactController) controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
