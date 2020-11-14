package Events;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.time.LocalDateTime;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 14 2020

// Architecture level - Use class
public class RoomManager {
    Map<String, EventManager> roomList;
    Map<String, String> roomsByName;
    LocalDateTime conferenceStart;


    /**
     * Creates a new RoomManager
     */
    public RoomManager() {
        //make serializeable?
        roomList = new TreeMap<String, EventManager>();
        roomsByName = new TreeMap<String, String>();
    }

    /**
     * Returns an EventManager for a specific Room's Events
     * @param id The ID of the Room
     * @return The Room's EventManager
     */
    public EventManager getRoom(String id) {
        return roomList.get(id);
    }

    /**
     * Finds the ID of a specific Room
     * @param name The Room's name
     * @return The Room's ID
     */
    public String getRoomId (String name) {
        return roomsByName.get(name);
    }

    /**
     * Creates a new Room of capacity 2
     * @param name The name of the new Room
     * @return The ID of the new Room
     */
    public String addRoom(String name) {
        Room thisRoom = new Room(name);
        roomList.put(thisRoom.getID(), new EventManager(thisRoom, conferenceStart));
        roomsByName.put(thisRoom.getName(), thisRoom.getID());
        return thisRoom.getID();
    }

    /**
     * Creates a new Room
     * @param name The name of the new Room
     * @param capacity The capacity of the new Room
     * @return The ID of the new Room
     */
    public String addRoom(String name, int capacity) {
        Room thisRoom = new Room(capacity, name);
        roomList.put(thisRoom.getID(), new EventManager(thisRoom, conferenceStart));
        roomsByName.put(thisRoom.getName(), thisRoom.getID());
        return thisRoom.getID();
    }

}
