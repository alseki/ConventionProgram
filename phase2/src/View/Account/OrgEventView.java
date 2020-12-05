package View.Account;

import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgEventController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OrgEventView extends AccountView {
    OrgEventController controller;

    public OrgEventView(SubMenu controller) {
        super();
        this.controller = (OrgEventController) controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
