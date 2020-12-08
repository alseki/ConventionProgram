package Presenter.EmployeeController;

import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Request.RequestManager;

public class EmpReqController extends SubMenu {
    protected RequestManager reqM;
    protected int currentRequest;
    protected EmpReqMenu presenter;
    private String currentUserID;

    public EmpReqController(SubMenu subMenu, String currentUserID) {
        super(subMenu);
        this.presenter = new EmpReqMenu(reqM);
        this.currentUserID = currentUserID;
    }


    private String seeRequests(){
        return presenter.seeRequests();
    }

    private String specificRequest(String reqId){
        return presenter.seeSpecificRequestPrompt(reqId);
        //String id = "";//SubMenu.readInput(input);
        //presenter.seeRequest(id);
    }
    private void request(){
        presenter.makeRequestPrompt();
        String content = "";//SubMenu.readInput(input);
        requestManager.createRequest(this.currentUserID, content);
    }
    private String myRequests(){
        return presenter.myRequests(currentUserID);
    }


    @Override
    public EmpReqMenu getPresenter() {
        return this.presenter;
    }
}
