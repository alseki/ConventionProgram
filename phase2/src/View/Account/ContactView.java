package View.Account;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.ContactMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ContactView extends AccountView {
    ContactController controller;
    ContactMenu presenter;

    public ContactView(SubMenu controller) {
        super();
        this.controller = (ContactController) controller;
        this.presenter = (ContactMenu) controller.getPresenter();
    }

    /**
     * Calls the Contact Controller to retrieve the current user's contact list
     * @return a non-empty array of strings that represent the user's contacts
     */
    private String[] getContactList() {
        try {
            return controller.getContactList();
        } catch (InvalidChoiceException e) {
            JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), presenter.printException(e),
                    JOptionPane.DEFAULT_OPTION);
        }
        return null;
    }

    /**
     * Calls the Contact Controller to try to add a contact to the user's contact list
     */
    private void addContact() {
        String contactUsername = ""; // FIXME set to user's input
        try {
            controller.addContact(contactUsername);
        } catch (InvalidChoiceException e) {
            JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), presenter.printException(e),
                    JOptionPane.DEFAULT_OPTION);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
