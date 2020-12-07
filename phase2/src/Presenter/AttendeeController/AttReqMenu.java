package Presenter.AttendeeController;

import Request.RequestManager;
import Presenter.RequestMenuParent;

// Programmers:
// Description:
// Date Created:
// Date Modified: 02/12/2020

public class AttReqMenu extends RequestMenuParent {
    /**
     * construcotr for attendee request menu
     * @param reqM requestManager
     */
    public AttReqMenu(RequestManager reqM){
        super(reqM);
    }

    /**
     * gets the manu options
     * @return the menu options
     */
    @Override
    public String[] getMenuOptions() {
        String[] options = {"Return to Main Menu", "Make a request", "View information about a request", "See my " +
                "personal requests", "Modify a request"};
        return options;
    }






}


