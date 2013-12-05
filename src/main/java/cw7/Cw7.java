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

        JMenuItem zad2a = new JMenuItem("zad2a");
        zad2a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionsCw7.iterativeMethod(picture,
                            "static/cw7_zad2a.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad2b = new JMenuItem("zad2b");
        zad2b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionsCw7.iterativeMethod(picture,
                            "static/cw7_zad2b.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        menuCw7.add(zad1);
        menuCw7.add(zad2a);
        menuCw7.add(zad2b);

    }
}
