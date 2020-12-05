package View.Central;

import Presenter.PersonController.LoginController;
import Presenter.PersonController.LoginMenu;
import Presenter.Exceptions.InvalidChoiceException;

import javax.swing.*;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 29 2020

// Architecture Level - UI

public class LoginView {

    private LoginController controller;
    private LoginMenu presenter;

    public LoginView(LoginController controller) {
        this.controller = controller;
        presenter =  controller.getPresenter();
    }

    /**
     * Runs this LoginView.
     * @return A valid User ID, if the user has successfully logged into the system; "0", if not
     */
    public String run() {
        int currentRequest = mainMenu();
        while (currentRequest != 0) {
            String id;
            switch (currentRequest) {
                case 1: id = logIn(); break;
                case 2: signUp();
                default: id = null;
            }
            if (id == null) {
                currentRequest = mainMenu();
            }
            else {
                return id;
            }
        }
        return "0";
    }

    /**
     * Presents the main menu, as well as buttons the User can click to choose each option
     * @return The option the User chose, as an integer value from 0-2. (0 = return to main menu, 1 = log in to
     * existing account, 2 = sign up for new account)
     */
    private Integer mainMenu() {
        Integer[] args = {0, 1, 2};
        // TODO add 3 for employee
        // TODO add 4 for admin
        StringBuilder menuOptions = new StringBuilder();
        for (String option: presenter.getMenuOptions()) {
            menuOptions.append(option);
            menuOptions.append("\n");
        }
        return JOptionPane.showOptionDialog(null, menuOptions.toString(),
                presenter.getMenuTitle(), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, args, args[0]);
    }

    /**
     * Presents the user with the ability to log into an existing account
     * @return The User ID of the account the User logs into, if they're successful; null, if not
     */
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

    /**
     * Presents the user with the ability to create a new account
     */
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
