package Presenter.OrganizerController;

import Controllers.SubMenu;
import Presenter.Central.SubMenu;
import Request.RequestManager;

import java.util.Scanner;

public class OrgReqController implements SubMenu {
    protected RequestManager reqM;
    protected int currentRequest;
    protected OrgReqMenu presenter;
    Scanner input = new Scanner(System.in);
    OrgReqController(RequestManager reqM) {
        this.reqM = reqM;
        this.presenter = new OrgReqMenu(reqM);
    }

    @Override
    public void menuOptions() {
        presenter.printMenuOptions();
        currentRequest = SubMenu.readInteger(input);
    }

    @Override
    public void menuChoice() {
        do {
            menuOptions();
            switch (currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1: // see the requests --> must be an organizer
                    seeRequests();
                    break;
                case 2: // fulfill requests --> must be an organizer
                    fulfillRequest();
                    break;
                case 3: // see a sepcific request
                    getRequest();
                    break;
                case 4:
                    request();
                    break;

            }
        }
        while (currentRequest != 0);
    }
    private void fulfillRequest(){
        presenter.fulfillRequestPrompt();
        String id = SubMenu.readInput(input);
        reqM.updateEntity(id);
    }
    private void seeRequests(){
        presenter.seeRequests();
    }
    private void getRequest(){
        presenter.seeSpecificRequestPrompt();
        String id = SubMenu.readInput(input);
        presenter.seeRequest(id);
    }
    protected void request(){
        presenter.makeRequestPrompt();
        String content = SubMenu.readInput(input);
        presenter.enterIdPrompt();
        String id = SubMenu.readInput(input);
        reqM.createRequest(id, content);
        // TODO reqM.addObserver(OrganizerManagerreqs);
        // TODO reqM.addObeserver(currentPerson)
    }


}
