package Presenter.AttendeeController;

import Presenter.Central.SubMenu;
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


    public void menuChoice() {
        do {
            switch (currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1: //make request
                    request();
                    break;
                case 2:
                    specificRequest();
                    break;
                    case 3:
                        myRequests();
                        break;
            }
        }
        while (currentRequest != 0);
    }

    private void request(){
        presenter.makeRequestPrompt();
        String content = "";//SubMenu.readInput(input);
        reqM.createRequest(this.currentUserID, content);
    }
    private void specificRequest(){
        presenter.seeSpecificRequestPrompt();
        String id = "";//SubMenu.readInput(input);
        presenter.seeRequest(id);

    }
    private void myRequests(){
        presenter.myRequests(currentUserID);
    }


}




