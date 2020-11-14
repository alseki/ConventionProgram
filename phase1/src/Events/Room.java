package Events;

// Contributors: Sarah Kronenfeld, Eytan Weinstein
// Last edit: Nov 14 2020

// Architecture Level - Entity

import java.util.UUID;

class Room {
    private int capacity;
    private String ID;
    private String name;

    public Room () {
        this.capacity = 2;
        ID = UUID.randomUUID().toString();
        name = "Room " + ID;
    }

    public Room (int capacity) {
        this.capacity = capacity;
        ID = UUID.randomUUID().toString();
        name = "Room " + ID;
    }

    public Room (String name) {
        this.capacity = 2;
        this.name = name;
        ID = UUID.randomUUID().toString();
    }

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
     * @return the name of the Room (as a string)
     */
    public String getName() {
        return name;
    }
}

