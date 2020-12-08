package View.Account;

import Presenter.Central.SubMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListDisplayView implements ActionListener {
    JButton okButton;
    JLabel printedList;
    JFrame frame;
    JPanel contentPane;

    public ListDisplayView(String title, String[] options) {
        frame = new JFrame(title); // Create and set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 255, 255));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        StringBuilder list = new StringBuilder();
        for (String option: options) {
            list.append(option);
            list.append("\n\n");
        }

        printedList = new JLabel(list.toString());
        contentPane.add(printedList);
        printedList.setVisible(true);

        okButton = new JButton("return");
        okButton.setLocation(0, 0);
        okButton.setActionCommand("close");
        okButton.addActionListener(this);
        contentPane.add(okButton);
        okButton.setVisible(true);

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
    }

    /**
     * Shows every component stored in contentPane
     */
    public void display() {
        for (Component item: contentPane.getComponents()) {
            item.setVisible(true);
        }
        frame.setVisible(true);
    }

    /**
     * Hides every component stored in contentPane
     */
    public void hide() {
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
