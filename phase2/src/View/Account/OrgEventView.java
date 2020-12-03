package View.Account;

import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgEventController;

public class OrgEventView implements AccountView {
    OrgEventController controller;

    public OrgEventView(SubMenu controller) {
        this.controller = (OrgEventController) controller;
    }
}
