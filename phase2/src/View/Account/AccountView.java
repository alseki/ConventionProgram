package View.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A view that is instantiated with a Controller and builds frame based on said Controller
 */
public abstract class AccountView implements ActionListener {
    public JFrame frame = new JFrame();
    public JPanel contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders

    public AccountView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
     * Creates a new button component with an ActionListener
     * @param title the text and action listener of the button
     * @return a button with text and action listener 'title'
     */
    public JButton newButton(String title) {
        JButton newButton = new JButton(title);
        newButton.setLocation(0, 0);
        newButton.setActionCommand(title);
        newButton.addActionListener(this);
        return newButton;
    }
}
