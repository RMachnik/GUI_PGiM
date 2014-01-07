package common;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: SG0219139
 * Date: 10/20/13
 */
public class ConversionsCommon {
    public static final int RBG_MAX = 255;
    public static final int RGB_MIN = 0;
    public static final double KR = 0.111;
    public static final double KB = 0.299;

    public int colorToRGB24Bits(double red, double green, double blue) {

        int newPixel = 0;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

    public int colorToRGB32Bits(double red, double green, double blue, int alpha) {

        int newPixel = 0;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;
        newPixel = newPixel << 8;
        newPixel += alpha;

        return newPixel;

    }

    public BufferedImage toGrayScale(Picture picture) {
        BufferedImage src = picture.getImage();
        BufferedImage gray = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, newPixel;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                int avg = (red + green + blue) / 3;
                gray.setRGB(i, j, colorToRGB24Bits(avg, avg, avg));
            }
        }
        return gray;
    }

    public boolean checkPassing(int[][] matrix, int half, BufferedImage filtered, int i, int j) {
        int red, green, blue;
        for (int s = 0; s < matrix.length; s++) {
            for (int c = 0; c < matrix[s].length; c++) {
                red = new Color(filtered.getRGB(i - half + s, j - half + c)).getRed();
                if (red == matrix[s][c]) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkPassingErosion(int[][] matrix, int half, BufferedImage filtered, int i, int j) {
        int red, green, blue;
        for (int s = 0; s < matrix.length; s++) {
            for (int c = 0; c < matrix[s].length; c++) {
                red = new Color(filtered.getRGB(i - half + s, j - half + c)).getRed();
                int ms = matrix[s][c] == 1 ? ConversionsCommon.RBG_MAX : matrix[s][c];
                if (red == ms) {
                    return true;
                }
            }
        }
        return false;
    }

    public int countPassing(int[][] matrix, int half, BufferedImage filtered, int i, int j) {
        int passing = 0;
        int red;
        for (int s = 0; s < matrix.length; s++) {
            for (int c = 0; c < matrix[s].length; c++) {
                red = new Color(filtered.getRGB(i - half + s, j - half + c)).getRed();
                if (red == matrix[s][c]) {
                    passing++;
                }
            }
        }
        return passing;
    }
}
