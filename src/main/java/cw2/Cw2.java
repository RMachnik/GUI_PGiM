package cw2;

import common.Picture;
import common.PictureCustoms;
import cw1.ConversionsCw1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: SG0219139
 * Date: 10/20/13
 */
public class Cw2 {
    private ConversionsCw2 conversions = new ConversionsCw2();
    private ConversionsCw1 conversionsCw1 = new ConversionsCw1();

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
                PictureCustoms.showImageInNewWindow(conversionsCw1.toGrayScale(picture));
            }
        });

        JMenuItem negative = new JMenuItem("Negative");
        negative.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversions.negative(picture));
            }
        });

        JMenuItem sepia = new JMenuItem("Sepia");
        sepia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversions.sepia(picture,40));
            }
        });



        menuCw2.add(grayScale);
        menuCw2.add(negative);
        menuCw2.add(sepia);
    }
}
