package Presenter.AttendeeController;

import Presenter.AttendeeController.AttReqMenu;
import Presenter.Central.SubMenu;
import Request.RequestManager;

import java.util.Scanner;

public class AttReqController extends SubMenu {
    protected RequestManager reqM;
    protected int currentRequest;
    protected AttReqMenu presenter;
    private String currentUserID;

    AttReqController(SubMenu subMenu, String currentUserID) {
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
            }
        }
        while (currentRequest != 0);
    }

    protected void request(){
        presenter.makeRequestPrompt();
        String content = "";//SubMenu.readInput(input);
        presenter.enterIdPrompt();
        String id = "";//SubMenu.readInput(input);
        reqM.createRequest(id, content);
        // TODO reqM.addObserver(OrganizerManagerreqs);
        // TODO reqM.addObeserver(currentPerson)
    }
    protected void specificRequest(){
        presenter.seeSpecificRequestPrompt();
        String id = "";//SubMenu.readInput(input);
        presenter.seeRequest(id);

    }


}




