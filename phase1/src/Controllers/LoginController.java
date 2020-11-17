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
     * @return true iff choice was inputted
     */
    @Override
    public boolean menuOptions() {
        presenter.printMenuOptions();
        currentRequest = input.nextInt();
        presenter.printSkipLine();
        return true;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        do {
            menuOptions();
            switch (currentRequest) {
                case 0:
                    return false; // The user has inputted 0
                case 1:
                    if (login()) {
                        currentRequest = 0; // Login was successful,
                        break;
                    }
                    else {
                        System.out.println("error loggin in"); // FIXME this should be some kind of exception
                        break;
                    }
                case 2:
                    if (createAccount()) {
                        break;
                    }
                    else{
                        System.out.println("error creating account"); // FIXME this should be some kind of exception
                        break;
                    }
            }
        }
        while (currentRequest != 0);
        return true;
    }

    /**
     * Prompts user to input username and password.
     * @return true iff login info corresponds with an existing Person.Person account.
     */
    private boolean login() {
        presenter.printLoginPrompt();
        presenter.printUsernamePrompt();
        username = input.next();
        presenter.printPasswordPrompt();
        String password = input.next();
        if (manager.getCurrentUserID(username) != null)  {
            if(manager.confirmPassword(username, password)) {
                return true;
            }
        }
        presenter.printSkipLine();
        return false;
    }

    /**
     * Prompts user for relevant information and uses it to create a new account.
     * @return true iff new account has been created
     */
    private boolean createAccount() {
        presenter.printCreateAccountPrompt();
        presenter.printUsernamePrompt();
        username = input.next();
        presenter.printPasswordPrompt();
        String password = input.next();
        presenter.printNamePrompt();
        String name = input.next();
        presenter.printEmailPrompt();
        String email = input.nextLine();
        input.nextLine();
        presenter.printSkipLine();
        if (manager.createAccount(name, username, password, email)) {
            presenter.printAccountCreationSuccessful();
            return true;
        }
        return false;
    }
}
