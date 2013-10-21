package cw2;

import com.google.common.base.Function;
import common.ConversionsCommon;
import common.Picture;
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

    public BufferedImage transformUsingFunction(Picture picture, Function<Pair<Integer,Integer>, Integer> function,int argument) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, newPixel;
        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                red = new Color(filtered.getRGB(i, j)).getRed();
                green = new Color(filtered.getRGB(i, j)).getGreen();
                blue = new Color(filtered.getRGB(i, j)).getBlue();
                red = function.apply(new Pair(argument, red));
                blue = function.apply(new Pair(argument, blue));
                green = function.apply(new Pair(argument,green));
                newPixel = conversionsCommon.colorToRGB(red, green, blue);
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }





    public static class Increment implements Function<Pair<Integer,Integer>, Integer> {

        public Increment() {
        }

        @Override
        public Integer apply(Pair<Integer,Integer> pair) {
            return pair.getValue() + pair.getKey();
        }
    }

    public static class Decrement implements Function<Pair<Integer,Integer>,Integer>{

        @Override
        public Integer apply(Pair<Integer,Integer> pair) {
            return pair.getValue() - pair.getKey();
        }
    }

    public static class Multiply implements Function<Pair<Integer,Integer>,Integer>{

        @Override
        public Integer apply(Pair<Integer,Integer> pair) {
            return pair.getKey() * pair.getValue();
        }
    }
    public static class Division implements Function<Pair<Integer,Integer>,Integer>{

        @Override
        public Integer apply(Pair<Integer,Integer> pair) {
            return pair.getValue()/pair.getKey();
        }
    }


}
