package Request.controllers;

import Presenter.Central.SubMenu;
import Request.RequestManager;
import Request.presenters.EmpReqMenu;

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


    public void menuChoice() {
        do {
            switch (currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1: // see the requests --> must be an organizer
                    specificRequest();
                    break;
                case 2: // fulfill requests --> must be an organizer
                    seeRequests();
                    break;
                case 3: // see a specific request
                    fulfillRequest();
                    break;
                case 4:
                    request(); // make a request
                    break;
                case 5:
                    myRequests(); // see my request till now
                    break;
            }
        }
        while (currentRequest != 0);
    }

    private void fulfillRequest(){
        presenter.fulfillRequestPrompt();
        String id = "";//SubMenu.readInput(input);
        requestManager.updateEntity(id);
    }
    private void seeRequests(){
        presenter.seeRequests();
    }
    private void specificRequest(){
        presenter.seeSpecificRequestPrompt();
        String id = "";//SubMenu.readInput(input);
        presenter.seeRequest(id);
    }
    private void request(){
        presenter.makeRequestPrompt();
        String content = "";//SubMenu.readInput(input);
        requestManager.createRequest(this.currentUserID, content);
    }
    private void myRequests(){
        presenter.myRequests(currentUserID);
    }


}
