package Presenter.EmployeeController;

import Event.CapacityException;
import Event.EventPermissions;
import Person.AttendeeManager;
import Presenter.AttendeeController.AttEventMenu;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;


    // get schedule of all talks

    // get request board

    // contact organizers .. set up that check in the EmployeeManager
    // contact employees  ... set up that check in the EmployeeManager -- check with message people

    //  do a test exceptions .. try sending to speaker and/or attendee -- and get back message

    // set up a request board of tally for each room - whether it is an event or not.

    // which organizers and employees and administrator can see.



    public class EmpEventController extends SubMenu {

        private String currentUserID;
        private AttendeeManager attendeeManager;
        private EventPermissions eventPermissions;

        private AttEventMenu presenter;

        public EmpEventController(SubMenu subMenu, String currentUserID, AttendeeManager attendeeManager) {
            super(subMenu);
            this.currentUserID = currentUserID;
            this.attendeeManager = attendeeManager;
            eventPermissions = new EventPermissions(roomManager, eventManager);
            presenter = new AttEventMenu(currentUserID, roomManager, eventManager, attendeeManager);
        }

        /**
         * Takes user input and calls appropriate methods, until user wants to return to Main Menu
         */
        public void menuChoice() {
            do {
                switch(1) {
                    case 0:
                        // return to main menu
                        break;
                    case 1:
                    /*try {
                        String roomID = this.getRoomChoice();
                        if (roomID.equals("1")) {
                            presenter.printEventList();
                        } else {
                            presenter.printRoomEventList(roomManager.getEventIDs(roomID),
                                    roomManager.getRoomName(roomID));
                        }
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                    }*/
                        break;
                    case 2:
                        presenter.printAddEventPrompt();
                        String addingEventInput = "";//SubMenu.readInput(input);
                        try {
                            signupForEvent(addingEventInput);
                        } catch (InvalidChoiceException e) {
                            presenter.printException(e);
                        }
                        break;
                    case 3:
                        presenter.printRemoveEventPrompt();
                        String removingEventInput = "";//SubMenu.readInput(input);
                        try {
                            cancelSpotFromEvent(removingEventInput);
                        } catch (InvalidChoiceException e) {
                            presenter.printException(e);
                        }
                        break;
                    case 4:
                        //presenter.printAttendeeEventList();
                        break;
                }
            }
            while (true);

        }

        // TODO change, delete and/or add to the methods below

        /**
         * Takes user input to pick a Room for which that user wishes to view the Events held there
         * @return the ID of the Room chosen
         */
   /* private String getRoomChoice() throws InvalidChoiceException {
        presenter.printRoomChoicePrompt();
        String room = "";//SubMenu.readInput(input);
        if (room.equals("0")) {
            presenter.printRoomList();
            room = "";//SubMenu.readInput(input);
        }
        if (room.equals("1")) {
            return "1";
        }
        if (roomManager.getRoomID(room) != null) {
            return roomManager.getRoomID(room);
        } else {
            throw new InvalidChoiceException("room");
        }
    }*/

        /**
         * Tries to sign user up for an Event
         * @param eventName The name of the Event the current user requested to sign up for
         */
        private void signupForEvent (String eventName) throws InvalidChoiceException  {
            String event = eventManager.getEventID(eventName);
            String room = roomManager.getEventRoom(event);
            if (room == null || event == null) {
                throw new InvalidChoiceException("event");
            }
            try {
                eventPermissions.signUpForEvent(currentUserID, event, room);
                boolean eventAddedToPerson = attendeeManager.signUpForEvent(currentUserID, event);
                attendeeManager.addAnChat(currentUserID, eventManager.getEventChat(event));
                if (eventAddedToPerson) {
                    presenter.printEventAdded();
                }
            } catch (CapacityException c) {
                presenter.printEventFull();
            }
        }

        /**
         * Remove this user from Event
         * @param eventName The name of the Event the current user requested to cancel
         */
        private void cancelSpotFromEvent(String eventName) throws InvalidChoiceException {
            String event = eventManager.getEventID(eventName);
            if (event == null) {
                throw new InvalidChoiceException("event");
            }

            boolean personRemovedFromEvent = eventPermissions.removeFromEvent(currentUserID, event);
            boolean eventRemovedFromPerson = attendeeManager.removeSpotFromEvents(currentUserID, event);
            attendeeManager.removeAnChat(currentUserID, eventManager.getEventChat(event));
            if(personRemovedFromEvent && eventRemovedFromPerson) {
                presenter.printEventRemoved();
            }
        }
    }

}

//package Presenter.EmployeeController;
//
//// Programmer: Karyn Komatsu,
//// Description: Event Menu for SpeakerController users.
//// Date Created: 01/11/2020
//// Date Modified: 19/11/2020
//
//        import Presenter.Exceptions.InvalidChoiceException;
//        import Presenter.Exceptions.NoDataException;
//        import Presenter.Central.SubMenu;
//        import Person.SpeakerManager;
//
//        import java.util.InputMismatchException;
//        import java.util.Scanner;


