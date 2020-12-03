package Request;

import Presenter.Central.SubMenuPrinter;
import Request.RequestEntity;
import Request.RequestManager;

// Programmers:
// Description:
// Date Created:
// Date Modified: 02/12/2020

public class AttReqMenu extends RequestMenuParent{
    protected RequestManager reqM;
    public AttReqMenu(RequestManager reqM){
        super(reqM);
    }


    @Override
    public String[] getMenuOptions() {
        String[] options = {"Return to Main Menu", "Make a request", "View information about a request", "See my " +
                "personal requests"};
        return options;
    }






}


