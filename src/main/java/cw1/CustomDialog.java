package cw1;

import add.PictureCustoms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: SG0219139
 */
public class CustomDialog extends JDialog implements ActionListener {
    private JPanel myPanel = null;
    private JButton yesButton = null;
    private JButton noButton = null;
    private boolean answer = false;
    private JTextField textField = null;

    public CustomDialog(JFrame frame, boolean modal, String myMessage) {
        super(frame, modal);

        myPanel = new JPanel();

        getContentPane().add(myPanel);
        myPanel.add(new JLabel(myMessage));
        textField = new JTextField();
        textField.setColumns(5);
        myPanel.add(textField);
        yesButton = new JButton("OK");
        yesButton.addActionListener(this);
        myPanel.add(yesButton);
        noButton = new JButton("Cancel");
        noButton.addActionListener(this);
        myPanel.add(noButton);

        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public boolean getAnswer() {
        return answer;
    }

    public void actionPerformed(ActionEvent e) {
        if (yesButton == e.getSource()) {
            String[] size = textField.getText().split("X");
            if (size.length == 2) {
                setVisible(false);
                PictureCustoms.showImageInNewWindow(Conversions.createCheckerBoard(Integer.parseInt(size[0]), Integer.parseInt(size[1])));
            }
        } else if (noButton == e.getSource()) {
            System.err.println("User chose no.");
            answer = false;
            setVisible(false);
        }
    }

}