package Request.presenters;

import Request.RequestEntity;
import Request.RequestManager;

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

    public void seeRequests() {
        StringBuilder reqs = new StringBuilder();
        for (RequestEntity r : reqM.getAllRequests()) { //TODO see if this violates clean architecture. THIS DOES NOT
                                                        // I BELIEVE violate c.a. because requestFormat() and printRequest are coming
                                                        // from an abstract class in the same layer, and (2) it is acceptable in the
                                                        // presenter/controller layer for there to be some interaction. I'll check with Lindsey
            reqs.append(printRequest(r.getRequestId()));
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
