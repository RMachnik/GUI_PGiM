package cw4;

import common.Picture;
import common.PictureCustoms;
import cw4.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: SG0219139
 * Date: 11/9/13
 */
public class Cw4 {
    private ConversionsCw4 conversionsCw4 = new ConversionsCw4();

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
                BinaryRangeDialog binaryRangeDialog = new BinaryRangeDialog(picture, false, "Pass correct binary range 'a,b': ");
                binaryRangeDialog.repaint();
            }
        });

        JMenuItem otsu = new JMenuItem("Otsu binary conversion");
        otsu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversionsCw4.otsuBinaryConversion(picture));
            }
        });

        JMenuItem bransen = new JMenuItem("Bransen binary conversion");
        bransen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BransenArgDialog bransenArgDialog = new BransenArgDialog(picture, false, "Pass bransen arg: ");
                bransenArgDialog.repaint();
            }
        });

        JMenuItem bransenWithOtsu = new JMenuItem("Bransen with otsu conversion");
        bransenWithOtsu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BransenWithOtsuDialog bransenWithOtsuDialog = new BransenWithOtsuDialog(picture, false, "Pass local and threshold values 'local,threshold': ");
                bransenWithOtsuDialog.repaint();
            }
        });

        JMenuItem greenPeppersEffect = new JMenuItem("Green pepper effect");
        greenPeppersEffect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversionsCw4.greenPeppersEffect(picture));
            }
        });


        menuCw4.add(simpleBinaryConversion);
        menuCw4.add(simpleRangeBinaryConversion);
        menuCw4.add(otsu);
        menuCw4.add(bransen);
        menuCw4.add(bransenWithOtsu);
        menuCw4.add(greenPeppersEffect);
    }
}
