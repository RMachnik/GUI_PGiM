package cw2;

import common.Picture;
import common.PictureCustoms;
import cw1.Conversions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: SG0219139
 * Date: 10/20/13
 */
public class Cw2 {

    public Cw2(Picture picture) {
        cw2(picture);
    }

    private void cw2(final Picture picture) {
        JMenuBar menuBar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu menuCw2 = new JMenu("CW2");
        menuBar.add(menuCw2);
        JMenuItem grayScale = new JMenuItem("Gray scale");
        grayScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(Conversions.toGrayScale(picture));
            }
        });



        menuCw2.add(grayScale);
    }
}
