package Events;

import java.io.Serializable;
import java.util.UUID;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 15 2020

// Architecture Level - Entity

class Room implements Serializable {

    private int capacity;
    private String ID;
    private String name;

    /**
     * Constructor for a Room object, with a capacity of 2, name, and ID automatically assigned
     */
    public Room () {
        this.capacity = 2;
        ID = UUID.randomUUID().toString();
        name = "Room " + ID;
    }

    /**
     * Alternate constructor for a Room object, with name and ID automatically assigned
     * @param capacity The Room's capacity
     */
    public Room (int capacity) {
        this.capacity = capacity;
        ID = UUID.randomUUID().toString();
        name = "Room " + ID;
    }

    /**
     * Alternate constructor for a Room object, with capacity and ID automatically assigned
     * @param name The Room's name
     */
    public Room (String name) {
        this.capacity = 2;
        this.name = name;
        ID = UUID.randomUUID().toString();
    }

    /**
     * Alternate constructor for a Room object, with only ID automatically assigned
     * @param capacity The Room's capacity
     * @param name The Room's name
     */
    public Room (int capacity, String name) {
        this.capacity = capacity;
        this.name = name;
        ID = UUID.randomUUID().toString();
    }

    /**
     * Getter for the capacity of this Room
     * @return the capacity of the Room (as an int)
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Getter for the ID of this Room
     * @return the ID of this Room
     */
    public String getID() {
        return ID;
    }

    /**
     * Getter for the name of this Room
     * @return the name of the Room (as a String)
     */
    public String getName() {
        return name;
    }

}


