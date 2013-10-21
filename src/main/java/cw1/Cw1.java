package cw1;

import common.Picture;
import common.PictureCustoms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by SG0219139 on 10/13/13.
 */
public class Cw1 {
    private ConversionsCw1 conversions = new ConversionsCw1();
    public Cw1(Picture picture) {
        cw1(picture);
    }

    private void cw1(final Picture picture) {
        JMenuBar menuBar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu menuCw1 = new JMenu("CW1");
        JMenuItem r = new JMenuItem("R");
        JMenuItem g = new JMenuItem("G");
        JMenuItem b = new JMenuItem("B");
        JMenuItem grayScale = new JMenuItem("Black/white photo");
        JMenuItem createCheckerBoard = new JMenuItem("Create CheckerBoard");
        JMenuItem transformToVHS = new JMenuItem("Transform RGB to VHS");
        JMenuItem transformVHSToRgb = new JMenuItem("Transform VHS to Rgb");
        JMenuItem draw = new JMenuItem("Draw Checker Board");
        r.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversions.rgb((BufferedImage) image, "red_scale.txt", 0));

            }
        });
        g.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversions.rgb((BufferedImage) image, "green_scale.txt", 1));
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversions.rgb((BufferedImage) image, "blue_scale.txt", 2));
            }
        });
        JMenuItem saveRGBTxt = new JMenuItem("save rgb to rgb.txt");
        saveRGBTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conversions.pictureToTxt(picture.getImage(), "rgb.txt");
            }
        });
        grayScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversions.toGrayScale(picture));
            }
        });
        createCheckerBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversions.createCheckerBoard(500, 20));
            }
        });
        transformToVHS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversions.transformRGBToVHS(picture.getImage(), 0.111, 0.299));
            }
        });
        transformVHSToRgb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PictureCustoms.showImageInNewWindow(conversions.transformVHSToRgb(conversions.transformRGBToVHS
                        (picture.getImage(), 0.111, 0.299),
                        0.111, 0.299));
            }
        });
        draw.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ChessSizeDialog customDialog  = new ChessSizeDialog(picture,true,"Please provide Checker size: sizeOfBoardXsizeOfBlock");
                customDialog.repaint();

            }
        });
        menuBar.add(menuCw1);
        menuCw1.add(r);
        menuCw1.add(g);
        menuCw1.add(b);
        menuCw1.add(grayScale);
        menuCw1.add(saveRGBTxt);
        menuCw1.add(transformToVHS);
        menuCw1.add(transformVHSToRgb);
        menuCw1.add(draw);

    }
}
