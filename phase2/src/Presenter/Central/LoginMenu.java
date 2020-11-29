package Presenter.Central;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to a user's login information
// Date Created: 11/11/2020
// Date Modified: 13/11/2020

import javax.swing.*;

public class LoginMenu {

    // TODO either turn this into a helper class, or movw all the info into the Login Controller

    /**
     * Prints the options for this menu.
     */
    public String printMenuOptions() {
        String message = "\nTo return to start page, Enter '0'." + "\nTo Login, Enter '1'."
                + "\nTo Create a new account, Enter '2'.";
        return JOptionPane.showInputDialog(null, message, "[User Type] Login Menu",
                JOptionPane.PLAIN_MESSAGE);
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
     * Prints instructions for user to login
     */
    public void printLoginPrompt() {
        JOptionPane.showMessageDialog(null, "\nTo login, enter the following: ",
                "[User Type] Create Account", JOptionPane.PLAIN_MESSAGE);
    }


    /**
     * Prints confirmation that Login was successful
     */
    public void printLoginSuccessful() {
        JOptionPane.showMessageDialog(null, "Login Successful! Redirecting back to account " +
                "Main Menu..." + '\n', "[User Type] Create Account", JOptionPane.PLAIN_MESSAGE);
    }

    public void printException(Exception e) {
        String message = "\nSorry! That didn't work." + '\n' + e.getMessage() + "\nPlease try again!";
        JOptionPane.showMessageDialog(null, message, "[User Type] Login", JOptionPane.ERROR_MESSAGE);
    }
}
