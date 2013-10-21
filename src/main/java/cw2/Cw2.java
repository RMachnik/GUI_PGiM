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
                PictureCustoms.showImageInNewWindow(conversions.sepia(picture, 40));
            }
        });

        JMenuItem add = new JMenuItem("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PassArgDialog addingWindow = new PassArgDialog(picture, new ConversionsCw2.Increment(), picture.getFrame(), false, "Provide your argument: ");
                addingWindow.repaint();
            }
        });

        JMenuItem dec = new JMenuItem("Decrement");
        dec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PassArgDialog decrementWindow = new PassArgDialog(picture, new ConversionsCw2.Decrement(), picture.getFrame(), false, "Provide your argument: ");
                decrementWindow.repaint();
            }
        });

        JMenuItem multip = new JMenuItem("Multiply");
        dec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PassArgDialog multiWindow = new PassArgDialog(picture, new ConversionsCw2.Multiply(), picture.getFrame(), false, "Provide your argument: ");
                multiWindow.repaint();
            }
        });

        JMenuItem div = new JMenuItem("Division");
        dec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PassArgDialog divisionWindow = new PassArgDialog(picture, new ConversionsCw2.Division(), picture.getFrame(), false, "Provide your argument: ");
                divisionWindow.repaint();
            }
        });


        menuCw2.add(grayScale);
        menuCw2.add(negative);
        menuCw2.add(sepia);
        menuCw2.add(add);
        menuCw2.add(dec);
        menuCw2.add(multip);
        menuCw2.add(div);
    }
}
