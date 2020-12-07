package Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Dec 6 2020

// Architecture Level - Use Class

abstract class RoomAccess {

    // RoomAccess is an abstract class representing an object that can find Rooms at this convention by RoomID.

    protected abstract Room getRoom(String roomID);
    abstract String[] getEventIDs(String roomID);

}

public class RoomManager extends RoomAccess implements Serializable {

    /** A mapping of Room IDs to the lists of IDs for Events taking place in those Rooms. */
    private Map<String, ArrayList<String>> roomEventList;

    /** A mapping of Room IDs to their respective Room objects. */
    private Map<String, Room> roomList;

    /** A mapping of Room names to their respective IDs. */
    private Map<String, String> roomsByName;

    /**
     * Constructor for RoomManager objects
     */
    public RoomManager() {
        roomEventList = new TreeMap<String, ArrayList<String>>();
        roomList = new TreeMap<String, Room>();
        roomsByName = new TreeMap<String, String>();
    }

    /**
     * Returns the Room associated with this RoomID
     * @param roomID The Room's ID
     * @return The Room
     */
    protected Room getRoom(String roomID) {
        try {
            return this.roomList.get(roomID);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Finds the ID of a specific Room (by name)
     * @param name The Room's name
     * @return The Room's ID
     */
    public String getRoomID (String name) {
        try {
            return this.roomsByName.get(name);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Finds the name of a specific Room (by ID)
     * @param ID The Room's ID
     * @return The Room's name
     */
    public String getRoomName (String ID) {
        try {
            return this.roomList.get(ID).getName();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Returns a list of the names of all Rooms currently established at this convention
     * @return An array of the Room names at this convention
     */
    public String[] getRoomNames() {
        String[] names = {};
        try {
            return this.roomsByName.keySet().toArray(names);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Gets the Room an Event is held in
     * @param ID the ID of the Event
     * @return the ID of the Room
     */
    public String getEventRoom(String ID) {
        try {
            for (String room : getRoomNames()) {
                ArrayList<String> events = this.roomEventList.get(roomsByName.get(room));
                if (events.contains(ID)) {
                    return room;
                }
            }
            return null;
        } catch (NullPointerException n) {
            return null;
        }
    }

    /**
     * Finds the list of IDs of the Events taking place in a specific Room
     * @param roomID The ID of the Room
     * @return The list of IDs of the Events taking place in that Room
     */
    public String[] getEventIDs(String roomID) {
        try {
            String[] evList = {};
            return this.roomEventList.get(roomID).toArray(evList);
        } catch (NullPointerException n) {
            return null;
        }
    }

    /**
     * Returns the number of Rooms currently in this convention
     * @return the number of Rooms currently in this convention
     */
    public int numRooms() {
        return this.roomList.size();
    }

    /**
     * Creates a new Room with the inputted capacity
     * @param name The name of the new Room
     * @param capacity The capacity of the new Room
     * @return The ID of the new Room
     */
    public String addRoom(String name, int capacity) {
        Room thisRoom = new Room(name, capacity);
        this.roomList.put(thisRoom.getID(), thisRoom);
        this.roomsByName.put(thisRoom.getName(), thisRoom.getID());
        this.roomEventList.put(thisRoom.getID(), new ArrayList<String>());
        return thisRoom.getID();
    }

    /**
     * Adds an Event to a certain Room's list of Event IDs
     * @param roomID The Room's ID
     * @param eventID The Event's ID
     * @return True if successful, false if unsuccessful (i.e. Room does not have the capacity for this Event)
     */
    public boolean addEvent(String roomID, String eventID) {
        try {
            int current_occupancy = 0;
            for (String ID : this.getEventIDs(roomID)) {
                int event_capacity;
                // FIXME
                // event_capacity = EventManager.getCapacity(ID);

            }


            // FIXME
            //int current_occupancy =



            // FIXME
            // String[] getEventIDs(String roomID)

            ArrayList<String> events = roomEventList.get(roomID);
            // FIXME
            // room.add(eventID);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Checks to see if this convention contains a Room of a certain name
     * @param name The name
     * @return True if an Room with this name exists; false if not
     */
    public boolean contains(String name) {
        if (getRoomID(name)!= null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (obj.getClass() != RoomManager.class){
            System.out.println("Wrong class!");
            return false;
        }
        RoomManager rooms2 = (RoomManager) obj;
        if (rooms2.numRooms() == this.numRooms()) {
            if(rooms2.getRoomNames().equals(this.getRoomNames())) {
                for (String room : this.getRoomNames()) {
                    String id = this.getRoomID(room);
                    if (rooms2.getEventIDs(id).equals(this.getEventIDs(id))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
