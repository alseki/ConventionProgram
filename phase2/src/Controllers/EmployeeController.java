package Controllers;

import java.util.ArrayList;

public class EmployeeController {

    @Override
    public void menuChoice() {
        do {
            menuOptions();
            switch (currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1: //Check your inbox: messages from organizers and other employees
                    inBox();
                    break;
                case 2: //Check your sent box
                    sentBox();
                    break;

                case3:
                // check the Request board

                case4:
                // check the ConferenceConstrains board

            }
            // Option 1

            /**
             * Show all the messages this user received in presenter, **sorted by datetime.
             */
            protected void inBox(){
                try {
                    ArrayList<String> receivedMessages = new ArrayList<>();
                    for (String message: messageManager.getMessageIDs()){
                        if (messageManager.getRecipientId(message).equals(currentUserID)){
                            receivedMessages.add(message);
                        }
                    }

                    presenter.printList(presenter.formatMessages(receivedMessages), "message");
                } catch (NullPointerException e) {
                    presenter.printException(new NoDataException("message"));
                } catch (NoDataException e) {
                    presenter.printException(e);
                }
            }

            // Option 2

            /**
             * Show all the messages this user sent in presenter, **sorted by datetime.
             */
            protected void sentBox(){
                try {
                    ArrayList<String> sentMessages = new ArrayList<>();
                    for (String message: messageManager.getMessageIDs()){
                        if (messageManager.getSenderID(message).equals(currentUserID)){
                            sentMessages.add(message);
                        }
                    }

                    presenter.printList(presenter.formatMessages(sentMessages), "message");
                } catch (NullPointerException e) {
                    presenter.printException(new NoDataException("message"));
                } catch (NoDataException e) {
                    presenter.printException(e);
                }
            }

}
