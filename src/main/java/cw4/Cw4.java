package cw4;

import common.Picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: SG0219139
 * Date: 11/9/13
 */
public class Cw4 {

    public Cw4(Picture picture) {
        cw4(picture);
    }

    private void cw4(final Picture picture) {
        JMenuBar menuBar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu menuCw4 = new JMenu("CW4");
        menuBar.add(menuCw4);

        JMenuItem simpleBinaryConversion = new JMenuItem("Simple binary conversion");
        simpleBinaryConversion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BinaryThresholdDialog binaryThresholdDialog = new BinaryThresholdDialog(picture, false, "Pass binary threshold: ");
                binaryThresholdDialog.repaint();
            }
        });

        JMenuItem simpleRangeBinaryConversion = new JMenuItem("Simple range binary conversion");
        simpleRangeBinaryConversion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BinaryRangeDialog binaryRangeDialog = new BinaryRangeDialog(picture,false,"Pass correct binary range 'a,b': ");
                binaryRangeDialog.repaint();
            }
        });


        menuCw4.add(simpleBinaryConversion);
        menuCw4.add(simpleRangeBinaryConversion);
    }
}
