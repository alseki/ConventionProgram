package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttMessageController;
import Request.controllers.AttReqController;
import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgEventController;
import Request.controllers.OrgReqController;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.MessageController;
import Presenter.SpeakerController.SpeEventController;

import javax.swing.*;

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
    }
}
