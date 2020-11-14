package Controllers;

// Programmer: Cara McNeil
// Description: All the methods that take user input in the Contact Menu
// Date Created: 01/11/2020
// Date Modified: 13/11/2020


public class ContactController implements SubMenu{

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     * @return true iff choice was inputted
     */
    @Override
    public boolean menuOptions() {
        return false;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        return false;
    }

    /**
     * Get's the Person.Person user's contactList
     *
     * @return true iff the presenter printed a formatted contactList
     */
    public boolean getContactList() {
        //manager.getPerson(currentUserID).getContactList();
        // format list ? to what end ?
        // send the Presenter the formatted contactList to print (if empty, say so)
        return false;

    }

    /**
     * Add a contact to the Person.Person user's contactList
     *
     * @param contactUsername
     * @return true iff the presenter printed a formatted contactList
     */
    public boolean addContact(String contactUsername) {
        //String contactID = manager.getCurrentUserID(contactUsername);
        //if ((manager.addContact(currentUserID, contactID) && manager.addContact(contactID, currentUserID))) {
        // update presenter to say contact was added
        //  return true;
        //}
        return false;
    }

}
