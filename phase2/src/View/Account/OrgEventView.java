package View.Account;

import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgEventController;

import javax.swing.*;

public class OrgEventView extends AccountView {
    OrgEventController controller;

    public OrgEventView(SubMenu controller) {
        super();
        this.controller = (OrgEventController) controller;
    }
}
