package View.Central;

import Person.PersonManager;
import Presenter.PersonController.LoginController;
import Presenter.PersonController.LoginMenu;
import Presenter.InvalidChoiceException;

import javax.swing.*;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 29 2020

// Architecture Level - UI

public class LoginView {

    private LoginController controller;
    private LoginMenu presenter;

    public void LoginView(LoginController controller) {
        this.controller = controller;
        presenter =  controller.getPresenter();
    }

    public String run() {
        int currentRequest = mainMenu();
        while (currentRequest != 0) {
            String id;
            switch (currentRequest) {
                case 1: id = logIn(); break;
                case 2: signUp();
                default: id = "";
            }
            if (id.equals("")) {
                currentRequest = mainMenu();
            }
            else {
                return id;
            }
        }
        return "";
    }

    private Integer mainMenu() {
        Integer[] args = {0, 1, 2};
        return JOptionPane.showOptionDialog(null, presenter.printMenuOptions(),
                presenter.mainMenuTitle(), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, args, args[0]);
    }

    private String logIn() {
        try {
            String username = JOptionPane.showInputDialog(null, presenter.printUsernamePrompt(),
                    presenter.loginMessageTitle(), JOptionPane.PLAIN_MESSAGE);
            String password = JOptionPane.showInputDialog(null, presenter.printPasswordPrompt(),
                    presenter.loginMessageTitle(), JOptionPane.PLAIN_MESSAGE);

            String personID = controller.login(username, password);
            JOptionPane.showConfirmDialog(null, presenter.loginMessageTitle(),
                    presenter.printLoginSuccessful(), JOptionPane.DEFAULT_OPTION);
            return personID;
        }

        catch (InvalidChoiceException e) {
            JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), presenter.printException(e),
                    JOptionPane.DEFAULT_OPTION);
            return null;
        }
    }

    private void signUp() {
        try {
            String username = JOptionPane.showInputDialog(null, presenter.printUsernamePrompt(),
                    presenter.signupMessageTitle(), JOptionPane.PLAIN_MESSAGE);
            String password = JOptionPane.showInputDialog(null, presenter.printPasswordPrompt(),
                    presenter.signupMessageTitle(), JOptionPane.PLAIN_MESSAGE);
            String name = JOptionPane.showInputDialog(null, presenter.printNamePrompt(),
                    presenter.signupMessageTitle(), JOptionPane.PLAIN_MESSAGE);
            String email = JOptionPane.showInputDialog(null, presenter.printEmailPrompt(),
                    presenter.signupMessageTitle(), JOptionPane.PLAIN_MESSAGE);

            controller.createAccount(username, password, name, email);
            JOptionPane.showConfirmDialog(null, presenter.signupMessageTitle(),
                    presenter.printAccountCreationSuccessful(), JOptionPane.DEFAULT_OPTION);
        }

        catch (InvalidChoiceException e) {
            JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), presenter.printException(e),
                    JOptionPane.DEFAULT_OPTION);
        }

    }

}
