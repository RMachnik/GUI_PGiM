package cw8;

import common.CommonArgDialog;
import common.ConversionsCommon;
import common.Picture;
import common.PictureCustoms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

/**
 * rafik991@gmai.com
 * 12/12/13
 */
public class MovingMeanArgDialog extends CommonArgDialog {
    private String argTab[];
    private BufferedImage filtered;
    private Picture pictureSrc;

    public MovingMeanArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
        pictureSrc = picture;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConversionsCw8 conversionsCw8 = new ConversionsCw8();
        ConversionsCommon conversionsCommon = new ConversionsCommon();
        String args = getTextField().getText();
        if (getYesButton() == e.getSource()) {
            if (args.isEmpty()) {
                throw new RuntimeException("Pass correct parameter!");
            }
            argTab = args.split(",");
            switch (argTab[0]) {
                case ("S"):
                    filtered = conversionsCw8.steadyDysfunction(getPicture(), Integer.parseInt(argTab[3]),
                            Double.parseDouble(argTab[4]));
                    break;
                case ("N"):
                    filtered = conversionsCw8.normalDysfunction(getPicture(), Integer.parseInt(argTab[3]), Integer
                            .parseInt
                                    (argTab[4]),
                            Double.parseDouble(argTab[5]));
                    break;
                case ("SP"):
                    filtered = conversionsCw8.saltAndPepperNoise(getPicture(), Double.parseDouble(argTab[3]));
                    break;
                default:
                    throw new RuntimeException("pass correct value!");
            }
            setPicture(new Picture(conversionsCw8.meanFilter(new Picture(filtered), Integer.parseInt(argTab[1]),
                    argTab[2])));
            PictureCustoms.showImageInNewWindow(getPicture().getImage());
            BufferedImage src = pictureSrc.getImage();
            BufferedImage filtered = this.getPicture().getImage();
            BufferedImage result = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
            for (int i = 0; i < pictureSrc.getImage().getWidth(); i++) {
                for (int j = 0; j < pictureSrc.getImage().getHeight(); j++) {

                    int red, green, blue, newPixel;


                    red = new Color(src.getRGB(i, j)).getRed();
                    green = new Color(src.getRGB(i, j)).getGreen();
                    blue = new Color(src.getRGB(i, j)).getBlue();
                    red -= new Color(filtered.getRGB(i, j)).getRed();
                    green -= new Color(filtered.getRGB(i, j)).getGreen();
                    blue -= new Color(filtered.getRGB(i, j)).getBlue();
                    newPixel = conversionsCommon.colorToRGB24Bits(red, green, blue);
                    result.setRGB(i, j, newPixel);

                }
            }
            PictureCustoms.showImageInNewWindow(result);

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }

    public BufferedImage getFiltered() {
        return filtered;
    }

    public String[] getArgTab() {
        return argTab;
    }
}
