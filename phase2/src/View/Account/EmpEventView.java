package View.Account;

import Presenter.Central.SubMenu;
import Presenter.EmployeeController.EmpEventController;
import Presenter.EmployeeController.EmpEventMenu;
import View.AccountHelpers.ListDisplayView;

import java.awt.*;
import java.awt.event.ActionEvent;

public class EmpEventView extends AccountView {
    EmpEventController controller;
    EmpEventMenu presenter;
    String[] menuOp;
    ListDisplayView eventList;

    /**
     * The view for employee users to see their convention event options.
     * @param controller EmpEventController for handling user input
     */
    public EmpEventView(SubMenu controller) {
        super(controller);
        this.controller = (EmpEventController) controller;
        this.presenter = (EmpEventMenu) controller.getPresenter();

        contentPane.setBackground(new Color(255, 140, 190));
        //contentPane.setBackground(new Color(130, 255, 140));

        menuOp = this.presenter.getMenuOptions();
    }

    private void showViewEvents() {

        okayButton.setVisible(true);
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


