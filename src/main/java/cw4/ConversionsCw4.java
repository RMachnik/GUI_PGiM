package cw4;

import common.ConversionsCommon;
import common.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: SG0219139
 * Date: 11/9/13
 */
public class ConversionsCw4 {
    ConversionsCommon conversionsCommon = new ConversionsCommon();

    public BufferedImage simpleBinaryConversion(Picture picture, int a) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, y;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                y = (int) (ConversionsCommon.KR * red + (1 - ConversionsCommon.KR - ConversionsCommon.KB) * green + ConversionsCommon.KB * blue);
                if (y < a) {
                    y = 0;
                } else {
                    y = ConversionsCommon.RBG_MAX;
                }
                int newPixel = conversionsCommon.colorToRGB(y, y, y);
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;

    }

    BufferedImage simpleRangeBinaryConversion(Picture picture, int a, int b) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, y;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                y = (int) (ConversionsCommon.KR * red + (1 - ConversionsCommon.KR - ConversionsCommon.KB) * green + ConversionsCommon.KB * blue);
                if (y > a && y < b) {
                    y = 0;
                } else {
                    y = ConversionsCommon.RBG_MAX;
                }
                int newPixel = conversionsCommon.colorToRGB(y, y, y);
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }


}
