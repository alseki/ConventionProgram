package View.Account;

import Presenter.Central.SubMenu;
import Presenter.EmployeeController.EmpReqController;
import Presenter.EmployeeController.EmpReqMenu;

import java.awt.event.ActionEvent;

public class EmpReqView extends AccountView {
    EmpReqController controller;
    EmpReqMenu presenter;

    /**
     * The view for employee users to see their request options.
     * @param controller EmpReqController for handling user input
     */
    public EmpReqView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller =  (EmpReqController) controller;
        this.presenter = (EmpReqMenu) controller.getPresenter();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}