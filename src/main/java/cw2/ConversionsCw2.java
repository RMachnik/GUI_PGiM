package cw2;

import com.google.common.base.Function;
import common.ConversionsCommon;
import common.Picture;
import cw1.ConversionsCw1;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: SG0219139
 * Date: 10/20/13
 */
public class ConversionsCw2 {
    private final int RGB_MAX = 255;
    private ConversionsCommon conversionsCommon = new ConversionsCommon();
    private ConversionsCw1 conversionsCw1 = new ConversionsCw1();

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
        BufferedImage src = conversionsCw1.toGrayScale(picture);
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, newPixel;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
            /*    int gray = src.getRGB(i,j) /3;
                red = blue = green = gray;*/
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                red = red + 2 * W;
                green = green + W;
                blue -= 20;

                // normalize if out of bounds
                if (blue < 0) blue = 0;
                if (blue > 255) blue = 255;
                newPixel = conversionsCommon.colorToRGB(red, green, blue);
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }

    public BufferedImage transformUsingFunction(Picture picture, Function<Pair<Integer, Integer>, Integer> function, int argument) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, newPixel;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                red = function.apply(new Pair(argument, red));
                blue = function.apply(new Pair(argument, blue));
                green = function.apply(new Pair(argument, green));
                newPixel = conversionsCommon.colorToRGB(red, green, blue);
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }

    public BufferedImage transformUsingAngle(Picture picture, double angle) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth() * 2, src.getHeight() * 2, src.getType());
        int x, y;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                x = Math.abs((int) (i * Math.cos(angle) - j * Math.sin(angle))) + 100;//indexoutof bound
                y = Math.abs((int) (i * Math.sin(angle) + j * Math.cos(angle))) + 100;
                filtered.setRGB(x, y, src.getRGB(i, j));
            }
        }
        return filtered;
    }

    public BufferedImage movePicture(Picture picture, int move) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int x, y;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {

                filtered.setRGB((i + move) % src.getWidth(), j, src.getRGB(i, j));
            }
        }
        return filtered;
    }

    public BufferedImage resiziePhoto(Picture picture, int scale) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        BufferedImage small = new BufferedImage(src.getWidth() / scale + 100, src.getHeight() / scale + 100, src.getType());
        int x_dim = src.getWidth() / scale;
        int y_dim = src.getHeight() / scale;
        int s_x, s_y;
        s_x = s_y = 0;
        int tmp[] = new int[scale];
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                tmp[j % scale] = src.getRGB(i, j);
                if (j % scale == 0) {
                    small.setRGB(s_x++, s_y++, getAvg(tmp));
                    s_x = s_x % x_dim;
                    s_y = s_y % y_dim;
                    clearTab(tmp);
                }

            }
        }
        return small;
    }

    private void clearTab(int tab[]) {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = 0;
        }
    }

    private int getAvg(int tab[]) {
        int sum = 0;
        for (int i = 0; i < tab.length; i++) {
            sum += tab[i];
        }
        return sum / tab.length;
    }

    public static class Increment implements Function<Pair<Integer, Integer>, Integer> {

        public Increment() {
        }

        @Override
        public Integer apply(Pair<Integer, Integer> pair) {
            return pair.getValue() + pair.getKey();
        }
    }

    public static class Decrement implements Function<Pair<Integer, Integer>, Integer> {

        @Override
        public Integer apply(Pair<Integer, Integer> pair) {
            return pair.getValue() - pair.getKey();
        }
    }

    public static class Multiply implements Function<Pair<Integer, Integer>, Integer> {

        @Override
        public Integer apply(Pair<Integer, Integer> pair) {
            return pair.getKey() * pair.getValue();
        }
    }

    public static class Division implements Function<Pair<Integer, Integer>, Integer> {

        @Override
        public Integer apply(Pair<Integer, Integer> pair) {
            return pair.getValue() / pair.getKey();
        }
    }


}
