package cw5;

import common.Picture;
import common.PictureCustoms;
import cw5.util.AddArgDialog;
import cw5.util.AddTwoPicturesDialog;
import cw5.util.ConversionsCw5;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * User: SG0219139
 * Date: 11/16/13
 */
public class Cw5 {
    public Cw5(Picture picture) {
        cw5(picture);
    }

    private void cw5(final Picture picture) {
        final ConversionsCw5 conversionsCw5 = new ConversionsCw5();
        JMenuBar menuBar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu menuCw5 = new JMenu("CW5");
        menuBar.add(menuCw5);

        JMenuItem addToPicture = new JMenuItem("Add to picture");
        addToPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddArgDialog addArgDialog = new AddArgDialog(picture, false, "Pass add arg: ");
            }
        });

        JMenuItem addTwoPictures = new JMenuItem("Add two pictures in scale");
        addTwoPictures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTwoPicturesDialog addTwoPicturesDialog = new AddTwoPicturesDialog(picture, false, "AddPictures pass arg ", "+");
                addTwoPicturesDialog.repaint();

            }
        });

        JMenuItem addTwoPicturesMinMax = new JMenuItem("Add two pictures minmax");
        addTwoPicturesMinMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/eagle.jpg");
                File file2 = new File("static/lake.jpg");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.addTwoPicturesWithMinMax((BufferedImage) image1, (BufferedImage) image2, "+"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem addTwoPictureInCycleEffect = new JMenuItem("Add two pictures with cycle effect");
        addTwoPictureInCycleEffect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/eagle.jpg");
                File file2 = new File("static/lake.jpg");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.addTwoPicturesWithCycleEffect((BufferedImage) image1, (BufferedImage) image2, "+"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem substractingTwoImages = new JMenuItem("Substracting two images");
        substractingTwoImages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTwoPicturesDialog addTwoPicturesDialog = new AddTwoPicturesDialog(picture, false, "Substracting images ", "-");
                addTwoPicturesDialog.repaint();
            }
        });

        JMenuItem substractingTwoImagesMinMax = new JMenuItem("Substracting two images minmax");
        substractingTwoImagesMinMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/eagle.jpg");
                File file2 = new File("static/lake.jpg");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.addTwoPicturesWithMinMax((BufferedImage) image1, (BufferedImage) image2, "-"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem substractingTwoImagesWithCycleEffect = new JMenuItem("Substracting two images with cycle effect");
        substractingTwoImagesWithCycleEffect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/eagle.jpg");
                File file2 = new File("static/lake.jpg");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.addTwoPicturesWithCycleEffect((BufferedImage) image1, (BufferedImage) image2, "-"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem addTwoWithSpecifiedMethod = new JMenuItem("Add two pictures with specified method");
        addTwoWithSpecifiedMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/texture.jpg");
                File file2 = new File("static/kola.jpg");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.addTwoPicturesWithSpecyficMethod((BufferedImage) image1, (BufferedImage) image2));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem findDifferences = new JMenuItem("Find differences in kings");
        findDifferences.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/krol1.png");
                File file2 = new File("static/krol2.png");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.findDifferences((BufferedImage) image1, (BufferedImage) image2));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        JMenuItem findDifferencesInBlackWhite = new JMenuItem("Find differences in maids");
        findDifferencesInBlackWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/s1.png");
                File file2 = new File("static/s2.png");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.findDifferences((BufferedImage) image1, (BufferedImage) image2));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem multiplyTwoImages = new JMenuItem("Multiply two images");
        multiplyTwoImages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/lake.jpg");
                File file2 = new File("static/eagle.jpg");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.multiplyImages((BufferedImage) image1, (BufferedImage) image2, "*"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem multiplyImagesWithScale = new JMenuItem("Multiply two images with scale");
        multiplyImagesWithScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/ok.jpg");
                File file2 = new File("static/LENA_512.jpg");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.multiplyImagesWithScale((BufferedImage) image1, (BufferedImage) image2, "*"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem division = new JMenuItem("Division of two images");
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/eagle.jpg");
                File file2 = new File("static/lake.jpg");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.multiplyImages((BufferedImage) image1, (BufferedImage) image2, "/"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem putTextureInWhitePlace = new JMenuItem("Put texture in White");
        putTextureInWhitePlace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file1 = new File("static/texture.jpg");
                File file2 = new File("static/kola.jpg");

                try {
                    Image image1 = ImageIO.read(file1);
                    Image image2 = ImageIO.read(file2);
                    PictureCustoms.showImageInNewWindow(conversionsCw5.applyTextureToWhite((BufferedImage) image1, (BufferedImage) image2));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        menuCw5.add(addToPicture);
        menuCw5.add(addTwoPictures);
        menuCw5.add(addTwoPicturesMinMax);
        menuCw5.add(addTwoPictureInCycleEffect);
        menuCw5.add(addTwoWithSpecifiedMethod);
        menuCw5.add(substractingTwoImages);
        menuCw5.add(substractingTwoImagesMinMax);
        menuCw5.add(substractingTwoImagesWithCycleEffect);
        menuCw5.add(findDifferences);
        menuCw5.add(findDifferencesInBlackWhite);
        menuCw5.add(multiplyTwoImages);
        menuCw5.add(multiplyImagesWithScale);
        menuCw5.add(division);
        menuCw5.add(putTextureInWhitePlace);

    }
}
