package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttMessageController;
import Presenter.AttendeeController.AttReqController;
import Presenter.Central.SubMenu;
import Presenter.EmployeeController.EmpEventController;
import Presenter.OrganizerController.OrgEventController;
import Presenter.OrganizerController.OrgReqController;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.MessageController;
import Presenter.SpeakerController.SpeEventController;


public class AccountViewFactory {

    public void construct(SubMenu controller) {
        AccountView view = null;
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
    }
}
