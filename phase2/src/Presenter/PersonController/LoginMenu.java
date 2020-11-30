package Presenter.PersonController;

// Programmers: Cara McNeil, Sarah Kronenfeld
// Description: Prints information pertaining to a user's login information
// Date Created: 11/11/2020
// Date Modified: 29/11/2020

import Presenter.Central.SubMenuPrinter;

public class LoginMenu implements SubMenuPrinter {

    private final int accountChoice;

    public LoginMenu(int accountChoice) {
        this.accountChoice = accountChoice;
    }

    /**
     * Gives the type of the account the user is trying to use to log in
     */
    private String getAccountType() {
        switch (accountChoice) {
            case 1: return "Attendee";
            case 2: return "Organizer";
            case 3: return "Speaker";
            default: return "";
        }
    }

    /**
     * Prints a title that says the user is logging in
     */
    public String loginMessageTitle() {
        return "Logging in as " + getAccountType();
    }

    /**
     * Prints a title that says the user is logging in
     */
    public String signupMessageTitle() {
        return "Creating " + getAccountType() + " account";
    }

    /**
     * Prints a title that says the user is logging in
     */
    public String mainMenuTitle() {
        return getAccountType() + " login menu";
    }

    /**
     * Prints the options for this menu.
     */
    public String printMenuOptions() {
        return "\nTo return to start page, Enter '0'." + "\nTo Login, Enter '1'."
                + "\nTo Create a new account, Enter '2'.";
    }

    /**
     * Prompts user to enter username
     */
    public String printUsernamePrompt() {
        return "Please enter username: ";
    }

    /**
     * Prompts user to enter password
     */
    public String printPasswordPrompt() {
        return "Please enter password: ";
    }

    /**
     * Prompts user to enter name
     */
    public String printNamePrompt() {
        return "Please enter your full name: ";
    }

    /**
     * Prompts user to enter email
     */
    public String printEmailPrompt() {
        return "Please enter email address: ";
    }

    /**
     * Returns a written confirmation that an account was created.
     */
    public String printAccountCreationSuccessful() {
        return "Account creation successful! Redirecting back to account \nMain Menu...";
    }


    /**
     * Returns a written confirmation that Login was successful
     */
    public String printLoginSuccessful() {
        return "Login successful! Redirecting back to account \nMain Menu...";
    }

}