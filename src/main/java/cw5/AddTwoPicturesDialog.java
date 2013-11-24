package cw5;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * User: rafik991@gmail.com
 * Date: 11/24/13
 */
public class AddTwoPicturesDialog extends CommonArgDialog {
    private String method;
    public AddTwoPicturesDialog(Picture picture, boolean modal, String myMessage,String method) {
        super(picture, modal, myMessage);
        method = method;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            String arg = getTextField().getText();

            ConversionsCw5 conversionsCw5 = new ConversionsCw5();
            setVisible(false);
            File file1 = new File("static/eagle.jpg");
            File file2 = new File("static/lake.jpg");

            try {
                Image image1 = ImageIO.read(file1);
                Image image2 = ImageIO.read(file2);
                PictureCustoms.showImageInNewWindow(conversionsCw5.addTwoPicturesInTheSameScale((BufferedImage) image1, (BufferedImage) image2, Double.parseDouble(arg),method));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
