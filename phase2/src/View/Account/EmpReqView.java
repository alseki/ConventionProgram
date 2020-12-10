package View.Account;

import Presenter.Central.SubMenu;
import Presenter.EmployeeController.EmpReqController;
import Presenter.EmployeeController.EmpReqMenu;

import java.awt.event.ActionEvent;

public class EmpReqView extends AccountView {
    EmpReqController controller;
    EmpReqMenu presenter;

    public EmpReqView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller =  (EmpReqController) controller;
        this.presenter = ((EmpReqController) controller).getPresenter();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}