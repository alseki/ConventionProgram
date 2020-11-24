package Controllers;

import Presenter.RequestMenu;
import ReqestPackage.RequestManager;
import java.util.Scanner;
public class RequestController implements SubMenu {
    private RequestManager reqM;
    private int currentRequest;
    private RequestMenu presenter;
    Scanner input = new Scanner(System.in);

    RequestController() {
        this.reqM = new RequestManager();
        this.presenter = new RequestMenu(reqM);
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
                case 2: // see the requests --> must be an organizer
                    seeRequests();
                    break;
                case 3: // fulfill requests --> must be an organizer
                    fulfillRequest();
            }
        }
        while (currentRequest != 0);
    }
    private void seeRequests(){
        presenter.seeOutstandingRequests();
    }
    private void fulfillRequest(){
        presenter.fulfillRequestPrompt();
        String id = SubMenu.readInput(input);
        reqM.updateEntity(id);
    }
    private void request(){
        presenter.makeRequestPrompt();
        String content = SubMenu.readInput(input);
        presenter.enterIdPrompt();
        String id = SubMenu.readInput(input);
        reqM.createRequest(id, content);
    }
}


}
