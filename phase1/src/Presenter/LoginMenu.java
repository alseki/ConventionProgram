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
        return true;
    }

    /**
     * Prompts user to enter name
     * @return true iff name prompt has been printed
     */
    public boolean printNamePrompt() {
        return true;
    }

    /**
     * Prompts user to enter email
     * @return true iff email prompt has been printed
     */
    public boolean printEmailPrompt() {
        return true;
    }

    /**
     * Prints instructions for user to create account
     * @return true if account creation instructions are printed
     */
    public boolean printCreateAccountPrompt() {
        return true;
    }

    /**
     * Prints instructions for user to login
     * @return true iff login instructions were printed
     */
    public boolean printLoginPrompt() {
        return true;
    }
}
