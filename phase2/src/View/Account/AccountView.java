package View.Account;

import Presenter.Central.SubMenuPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A view that is instantiated with a Controller and builds frame based on said Controller
 */
public abstract class AccountView implements ActionListener {
    public JFrame frame = new JFrame();
    public JPanel contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
    public JButton okayButton = newButton("okay");
    public JButton continueButton = newButton("continue");
    public JButton backButton = newButton("back");
    JComboBox<String> dropDownMenu;
    public String eventName;
    public final String[] menuOp;

    public static final Color whiteBG = new Color(255, 255, 200);
    public static final Color yellowBG = new Color(255, 200, 0);
    public static final Color pinkBG = new Color(255, 100, 200);

    /**
     * Abstract class; A view that is instantiated and run by the Account Class.
     * Initializes basic frame with dropDownMenu and navigation buttons.
     * @param presenter the string container for this class
     */
    public AccountView(SubMenuPrinter presenter) {
        frame.setTitle(presenter.getMenuTitle()); // Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);

        menuOp = presenter.getMenuOptions();

        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame

        continueButton.setToolTipText("click this button to navigate to the chosen menu");
        backButton.setToolTipText("click this button to go back to the previous menu");

        makeDropDownMenu(presenter);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
        showMainDropDownMenu();
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
     * Shows dropDownMenu
     */
    public void showMainDropDownMenu() {
        hideAll();
        dropDownMenu.setVisible(true);
        continueButton.setVisible(true);
        // TODO remove lines below once okay button stops being altered
        okayButton.setText("okay");
        okayButton.setActionCommand("okay");
    }

    /**
     * Hides dropDownMenu
     */
    public void hideMainDropDownMenu() {
        dropDownMenu.setVisible(false);
        continueButton.setVisible(false);
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
        dropDownMenu.setToolTipText("select a menu to navigate to");
        initializeObject(dropDownMenu);
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
        initializeObject(newButton);
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
        eventName = event.getActionCommand();
        assert eventName != null;

        if(eventName.equals(backButton.getActionCommand()) ||
                eventName.equals(okayButton.getActionCommand())) {
            showMainDropDownMenu();
        }

        if (eventName.equals("okay")) {
            showMainDropDownMenu();
        }

        if (eventName.equals(continueButton.getActionCommand())) {
            eventName = (String)dropDownMenu.getSelectedItem();
        }
    }

    protected void initializeObject(JComponent object) {
        contentPane.add(object);
        object.setVisible(false);
    }
}
