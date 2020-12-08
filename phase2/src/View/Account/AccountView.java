package View.Account;

import Presenter.Central.SubMenuPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A view that is instantiated with a Controller and builds frame based on said Controller
 */
public abstract class AccountView implements ActionListener {
    public JFrame frame = new JFrame();
    public JPanel contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
    public JButton okayButton = new JButton("okay");
    ArrayList<JButton> menuButtons = new ArrayList<>();
    JComboBox<String> dropDownMenu;

    public static final Color whiteBG = new Color(255, 255, 200);
    public static final Color yellowBG = new Color(255, 200, 0);
    public static final Color pinkBG = new Color(255, 100, 200);

    public AccountView(SubMenuPrinter presenter) {
        frame.setTitle(presenter.getMenuTitle()); // Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);

        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setLayout(new FlowLayout());

        contentPane.add(okayButton);
        okayButton.setVisible(false);

        makeMenuButtons(presenter);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
        showMainMenuButtons();
    }

    /**
     * Hides every component stored in contentPane
     */
    public void hideAll() {
        for (Component item: contentPane.getComponents()) {
            item.setVisible(false);
        }
    }

    /**
     * Builds all the menu buttons for this view
     * @param presenter the view's presenter menu class
     */
    public void makeMenuButtons(SubMenuPrinter presenter) {
        for (String option: presenter.getMenuOptions()) {
            menuButtons.add(newButton(option));
        }
    }

    /**
     * Shows the button options for the main menu of account view object
     */
    public void showMainMenuButtons() {
        hideAll();
        for (JButton button: menuButtons) {
            button.setVisible(true);
        }
    }

    /**
     * Hides the button options for the main menu of account view object
     */
    public void hideMainMenuButtons() {
        for (JButton button: menuButtons) {
            button.setVisible(false);
        }
    }

    /**
     * Shows dropDownMenu
     */
    public void showMainDropDownMenu() {
        hideAll();
        dropDownMenu.setVisible(true);
    }

    /**
     * Hides dropDownMenu
     */
    public void hideMainDropDownMenu() {
        dropDownMenu.setVisible(false);
    }

    /**
     * Builds drop down menu
     * @param presenter the view's presenter menu class
     */
    public void makeDropDownMenu(SubMenuPrinter presenter) {
        dropDownMenu = new JComboBox<>(presenter.getMenuOptions());// Generates dropdown menu
        dropDownMenu.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        dropDownMenu.setSelectedIndex(0);
        dropDownMenu.addActionListener(this);
        contentPane.add(dropDownMenu);
        dropDownMenu.setVisible(false);
    }

    /**
     * Creates a new button component with an ActionListener
     * @param title the text and action listener of the button
     * @return a button with text and action listener 'title'
     */
    public JButton newButton(String title) {
        JButton newButton = new JButton(title);
        newButton.setLocation(0, 0);
        newButton.setActionCommand(title);
        newButton.addActionListener(this);
        contentPane.add(newButton);
        newButton.setVisible(false);
        return newButton;
    }

    /**
     * Opens a JOptionPane box that displays the exception message
     * @param exceptionTitle A title for the box
     * @param exceptionText The text the box should display
     */
    public void exceptionDialogBox(String exceptionTitle, String exceptionText) {
        JOptionPane.showConfirmDialog(null, exceptionText, exceptionTitle,
                JOptionPane.DEFAULT_OPTION);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        if(eventName.equals("okay")) {
            showMainMenuButtons();
        }
    }

    protected void initializeObject(JComponent object) {
        contentPane.add(object);
        object.setVisible(false);
    }
}
