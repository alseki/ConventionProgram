package View.Account;

import Presenter.Central.SubMenu;
import Presenter.SpeakerController.SpeEventController;
import Presenter.SpeakerController.SpeEventMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SpeEventView extends AccountView {
    SpeEventController controller;
    SpeEventMenu presenter;

    /**
     * The view for speaker users to see their convention event options.
     * @param controller SpeEventController for handling user input
     */
    public SpeEventView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (SpeEventController) controller;
        this.presenter = (SpeEventMenu) controller.getPresenter();

        contentPane.setBackground(new Color(255, 10, 190));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
    }
}
