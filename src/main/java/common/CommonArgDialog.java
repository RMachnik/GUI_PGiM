package common;

import cw1.ConversionsCw1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: SG0219139
 * Date: 10/21/13
 */
public class CommonArgDialog extends JDialog implements ActionListener {
    private JPanel myPanel = null;
    private JButton yesButton = null;
    private JButton noButton = null;
    private boolean answer = false;
    private JTextField textField = null;
    private Picture picture;


    public CommonArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture.getFrame(), modal);
        this.picture = picture;
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
        setLocationRelativeTo(picture.getFrame());
        setVisible(true);
    }


    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public JButton getNoButton() {
        return noButton;
    }

    public void setNoButton(JButton noButton) {
        this.noButton = noButton;
    }

    public JPanel getMyPanel() {
        return myPanel;
    }

    public void setMyPanel(JPanel myPanel) {
        this.myPanel = myPanel;
    }

    public JButton getYesButton() {
        return yesButton;
    }

    public void setYesButton(JButton yesButton) {
        this.yesButton = yesButton;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
