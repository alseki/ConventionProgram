package Presenter;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to an Organizer's Event planning
// Date Created: 13/11/2020
// Date Modified: 13/11/2020

public class OrgEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        return true;
    }

    /**
     * Prompts user to input the room they wish to add
     * @return true iff room adding prompt was printed
     */
    public boolean addRoomPrompt() {
        return true;
    }



}
