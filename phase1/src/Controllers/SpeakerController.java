package Controllers;

// Programmer: Cara McNeil
// Description: Controllers.Main menu for Speaker users.
// Date Created: 01/11/2020
// Date Modified: 04/11/2020


public class SpeakerController extends PersonController{

    @Override
    void run() {

    }

    @Override
    boolean login() {
        return false;
    }

    @Override
    boolean createAccount() {
        return false;
    }

    @Override
    boolean getContactList() {
        return false;
    }

    @Override
    boolean addContact(String contactUsername) {
        return false;
    }

    @Override
    boolean getChats() {
        return false;
    }

    @Override
    boolean createChat(String contactUsername) {
        return false;
    }

    @Override
    boolean addMessage(String contactUsername, String messageContent) {
        return false;
    }

    @Override
    boolean getMessages(String chatID) {
        return false;
    }
}
