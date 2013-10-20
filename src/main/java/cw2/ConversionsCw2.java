package cw2;

import common.ConversionsCommon;
import common.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: SG0219139
 * Date: 10/20/13
 */
public class ConversionsCw2 {
    private final int RGB_MAX = 255;
    private ConversionsCommon conversionsCommon = new ConversionsCommon();

    public BufferedImage negative(Picture picture) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        for (int i = 0; i < src.getWidth(); i++)
            for (int j = 0; j < src.getHeight(); j++) {
                {
                    filtered.setRGB(i, j, RGB_MAX - src.getRGB(i, j));
                }
            }
        return filtered;

    }

    public BufferedImage sepia(Picture picture, int W) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, newPixel;
        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                red = new Color(filtered.getRGB(i, j)).getRed();
                green = new Color(filtered.getRGB(i, j)).getGreen();
                blue = new Color(filtered.getRGB(i, j)).getBlue();
                red = red + 2 * W;
                green = green + W;
                newPixel = conversionsCommon.colorToRGB(red, green, blue);
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }

}
