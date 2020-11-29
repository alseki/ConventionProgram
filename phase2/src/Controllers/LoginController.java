package Controllers;

// Programmer: Cara McNeil
// Description: All the methods that take user input in the Login Menu
// Date Created: 01/11/2020
// Date Modified: 16/11/2020

import Person.PersonManager;
import View.Login.LoginMenu;
import View.Login.iViewPane;

import java.util.Scanner;

public class LoginController implements SubMenu{
    private int currentRequest;
    private int accountChoice;
    private PersonManager manager;
    // TODO get ride of following line
    private LoginMenu presenter = new LoginMenu();
    private iViewPane viewPane;
    public String username;
    public boolean loggedIn = false;
    Scanner input = new Scanner(System.in);

    public LoginController(PersonManager manager, int accountChoice, iViewPane viewPane) {
        this.manager = manager;
        this.accountChoice = accountChoice;
        this.viewPane = viewPane;
    }

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     */
    public void menuOptions() {
        currentRequest = Integer.parseInt(presenter.printMenuOptions());
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     */
    @Override
    public void menuChoice() {
        do {
            menuOptions();
            switch (currentRequest) {
                case 0:
                    break; // The user has inputted 0
                case 1:
                    try {
                        login();
                        loggedIn = true;
                        currentRequest = 0;
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                    }
                    break;
                case 2:
                    try {
                        createAccount();
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                    }
                    break;
            }
        }
        while (currentRequest != 0);
    }

    /**
     * Prompts user to input username and password.
     */
    private void login() throws InvalidChoiceException {
        //int typePresenter = PersonController;
        presenter.printLoginPrompt();
        username = presenter.printUsernamePrompt();
        if(this.accountChoice != manager.typePerson(username)) {
            throw new InvalidChoiceException("account");
        }
        String password = presenter.printPasswordPrompt();
        if (manager.getCurrentUserID(username) != null)  {
            if(manager.confirmPassword(username, password)) {
                return;
            }
        }
        throw new InvalidChoiceException("account");
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
