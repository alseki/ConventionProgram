package Events;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 15 2020

// Architecture Level - Use Class

public class RoomManager {

    /** A mapping of Room IDs to their respective EventManager objects. */
    private Map<String, EventManager> roomList;

    /** A mapping of Room names to their respective IDs. */
    private Map<String, String> roomsByName;

    /** The start time of the conference held in all the Rooms managed here. */
    private LocalDateTime conferenceStart;

    /**
     * Constructor for RoomManager objects
     */
    public RoomManager() {
        //make serializeable?
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
     * Finds the ID of a specific Room (by name)
     * @param name The Room's name
     * @return The Room's ID
     */
    public String getRoomID (String name) {
        return roomsByName.get(name);
    }

    /**
     * Creates a new Room with an automatic capacity of 2
     * @param name The name of the new Room
     * @return The ID of the new Room
     */
    public String addRoom(String name) {
        Room thisRoom = new Room(name);
        RoomPermissions thisPermissions = new RoomPermissions(this.conferenceStart, thisRoom);
        roomList.put(thisRoom.getID(), new EventManager(thisPermissions));
        roomsByName.put(thisRoom.getName(), thisRoom.getID());
        return thisRoom.getID();
    }

    /**
     * Creates a new Room with capacity entered manually
     * @param name The name of the new Room
     * @param capacity The capacity of the new Room
     * @return The ID of the new Room
     */
    public String addRoom(String name, int capacity) {
        Room thisRoom = new Room(capacity, name);
        RoomPermissions thisPermissions = new RoomPermissions(this.conferenceStart, thisRoom);
        roomList.put(thisRoom.getID(), new EventManager(thisPermissions));
        roomsByName.put(thisRoom.getName(), thisRoom.getID());
        return thisRoom.getID();
    }

}
