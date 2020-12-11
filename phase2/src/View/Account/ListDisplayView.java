package View.Account;

import Presenter.Central.SubMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListDisplayView implements ActionListener {
    JButton returnButton;
    JLabel printedList;
    JFrame frame;
    JPanel contentPane;

    /**
     * A view that displays a bullet point list of Strings
     * @param title the title of the frame
     * @param options the array of Strings to be on the list
     */
    public ListDisplayView(String title, String[] options) {
        frame = new JFrame(title); // Create and set up the frame
        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 255, 255));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        StringBuilder list = new StringBuilder();
        for (String option: options) {
            list.append("<li>");
            list.append(option);
            list.append("<br><br>");
        }

        printedList = new JLabel("<html>" + list.toString() + "<html/>");
        contentPane.add(printedList);
        printedList.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        printedList.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        printedList.setVisible(true);

        returnButton = new JButton("return");
        returnButton.setLocation(0, 0);
        returnButton.setActionCommand("close");
        returnButton.addActionListener(this);
        returnButton.setToolTipText("click this button to return to previous screen");
        contentPane.add(returnButton);
        returnButton.setVisible(true);

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        display();
    }

    /**
     * Shows every component stored in contentPane
     */
    private void display() {
        for (Component item: contentPane.getComponents()) {
            item.setVisible(true);
        }
        frame.setVisible(true);
    }

    /**
     * Hides every component stored in contentPane
     */
    private void hide() {
        for (Component item: contentPane.getComponents()) {
            item.setVisible(false);
        }
        frame.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("close")) {
            hide();
        }
    }
}
