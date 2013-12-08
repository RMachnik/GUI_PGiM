package cw8;

import common.Picture;
import cw4.ConversionsCw4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * rafik991@gmai.com
 * 12/8/13
 */
public class Cw8 {
    public Cw8(Picture picture) {
        cw8(picture);
    }

    private void cw8(final Picture picture) {
        final ConversionsCw8 conversionsCw8 = new ConversionsCw8();
        final ConversionsCw4 conversionsCw4 = new ConversionsCw4();
        JMenuBar bar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu cw8 = new JMenu("CW8");
        bar.add(cw8);

        JMenuItem zad1 = new JMenuItem("zad1");
        zad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SteadyDysfunctionArgDialog steadyDysfunctionArgDialog = new SteadyDysfunctionArgDialog(picture,
                        false, "Pass level,probability");
                steadyDysfunctionArgDialog.repaint();
            }
        });

        JMenuItem zad1b = new JMenuItem("zad1b");
        zad1b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SteadyDysfunctionArgDialog steadyDysfunctionArgDialog = new SteadyDysfunctionArgDialog
                        (new Picture(conversionsCw4
                                .otsuBinaryConversion(picture)),
                                false, "Pass level,probability");
                steadyDysfunctionArgDialog.repaint();
            }
        });

        JMenuItem zad2 = new JMenuItem("zad2");
        zad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NormalFilterArgDialog normalFilterArgDialog = new NormalFilterArgDialog(picture, false, "Pass mean," +
                        "variation,probability");
                normalFilterArgDialog.repaint();
            }
        });

        JMenuItem zad2b = new JMenuItem("zad2b");
        zad2b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NormalFilterArgDialog normalFilterArgDialog = new NormalFilterArgDialog(new Picture(conversionsCw4
                        .otsuBinaryConversion(picture)), false,
                        "Pass mean," +
                                "variation,probability");
                normalFilterArgDialog.repaint();
            }
        });

        JMenuItem zad3 = new JMenuItem("zad3");
        zad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaltPepperArgDialog saltPepperArgDialog = new SaltPepperArgDialog(picture, false, "Pass probability ");
                saltPepperArgDialog.repaint();
            }
        });

        JMenuItem zad3b = new JMenuItem("zad3b");
        zad3b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaltPepperArgDialog saltPepperArgDialog = new SaltPepperArgDialog(new Picture(conversionsCw4
                        .otsuBinaryConversion(picture)),
                        false,
                        "Pass probability ");
                saltPepperArgDialog.repaint();
            }
        });

        JMenuItem zad4 = new JMenuItem("zad4");
        zad4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaltPepperArgDialog saltPepperArgDialog = new SaltPepperArgDialog(new Picture(conversionsCw4
                        .otsuBinaryConversion(picture)),
                        false,
                        "Pass probability ");
                saltPepperArgDialog.repaint();
                PassRGBArgDialog passRGBArgDialog = new PassRGBArgDialog(saltPepperArgDialog.getPicture(), false,
                        "Pass windowSize,R|G|B", saltPepperArgDialog);
                passRGBArgDialog.repaint();
            }
        });

        cw8.add(zad1);
        cw8.add(zad1b);
        cw8.add(zad2);
        cw8.add(zad2b);
        cw8.add(zad3);
        cw8.add(zad3b);
        cw8.add(zad4);
    }
}
