package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttEventMenu;
import Presenter.Central.SubMenu;
import Presenter.EmployeeController.EmpEventController;
import Presenter.EmployeeController.EmpEventMenu;
import Presenter.OrganizerController.OrgEventController;

import java.awt.*;
import java.awt.event.ActionEvent;

public class EmpEventView extends AccountView {
    EmpEventController controller;
    EmpEventMenu presenter;
    String[] menuOp;

    public EmpEventView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (EmpEventController) controller;
        this.presenter = ((EmpEventController) controller).getPresenter();

        contentPane.setBackground(new Color(130, 255, 140));

        menuOp = this.presenter.getMenuOptions();
    }

    private void showViewEvents() {

    }

    private void showViewRooms() {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        // [0] = view conference events list
        // [1] = view list of rooms
        if(eventName.equals(menuOp[0])) {
            hideMainDropDownMenu();
            showViewEvents();
        }

        if(eventName.equals(menuOp[1])) {
            hideMainDropDownMenu();
            showViewRooms();
        }
    }
}


