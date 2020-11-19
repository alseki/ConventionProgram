package Controllers;

// Programmer: Cara McNeil
// Description: All the methods that take user input in the Login Menu
// Date Created: 01/11/2020
// Date Modified: 16/11/2020

import Person.PersonManager;
import Presenter.LoginMenu;

import java.util.Scanner;

public class LoginController implements SubMenu {
    private int currentRequest;
    private PersonManager manager;
    private LoginMenu presenter = new LoginMenu();
    public String username;
    Scanner input = new Scanner(System.in);

    public LoginController(PersonManager manager) {
        this.manager = manager;
    }

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     */
    @Override
    public void menuOptions() {
        presenter.printMenuOptions();
        currentRequest = SubMenu.readInteger(input);
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
                        currentRequest = 0;
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                        break;
                    }
                case 2:
                    try {
                        createAccount();
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                        break;
                    }
            }
        }
        while (currentRequest != 0);
    }

    /**
     * Prompts user to input username and password.
     */
    private void login() throws InvalidChoiceException {
        presenter.printLoginPrompt();
        presenter.printUsernamePrompt();
        username = input.next();
        presenter.printPasswordPrompt();
        String password = input.next();
        if (manager.getCurrentUserID(username) != null)  {
            if(!manager.confirmPassword(username, password)) {
                throw new InvalidChoiceException("account");
            }
        }
    }

    /**
     * Prompts user for relevant information and uses it to create a new account.
     */
    private void createAccount() throws InvalidChoiceException {
        presenter.printCreateAccountPrompt();
        presenter.printUsernamePrompt();
        username = input.next();
        if (manager.getCurrentUserID(username) != null) {
            throw new InvalidChoiceException("username taken");
        }
        presenter.printPasswordPrompt();
        String password = input.next();
        presenter.printNamePrompt();
        String name = input.next();
        presenter.printEmailPrompt();
        String email = input.nextLine();
        input.nextLine();
        if (manager.createAccount(name, username, password, email)) {
            presenter.printAccountCreationSuccessful();
        }
        throw new InvalidChoiceException("username");
    }
}
