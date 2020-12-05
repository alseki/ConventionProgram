package View.Account;

import Presenter.Central.SubMenu;
import Presenter.SpeakerController.SpeEventController;

import javax.swing.*;

public class SpeEventView extends AccountView {
    SpeEventController controller;

    public SpeEventView(SubMenu controller) {
        super();
        this.controller = (SpeEventController) controller;
    }
}
