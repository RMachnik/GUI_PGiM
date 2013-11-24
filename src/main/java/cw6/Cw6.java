package cw6;

import common.Picture;
import common.PictureCustoms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * User: rafik991@gmail.com
 * Date: 11/24/13
 */
public class Cw6 {
    public Cw6(Picture picture) {
        cw6(picture);
    }

    private void cw6(final Picture picture) {
        final ConversionCw6 conversionCw6 = new ConversionCw6();
        JMenuBar menuBar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu menuCw6 = new JMenu("CW6");
        menuBar.add(menuCw6);

        JMenuItem zad1a = new JMenuItem("zad1a");
        zad1a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.erosion(picture, "static/cw6_zad1a.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad1b = new JMenuItem("zad1b");
        zad1b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.erosion(picture, "static/cw6_zad1b.txt"));
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
                    PictureCustoms.showImageInNewWindow(conversionCw6.erosion(picture, "static/cw6_zad2a.txt"));
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
                    PictureCustoms.showImageInNewWindow(conversionCw6.erosion(picture, "static/cw6_zad2b.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad3 = new JMenuItem("zad3");
        zad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PassRForCircleDialog passRForCircleDialog = new PassRForCircleDialog(picture, false, "Pass R to circle: ");
                passRForCircleDialog.repaint();
            }
        });

        JMenuItem zad4a = new JMenuItem("zad4a");
        zad4a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.dilatation(picture, "static/cw6_zad1a.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad4b = new JMenuItem("zad4b");
        zad4b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.dilatation(picture, "static/cw6_zad1b.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad5a = new JMenuItem("zad5a");
        zad5a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.dilatation(picture, "static/cw6_zad2a.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad5b = new JMenuItem("zad5b");
        zad5b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.dilatation(picture, "static/cw6_zad2b.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad10aErosion = new JMenuItem("zad10aErosion");
        zad10aErosion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.erosionForRGB(picture, "static/cw6_zad2b"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad10bErosion = new JMenuItem("zad10bErosion");
        zad10bErosion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.erosionForRGB(picture, "static/cw6_zad2a"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem zad10aDilation = new JMenuItem("zad10aDilation");
        zad10aDilation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.dilatationForRGB(picture, "static/cw6_zad2b"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        JMenuItem zad10bDilation = new JMenuItem("zad10bDilation");
        zad10bDilation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PictureCustoms.showImageInNewWindow(conversionCw6.dilatationForRGB(picture, "static/cw6_zad2a"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        menuCw6.add(zad1a);
        menuCw6.add(zad1b);
        menuCw6.add(zad2a);
        menuCw6.add(zad2b);
        menuCw6.add(zad3);
        menuCw6.add(zad4a);
        menuCw6.add(zad4b);
        menuCw6.add(zad5a);
        menuCw6.add(zad5b);
        menuCw6.add(zad10aErosion);
        menuCw6.add(zad10bErosion);
        menuCw6.add(zad10aDilation);
        menuCw6.add(zad10bDilation);
    }
}
