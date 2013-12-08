package cw7;

import common.Picture;
import common.PictureCustoms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by SG0219139 on 12/5/13.
 */
public class Cw7 {
    public Cw7(Picture picture) {
        cw7(picture);
    }

    private void cw7(final Picture picture) {
        final ConversionsCw7 conversionsCw7 = new ConversionsCw7();
        JMenuBar bar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu menuCw7 = new JMenu("CW7");
        bar.add(menuCw7);


        JMenuItem zad1 = new JMenuItem("zad1");
        zad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionsCw7.erosion(picture, "static/cw7_zad1.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad2 = new JMenuItem("zad2a");
        zad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IterationArgDialog iterationArgDialog = new IterationArgDialog(picture, false,
                        "Pass number of iterations");
                iterationArgDialog.repaint();
            }
        });

        JMenuItem zad3 = new JMenuItem("zad3b");
        zad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IterationZad3ArgDialog iterationZad3ArgDialog = new IterationZad3ArgDialog(picture, false,
                        "Pass number of iterations");
                iterationZad3ArgDialog.repaint();
            }
        });


        menuCw7.add(zad1);
        menuCw7.add(zad2);
        menuCw7.add(zad3);

    }
}
