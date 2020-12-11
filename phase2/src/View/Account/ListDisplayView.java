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
        contentPane.setPreferredSize(new Dimension(400,660));
        contentPane.setBackground(new Color(255, 255, 255));// Sets background colour to white
        //contentPane.setLayout(null);
        //contentPane.setLayout(new FlowLayout());

        StringBuilder list = new StringBuilder();
        for (String option: options) {
            list.append("<li>");
            list.append(option);
            list.append("<br><br>");
        }

        /*printedList = new JLabel("<html>" + list.toString() + "<html/>");
        printedList.setBounds(10, 10, 300, 300);
        contentPane.add(printedList);
        printedList.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        //printedList.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        printedList.setVisible(true);*/

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setText("Hello World!");
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        editorScrollPane.setBackground(null);
        editorScrollPane.setBorder(null);
        contentPane.add(editorScrollPane);


        returnButton = new JButton("return");
        returnButton.setBounds(10, 600, 80, 30);
        returnButton.setActionCommand("close");
        returnButton.addActionListener(this);
        returnButton.setToolTipText("click this button to return to previous screen");
        contentPane.add(returnButton);

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
    void hide() {
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
