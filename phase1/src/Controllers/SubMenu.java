package Controllers;

// Programmer: Cara McNeil
// Description: Classes that the Main Menu refers to
// Date Created: 01/11/2020
// Date Modified: 13/11/2020

public interface SubMenu {

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     * @return true iff choice was inputted
     */
    boolean menuOptions();

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    boolean menuChoice();
}
