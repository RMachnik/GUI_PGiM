package common;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: SG0219139
 * Date: 10/20/13
 */
public class ConversionsCommon {
    public int colorToRGB(double red, double green, double blue) {

        int newPixel = 0;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }
    public BufferedImage toGrayScale(Picture picture){
        BufferedImage src  = picture.getImage();
        BufferedImage gray = new BufferedImage(src.getWidth(),src.getHeight(),src.getType());
        int red,green,blue,newPixel;
        for(int i=0;i<src.getWidth();i++){
            for(int j=0;j<src.getHeight();j++){
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                int avg = (red+ green+blue)/3;
                gray.setRGB(i,j,colorToRGB(avg,avg,avg));
            }
        }
        return gray;
    }
}
