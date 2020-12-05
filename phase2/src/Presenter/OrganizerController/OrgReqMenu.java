package Presenter.OrganizerController;

import Request.RequestEntity;
import Request.RequestManager;
import Presenter.RequestMenuParent;

// Programmers:
// Description:
// Date Created:
// Date Modified: 02/12/2020

public class OrgReqMenu extends RequestMenuParent {
    private RequestManager reqM;

    /**
     * contructor
     * @param reqM a request manager
     */
    public OrgReqMenu(RequestManager reqM){
        super(reqM);
    }

    @Override
    public String[] getMenuOptions() {
        String[] options = {"Return to Main Menu", "View a specific request", "View requests", "Fulfill a request",
            "Make a request", "See my requests"};
        return options;
    }

    /**
     * see all the requests
     */
    public void seeRequests() {
        StringBuilder reqs = new StringBuilder();
        for (String id: reqM.getAllRequestUserIds()) {
            reqs.append(reqM.getRequestStringForPerson(id));
        }
        System.out.println(requestFormat() + reqs);
    }
    /**
     * prompt to get requestId for fullfilling
     */
    public void fulfillRequestPrompt(){
        System.out.println("Which Request Would you like to fulfill, please enter the ID.");
    }


}
