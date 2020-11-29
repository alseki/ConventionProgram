package Presenter.Central;

// Programmers: Cara McNeil, Sarah Kronenfeld
// Description: All the methods that take user input in the Login Menu
// Date Created: 01/11/2020
// Date Modified: 29/11/2020

import Presenter.InvalidChoiceException;
import Presenter.NoDataException;
import Presenter.OverwritingException;
import Person.PersonManager;

import java.util.Scanner;

public class LoginController implements SubMenu {

    private int accountChoice;
    private PersonManager manager;
    private LoginMenu presenter;
    public String username;

    public LoginController(PersonManager manager, int accountChoice) {
        this.manager = manager;
        this.accountChoice = accountChoice;
        presenter = new LoginMenu(accountChoice);
    }

    public LoginMenu getPresenter() {
        return this.presenter;
    }

    /**
     * Checks to see if the user has input a valid username
     */
    private String login (String username, String password) throws InvalidChoiceException {
        if(this.accountChoice != manager.typePerson(username)) {
            throw new InvalidChoiceException("username");
        }

        if (manager.getCurrentUserID(username) != null)  {
            if(manager.confirmPassword(username, password)) {
                return manager.getCurrentUserID(username);
            }
        }

        throw new NoDataException("account");
    }

    /**
     * Prompts user for relevant information and uses it to create a new account.
     */
    private void createAccount() throws InvalidChoiceException {
        presenter.printCreateAccountPrompt();
        username = presenter.printUsernamePrompt();
        if(username.contains(",")) {
            throw new InvalidChoiceException("username possible. Try again with NO comma(s)");
        }
        if (manager.getCurrentUserID(username) != null) {
            throw new OverwritingException("username");
        }
        String password = presenter.printPasswordPrompt();
        String name = presenter.printNamePrompt();
        String email = presenter.printEmailPrompt();
        if (manager.createAccount(name, username, password, email)) {
            presenter.printAccountCreationSuccessful();
        }
    }
}
