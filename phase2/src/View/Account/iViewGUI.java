package View.Account;

import View.View;

public interface iViewGUI extends View {
    ContactMenu cm = new ContactMenu();

    void MainMenu();

    void AttEventMenu();

    void AttMessageMenu();

    void ContactMenu();

    void EventMenu();

    void MessageMenu();

    void OrgEventMenu();

    void RequestMenu();

    void SpeEventMenu();


}
