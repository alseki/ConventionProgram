package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.Central.SubMenu;
import Presenter.EmployeeController.EmpEventController;
import Presenter.EmployeeController.EmpEventMenu;
import Presenter.OrganizerController.OrgEventController;

import java.awt.event.ActionEvent;

    public class EmpEventView extends AccountView {
        EmpEventController controller;
        EmpEventMenu presenter;


        public EmpEventView(SubMenu controller) {
            super(controller.getPresenter());
            this.controller = (EmpEventController) controller;
            //this.presenter = ((EmpEventController) controller).getPresenter();
        }

        @Override
        public void actionPerformed(ActionEvent event) {

        }
    }


