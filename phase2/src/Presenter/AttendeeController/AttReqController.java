package Presenter.AttendeeController;

import Controllers.SubMenu;
import Presenter.AttendeeController.AttReqMenu;
import ReqestPackage.RequestManager;
import Request.RequestManager;

import java.util.Scanner;

public class AttReqController implements SubMenu {
    protected RequestManager reqM;
    protected int currentRequest;
    protected AttReqMenu presenter;
    Scanner input = new Scanner(System.in);
    AttReqController(RequestManager reqM) {
        this.reqM = reqM;
        this.presenter = new AttReqMenu(reqM);
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
        String content = SubMenu.readInput(input);
        presenter.enterIdPrompt();
        String id = SubMenu.readInput(input);
        reqM.createRequest(id, content);
    }
    protected void specificRequest(){
        presenter.seeSpecificRequestPrompt();
        String id = SubMenu.readInput(input);
        presenter.seeRequest(id);

    }


}




