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
    private final int B = 8;
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
        BufferedImage src = conversionsCommon.toGrayScale(picture);
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

    public BufferedImage resiziePhoto(Picture picture, int size) {

        BufferedImage src = picture.getImage();
        if (src.getWidth() % size != 0 || src.getHeight() % size != 0) {
            throw new RuntimeException("Width or height mod size should be eq 0!");
        }
        int n_x = src.getWidth() / size;
        int n_y = src.getHeight() / size;
        int scale = (src.getHeight() * src.getWidth()) / ((src.getHeight() / size) * (src.getWidth() / size));
        int h = (int) Math.sqrt(scale);
        if (src.getHeight() % h != 0 || src.getWidth() % h != 0) {
            throw new RuntimeException("Width or height mod size should be eq 0!");
        }
        int x, y;
        x = y = 0;
        int r[] = new int[scale];
        int g[] = new int[scale];
        int b[] = new int[scale];

        BufferedImage small = new BufferedImage(n_x, n_y, src.getType());

        int red, green, blue;
        for (int i = 0; i < small.getWidth(); i++) {

            for (int j = 0; j < small.getHeight(); j++) {
                int k = 0;
                for (int w = x; w < x + h; w++) {
                    for (int s = y; s < y + h; s++) {
                        red = new Color(src.getRGB(w, s)).getRed();
                        green = new Color(src.getRGB(w, s)).getGreen();
                        blue = new Color(src.getRGB(w, s)).getBlue();
                        r[k] = red;
                        g[k] = green;
                        b[k++] = blue;
                    }
                }
                y += h;
                small.setRGB(i, j, conversionsCommon.colorToRGB(getAvg(r), getAvg(g), getAvg(b)));
            }
            x += h;
            y = 0;
        }
        return multipleImage(small, size);
    }

    public BufferedImage diffGrayImage(Picture picture, int arg) {
        BufferedImage src = conversionsCommon.toGrayScale(picture);
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int delta = (int) ((int) Math.pow(2, B) / Math.pow(2, arg));
        int newPixel;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                newPixel = Math.max((((delta / 2) - src.getRGB(i, j)) - 1) / delta, 0) * delta + (delta / 2 - 1);
                filtered.setRGB(i, j, newPixel);
            }

        }
        return filtered;
    }

    private BufferedImage multipleImage(BufferedImage src, int scale) {
        int w = src.getWidth() * scale;
        int h = src.getHeight() * scale;
        BufferedImage big = new BufferedImage(w, h, src.getType());
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                big.setRGB(i, j, src.getRGB(i % src.getWidth(), j % src.getHeight()));
            }
        }
        return big;

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
