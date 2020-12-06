package View.Account;

import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgEventController;

import java.awt.event.ActionEvent;

    public class EmpEventView extends AccountView {
        OrgEventController controller;

        public EmpEventView(SubMenu controller) {
            super();
            this.controller = (OrgEventController) controller;
        }

        @Override
        public void actionPerformed(ActionEvent event) {

        }
    }


