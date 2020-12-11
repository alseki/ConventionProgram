package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttMessageController;
import Presenter.AttendeeController.AttReqController;
import Presenter.Central.SubMenu;
import Presenter.EmployeeController.EmpEventController;
import Presenter.EmployeeController.EmpReqController;
import Presenter.OrganizerController.OrgEventController;
import Presenter.OrganizerController.OrgReqController;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.MessageController;
import Presenter.SpeakerController.SpeEventController;


public class AccountViewFactory {

    /**
     * Constructs a View based on the type of controller
     * @param controller a SubMenu controller
     */
    public void construct(SubMenu controller) {
        if (controller instanceof ContactController) {
            new ContactView(controller);
        }
        else if (controller instanceof AttMessageController) {
            new AttMessageView(controller);
        }
        else if (controller instanceof AttEventController) {
            new AttEventView(controller);
        }
        else if (controller instanceof AttReqController) {
            new AttReqView(controller);
        }
        else if (controller instanceof OrgEventController) {
            new OrgEventView(controller);
        }

        // TODO OrgPersonView
//        else if (controller instanceof OrgPersonController) {
//            new OrgPersonView(controller);
//        }
        else if (controller instanceof OrgReqController) {
            new OrgReqView(controller);
        }
        else if (controller instanceof MessageController) {
            new MessageView(controller);
        }
        else if (controller instanceof SpeEventController) {
            new SpeEventView(controller);
        }
        else if (controller instanceof EmpEventController) {
            new EmpEventView(controller);
        }
        else if (controller instanceof EmpReqController) {
            new EmpReqView(controller);
        }
    }
}
