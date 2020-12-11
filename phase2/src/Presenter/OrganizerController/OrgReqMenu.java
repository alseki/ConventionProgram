package Presenter.OrganizerController;

import Person.PersonManager;
import Presenter.AttendeeController.AttReqMenu;
import Request.RequestManager;

// Programmers:
// Description:
// Date Created:
// Date Modified: 11/12/2020

public class OrgReqMenu extends AttReqMenu {

    /**
     * constructor
     * @param reqM a request manager
     */
    public OrgReqMenu(RequestManager reqM, PersonManager perM){
        super(reqM, perM);
    }

    /**
     * getter for the menu options
     * @return the menu options
     */
    @Override
    public String[] getMenuOptions() {
        return new String[]{"View a specific request", "View requests", "Fulfill a request",
            "Make a request", "See my requests", "Modify a request"};
    }

    /**
     * fulfills request for reqId
     * @param reqId String
     * @return "The request with id" + reqId + "has been fulfilled"
     */
    public String fulfillResponse(String reqId){
        return "The request with id" + reqId + "has been fulfilled";
    }

    /**
     * notified who is handling request for reqId
     * @param reqId String
     * @return "The request with id" + reqId + "has been fulfilled"
     */
    public String handlingRequestPrompt(String reqId){
        StringBuilder users = new StringBuilder();
        for (String id: reqM.getHandlers(reqId)) {
            users.append(perM.getCurrentUsername(id));
            users.append(", ");
        }
        users.delete(users.length()-3, users.length());
        return "The request with id " + reqId + " is being taken care of by " + users;
    }


}
