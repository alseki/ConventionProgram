package Presenter.PersonController;

import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.NoDataException;
import Event.EventManager;
import Event.RoomManager;
import Person.PersonManager;

// Programmers: Sarah Kronenfeld
// Description: Abstract class to hold methods all EventMenus will need
// Date Created:
// Date Modified: 02/12/2020

public abstract class EventMenu implements SubMenuPrinter {
    protected RoomManager rooms;
    protected EventManager events;
    protected PersonManager persons;

    public EventMenu(RoomManager rooms, EventManager events, PersonManager persons) {
        this.rooms = rooms;
        this.events = events;
        this.persons = persons;
    }

    public String[] getRoomList() throws NoDataException{
        try {
            return rooms.getRoomNames();
        }
        catch (NullPointerException e) {
            throw new NoDataException("room");
        }
    }

    public String getEventListTitle(){
        return " -EVENTS-";
    }

    protected String getEventListTitle(String condition){
        return "\n-EVENTS " + condition.toUpperCase() + "-";
    }

    protected String[] printEventList(String[] eventIDList) throws InvalidChoiceException {
        try {
            String[] events = new String[eventIDList.length];
            for (int i  = 0; i < eventIDList.length; i++) {
                events[i] = formatEvent(eventIDList[i]);
            }
            return events;
        }
        catch (NullPointerException e) {
            throw new InvalidChoiceException("event");
        }
    }

    private String formatEvent(String eventID) throws InvalidChoiceException  {
        try {
            StringBuilder e = new StringBuilder();
            e.append(events.getEventType(eventID).toString());
            e.append(": ");
            e.append(events.getEventName(eventID));
            e.append("\nby ");
            e.append(persons.getName(events.getSpeakerID(eventID)));
            e.append(" in room ");
            e.append(rooms.getEventRoom(eventID));
            e.append("\nfrom ");
            e.append(events.getStartTime(eventID));
            e.append(" to ");
            e.append(events.getEndTime(eventID));
            e.append("\n");
            e.append(events.getDescription(eventID));
            return e.toString();
        } catch (NullPointerException n) {
            throw new InvalidChoiceException("event");
        }
    }

}
