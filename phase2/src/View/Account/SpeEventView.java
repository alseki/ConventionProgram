package View.Account;

import Presenter.Central.SubMenu;
import Presenter.SpeakerController.SpeEventController;

public class SpeEventView implements AccountView {
    SpeEventController controller;

    public SpeEventView(SubMenu controller) {
        this.controller = (SpeEventController) controller;
    }
}
