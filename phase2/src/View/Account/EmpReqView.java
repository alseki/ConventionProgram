package View.Account;

import Presenter.Central.SubMenu;
import Presenter.EmployeeController.EmpReqController;

import java.awt.event.ActionEvent;

public class EmpReqView extends AccountView {
    EmpReqController controller;

    public EmpReqView(SubMenu controller) {
        super();
        this.controller =  (EmpReqController) controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}