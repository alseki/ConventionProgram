package Presenter.EmployeeController;

import Presenter.Central.SubMenu;
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

//    /**
//     * allows user to change/modify the request that is still pending
//     * @param reqUserId the user who is changing the request
//     * @param reqId of the request content to be brought into a string
//     * @return true
//     */
//    public boolean modifyRequest(String reqUserId, String reqId) {
//        RequestEntity req = getRequestEntity(reqId);
//        if(!req.getFulfilled()) {
//            String oldRequest = getStringOfRequest(reqId);
//            createRequest(oldRequest, reqUserId);
//        }
//        return true;
//
//    }


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

    private String handleRequest(){
        return presenter.seeHandleRequestPrompt(currentUserID);
    }


    @Override
    public EmpReqMenu getPresenter() {
        return this.presenter;
    }
}
