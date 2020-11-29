package Presenter.Central;

// Programmers: Cara McNeil, Sarah Kronenfeld
// Description: Prints information pertaining to a user's login information
// Date Created: 11/11/2020
// Date Modified: 29/11/2020

import javax.swing.*;

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
        return JOptionPane.showInputDialog(null, "Enter username:",
                "[User Type] Login", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompts user to enter password
     */
    public String printPasswordPrompt() {
        return JOptionPane.showInputDialog(null, "Enter password:",
                "[User Type] Login", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompts user to enter name
     */
    public String printNamePrompt() {
        return JOptionPane.showInputDialog(null, "Enter full name: ", "[User Type] Login",
                JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompts user to enter email
     */
    public String printEmailPrompt() {
        return JOptionPane.showInputDialog(null, "Enter email address: ",
                "[User Type] Login", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prints instructions for user to create account
     */
    public void printCreateAccountPrompt() {
        JOptionPane.showMessageDialog(null, "\nTo create an account, select a username and " +
                        "password, then enter your full name and email address. ", "[User Type] Create Account",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Prints confirmation that an account was created.
     */
    public void printAccountCreationSuccessful() {
        JOptionPane.showMessageDialog(null, "Account creation successful! Redirecting to " +
                "Login Menu...\n", "[User Type] Create Account", JOptionPane.PLAIN_MESSAGE);
    }


    /**
     * Prints confirmation that Login was successful
     */
    public void printLoginSuccessful() {
        JOptionPane.showMessageDialog(null, "Login Successful! Redirecting back to account " +
                "Main Menu..." + '\n', "[User Type] Create Account", JOptionPane.PLAIN_MESSAGE);
    }

}
