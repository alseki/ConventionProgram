package Request.presenters;

import Request.RequestEntity;
import Request.RequestManager;

// Programmers:
// Description:
// Date Created:
// Date Modified: 02/12/2020

public class EmpReqMenu extends RequestMenuParent {
    protected RequestManager reqM;

    public EmpReqMenu(RequestManager reqM) {
        super(reqM);
    }

    @Override
    public String[] getMenuOptions() {
        String[] options = {"Return to Main Menu", "View a specific request", "View the Request Board", "Fulfill a request",
                "Make a request", "See my requests"};
        return options;
    }

    public void seeRequests() {
        StringBuilder reqs = new StringBuilder();
        for (RequestEntity r : reqM.getAllRequests()) { //TODO see if this violates clean architecture. THIS DOES NOT
                                                        // I BELIEVE violate c.a. because requestFormat() and printRequest are coming
                                                        // from an abstract class in the same layer, and (2) it is acceptable in the
                                                        // presenter/controller layer for there to be some interaction. I'll check with Lindsey
            reqs.append(printRequest(r));
        }
        System.out.println(requestFormat() + reqs);
    }


    /**
     * prompt to get requestId for fulfilling
     */
    public void fulfillRequestPrompt(){
        System.out.println("Which request would you like to fulfill, please enter the ID.");
    }

    public void fulfillRequestPromptByRoom() {
        System.out.println("For which event would you like to see if there are outstanding requests, please enter event name.");
    }
}

