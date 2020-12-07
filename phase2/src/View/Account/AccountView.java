package View.Account;

import Presenter.Central.SubMenuPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A view that is instantiated with a Controller and builds frame based on said Controller
 */
public abstract class AccountView implements ActionListener {
    public JFrame frame = new JFrame();
    public JPanel contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
    ArrayList<JButton> menuButtons = new ArrayList<>();

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
     */
    public void makeMenuButtons(SubMenuPrinter presenter) {
        for (String option: presenter.getMenuOptions()) {
            menuButtons.add(newButton(option));
        }
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
}
