package View.Account;

import Presenter.Central.SubMenu;
import Presenter.SpeakerController.SpeEventController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SpeEventView extends AccountView {
    SpeEventController controller;

    public SpeEventView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (SpeEventController) controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
