package Presenter;

public class SpeEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        System.out.println("----- Speaker Event Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        // TODO add print statements for all the other menu options
        return true;
    }

}
