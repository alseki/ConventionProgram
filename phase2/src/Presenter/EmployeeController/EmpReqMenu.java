package Presenter.EmployeeController;

import Request.RequestEntity;
import Request.RequestManager;
import Presenter.RequestMenuParent;

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
        String[] options = {"Return to Main Menu", "View a specific request", "View the Request Board",
                "Make a request", "See my requests"};
        return options;
    }

    public String seeRequests() {
        StringBuilder reqs = new StringBuilder();
        for (String id: reqM.getAllRequestUserIds()) {
            reqs.append(reqM.getStringOfRequest(id));
        }
        return requestFormat() + reqs;
    }

}

