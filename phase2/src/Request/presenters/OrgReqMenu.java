package Request.presenters;

import Request.RequestManager;
import Request.presenters.RequestMenuParent;

// Programmers:
// Description:
// Date Created:
// Date Modified: 02/12/2020

public class OrgReqMenu extends RequestMenuParent {

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

    public String seeRequests() {
        StringBuilder reqs = new StringBuilder();
        for (String str : reqM.getAllRequestUserIds()) {
            reqs.append(printRequest(str));
        }
        return requestFormat() + reqs;
    }
    /**
     * prompt to get requestId for fullfilling
     */
    public String fulfillRequestPrompt(){
        return "Which Request Would you like to fulfill, please enter the ID.";
    }


}
