package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttMessageController;
import Request.AttReqController;
import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgEventController;
import Request.OrgReqController;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.MessageController;
import Presenter.SpeakerController.SpeEventController;

public class AccountViewFactory {

    public AccountView construct(SubMenu controller) {
        AccountView view = null;
        if (controller instanceof ContactController) {
            view = new ContactView(controller);
        }
        else if (controller instanceof AttMessageController) {
            view = new AttMessageView(controller);
        }
        else if (controller instanceof AttEventController) {
            view = new AttEventView(controller);
        }
        else if (controller instanceof AttReqController) {
            view = new AttReqView(controller);
        }
        else if (controller instanceof OrgEventController) {
            view = new OrgEventView(controller);
        }
        else if (controller instanceof OrgReqController) {
            view = new OrgEventView(controller);
        }
        else if (controller instanceof MessageController) {
            view = new MessageView(controller);
        }
        else if (controller instanceof SpeEventController) {
            view = new SpeEventView(controller);
        }
        return view;
    }
}
