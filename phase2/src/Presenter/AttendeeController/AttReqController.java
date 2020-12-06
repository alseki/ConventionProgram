package Presenter.AttendeeController;

import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Request.RequestManager;

public class AttReqController extends SubMenu {
    protected RequestManager reqM;
    protected int currentRequest;
    protected AttReqMenu presenter;
    private String currentUserID;

    public AttReqController(SubMenu subMenu, String currentUserID) {
        super(subMenu);
        this.presenter = new AttReqMenu(reqM);
        this.currentUserID = currentUserID;
    }


    private void request(){
        presenter.makeRequestPrompt();
        String content = "";//SubMenu.readInput(input);
        reqM.createRequest(this.currentUserID, content);
    }
    private void specificRequest(String reqId){
        presenter.seeSpecificRequestPrompt(reqId);
        //String id = "";//SubMenu.readInput(input);
        //presenter.seeRequest(id);

    }
    private void myRequests(){
        presenter.myRequests(currentUserID);
    }


    @Override
    public AttReqMenu getPresenter() {
        return this.presenter;
    }
}




