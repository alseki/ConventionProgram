package View.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatListDisplayView extends ListDisplayView{

    JButton checkButton;
    JButton archiveButton;
    JButton exitButton;
    JButton submitButton;
    JDialog chatSerialNumber;
    JPanel dialogPanel;
    Container container;

    public ChatListDisplayView(String title, String[] options) {
        super(title, options);
        contentPane.setBackground(new Color(121, 193, 154));
        checkButton = new JButton("Check");
        checkButton.setBounds(100, 600, 80, 30);
        checkButton.setActionCommand("checkMessages");
        checkButton.addActionListener(this);
        checkButton.setToolTipText("click this button to Exit and Delete Chat");
        contentPane.add(checkButton);
        checkButton.setVisible(true);

        archiveButton = new JButton("Archive");
        archiveButton.setBounds(200, 600, 80, 30);
        archiveButton.setActionCommand("Archive");
        archiveButton.addActionListener(this);
        archiveButton.setToolTipText("click this button to Exit and Delete Chat");
        contentPane.add(archiveButton);
        archiveButton.setVisible(true);

        exitButton = new JButton("Delete");
        exitButton.setBounds(300, 600, 80, 30);
        exitButton.setActionCommand("exitChat");
        exitButton.addActionListener(this);
        exitButton.setToolTipText("click this button to Exit and Delete Chat");
        contentPane.add(exitButton);
        exitButton.setVisible(true);

        /*JRadioButton jb1 = new JRadioButton("11111111111111"+"\n" + "22222222222222");
        jb1.setBounds(10, 20, 380, 30);
        JRadioButton jb2 = new JRadioButton("2");
        JRadioButton jb3 = new JRadioButton("3");

        ButtonGroup group = new ButtonGroup();
        group.add(jb1);
        group.add(jb2);
        group.add(jb3);


        contentPane.add(jb1);
        contentPane.add(jb2);
        contentPane.add(jb3);
        frame.pack();*/
        StringBuilder list = new StringBuilder();
        for (String option: options) {
            list.append("<li>");
            list.append(option);
            list.append("<br><br>");
        }

        printedList = new JLabel("<html>" + list.toString() + "</html>");
        printedList.setBounds(10, 10, 200, 300);
        contentPane.add(printedList);
        printedList.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        //printedList.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        printedList.setVisible(true);






    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("close")) {
            hide();
        }
        if (event.getActionCommand().equals("checkMessages")) {
            //hide();
        }

    }
}
