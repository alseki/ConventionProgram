package Events;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 18 2020

// Architecture Level - Use Class

public class RoomManager implements Serializable {

    /** A mapping of Room IDs to their respective EventManager objects. */
    private Map<String, EventManager> roomList;

    /** A mapping of Room names to their respective IDs. */
    private Map<String, String> roomsByName;
    /**
     * Constructor for RoomManager objects
     */
    public RoomManager() {
        roomList = new TreeMap<String, EventManager>();
        roomsByName = new TreeMap<String, String>();
    }

    /**
     * Finds the EventManager object for a specific Room's Events (by ID)
     * @param ID The ID of the Room
     * @return The Room's EventManager
     */
    public EventManager getRoom(String ID) {
        return roomList.get(ID);
    }

    /**
     * Returns a list of the names of all Rooms currently established at this conference
     * @return The Room's EventManager
     */
    public String[] getRoomNames() {
        String[] names = {};
        return roomsByName.keySet().toArray(names);
    }

    /**
     * Returns the number of Rooms this RoomManager contains
     * @return ^
     */
    public int numRooms() {
        return roomList.size();
    }

    /**
     * Finds the ID of a specific Room (by name)
     * @param name The Room's name
     * @return The Room's ID
     */
    public String getRoomID (String name) {
        return roomsByName.get(name);
    }

    /**
     * Finds the name of a specific Room (by ID)
     * @param ID The Room's ID
     * @return The Room's name
     */
    public String getRoomName (String ID) {
        return roomList.get(ID).getRoomName();
    }

    /**
     * Creates a new Room with the inputted capacity
     * @param capacity The name of the new Room
     * @return The ID of the new Room
     */
    public String addRoom(String name, int capacity) {
        Room thisRoom = new Room(name, capacity);
        RoomPermissions thisPermissions = new RoomPermissions(thisRoom);
        roomList.put(thisRoom.getID(), new EventManager(thisPermissions));
        roomsByName.put(thisRoom.getName(), thisRoom.getID());
        return thisRoom.getID();
    }

    /**
     * Returns the EventManager for the Room in which a particular Event is held
     * @param eventName The name of the Event for which we need to find the EventManager
     * @return The EventManager
     */
    public EventManager getEventRoom(String eventName) {
        if(getRoomNames().length > 0) {
            for(String room: getRoomNames()) {
                EventManager manager = this.getRoom(this.getRoomID(room));
                if (manager.getEventID(eventName) != null) {
                    return manager;
                }
            }
        }
        return null;
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
            String[] roomIDs = {};
            roomIDs = roomList.keySet().toArray(roomIDs);
            for(String id: roomIDs) {
                EventManager thisRoom = this.getRoom(id);
                if (!thisRoom.equals(rooms2.getRoom(rooms2.getRoomID(thisRoom.getRoomName())))) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
