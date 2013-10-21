package cw2;

import com.google.common.base.Function;
import common.Picture;
import common.PictureCustoms;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: SG0219139
 * Date: 10/21/13
 */
public class PassArgDialog extends JDialog implements ActionListener {
    private Function<Pair<Integer, Integer>, Integer> function;
    private JPanel myPanel = null;
    private JButton yesButton = null;
    private JButton noButton = null;
    private boolean answer = false;
    private JTextField textField = null;
    private Picture picture;
    private ConversionsCw2 conversions = new ConversionsCw2();

    public PassArgDialog(Picture pic, Function<Pair<Integer, Integer>, Integer> func, JFrame frame, boolean modal, String myMessage) {
        super(frame, modal);
        picture = pic;
        function = func;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (yesButton == e.getSource()) {
            Integer arg = Integer.parseInt(textField.getText());

            setVisible(false);
            PictureCustoms.showImageInNewWindow(conversions.transformUsingFunction(picture, function, arg));

        } else if (noButton == e.getSource()) {
            System.err.println("User chose no.");
            answer = false;
            setVisible(false);
        }
    }
}

