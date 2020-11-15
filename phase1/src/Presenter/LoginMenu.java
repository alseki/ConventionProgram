package Presenter;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to a user's login information
// Date Created: 11/11/2020
// Date Modified: 13/11/2020

public class LoginMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        System.out.println("----- Login Menu -----");
        System.out.println("To return to start page, Enter '0'.");
        System.out.println("To Login, Enter '1'.");
        System.out.println("To Create a new account, Enter '2'.");
        return true;
    }

    /**
     * Prompts user to enter username
     * @return true iff username prompt has been printed
     */
    public boolean printUsernamePrompt() {
        System.out.println("Enter username: ");
        return true;
    }

    /**
     * Prompts user to enter password
     * @return true iff password prompt has been printed
     */
    public boolean printPasswordPrompt() {
        System.out.println("Enter password: ");
        return true;
    }

    /**
     * Prompts user to enter name
     * @return true iff name prompt has been printed
     */
    public boolean printNamePrompt() {
        System.out.println("Enter full name: ");
        return true;
    }

    /**
     * Prompts user to enter email
     * @return true iff email prompt has been printed
     */
    public boolean printEmailPrompt() {
        System.out.println("Enter email address: ");
        return true;
    }

    /**
     * Prints instructions for user to create account
     * @return true if account creation instructions are printed
     */
    public boolean printCreateAccountPrompt() {
        System.out.println("To create an account, select a username and password, then enter your full name and email " +
                "address. ");
        return true;
    }

    /**
     * Prints confirmation that an account was created.
     * @return true iff account creation successful statement was printed
     */
    public boolean printAccountCreationSuccessful() {
        System.out.println("Account creation successful! Redirecting to Login Menu...");
        return true;
    }

    /**
     * Prints instructions for user to login
     * @return true iff login instructions were printed
     */
    public boolean printLoginPrompt() {
        System.out.println("To login, enter the following: ");
        return true;
    }


    /**
     * Prints confirmation that Login was successful
     * @return true iff login successful statement was printed
     */
    public boolean printLoginSuccessful() {
        System.out.println("Login Successful! Redirecting to account Main Menu...");
        return true;
    }
}
