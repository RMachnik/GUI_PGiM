package cw5;

import common.ConversionsCommon;
import common.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: SG0219139
 * Date: 11/16/13
 */
public class ConversionCw5 {
    ConversionsCommon conversionsCommon = new ConversionsCommon();

    public BufferedImage addValueToColours(Picture picture, int a) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                int newPixel = conversionsCommon.colorToRGB(red + a, green + a, blue + a);
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }

    public BufferedImage addTwoPicturesInTheSameScale(BufferedImage image1, BufferedImage image2, String plusMinus) {
        BufferedImage filtered = new BufferedImage(Math.max(image1.getWidth(), image2.getWidth()), Math.max(image1.getHeight(), image2.getHeight()), image1.getType());

        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                int newPixel = 0;
                switch (plusMinus) {
                    case "+":
                        newPixel = (image1.getWidth() * image1.getRGB(i, j) + (1 - image1.getWidth()) * image2.getRGB(i, j));
                        break;
                    case "-":
                        newPixel = (image1.getWidth() * image1.getRGB(i, j) - (1 - image1.getWidth()) * image2.getRGB(i, j));
                        break;
                    default:
                        System.out.println("pass correct value!");
                }

                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }

    public BufferedImage addTwoPicturesWithMinMax(BufferedImage image1, BufferedImage image2, String plusMinus) {
        BufferedImage filtered = new BufferedImage(Math.max(image1.getWidth(), image2.getWidth()), Math.max(image1.getHeight(), image2.getHeight()), image1.getType());

        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                int newPixel = 0;

                switch (plusMinus) {
                    case "+":
                        newPixel = Math.min(ConversionsCommon.RBG_MAX, image1.getRGB(i, j) + image2.getRGB(i, j));
                        break;
                    case "-":
                        newPixel = Math.min(ConversionsCommon.RBG_MAX, image1.getRGB(i, j) - image2.getRGB(i, j));
                        break;
                    default:
                        System.out.println("pass correct value!");
                }
                filtered.setRGB(i, j, newPixel);

            }
        }
        return filtered;
    }

    public BufferedImage addTwoPicturesWithCycleEffect(BufferedImage image1, BufferedImage image2, String plusMinus) {
        BufferedImage filtered = new BufferedImage(Math.max(image1.getWidth(), image2.getWidth()), Math.max(image1.getHeight(), image2.getHeight()), image1.getType());

        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                int newPixel = 0;
                switch (plusMinus) {
                    case "+":
                        newPixel = (image1.getRGB(i, j) + image2.getRGB(i, j)) % ConversionsCommon.RBG_MAX;
                        break;
                    case "-":
                        newPixel = (image1.getRGB(i, j) - image2.getRGB(i, j)) % ConversionsCommon.RBG_MAX;
                    default:
                        System.out.println("pass correct value!");
                }

                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }

    public BufferedImage addTwoPicturesWithSpecyficMethod(BufferedImage image1, BufferedImage image2) {
        BufferedImage filtered = new BufferedImage(Math.max(image1.getWidth(), image2.getWidth()), Math.max(image1.getHeight(), image2.getHeight()), image1.getType());

        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                int newPixel = (image1.getRGB(i, j) - ((1 - image1.getWidth()) * image2.getRGB(i, j))) % ConversionsCommon.RBG_MAX;
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }

    public int findDifferences(BufferedImage image1, BufferedImage image2) {
        int red1, green1, blue1;
        int red2, green2, blue2;
        int differences = 0;
        for (int i = 0; i < image1.getWidth(); i++) {
            for (int j = 0; j < image2.getHeight(); j++) {
                red1 = new Color(image1.getRGB(i, j)).getRed();
                green1 = new Color(image1.getRGB(i, j)).getGreen();
                blue1 = new Color(image1.getRGB(i, j)).getBlue();

                red2 = new Color(image2.getRGB(i, j)).getRed();
                green2 = new Color(image2.getRGB(i, j)).getGreen();
                blue2 = new Color(image2.getRGB(i, j)).getBlue();
                if (Math.sqrt((red1 - red2) ^ 2 + (green1 - green2) ^ 2 + (blue1 - blue2) ^ 2) > 0) {
                    differences++;
                }

            }
        }
        return differences;
    }

    public BufferedImage multiplyImages(BufferedImage image1, BufferedImage image2, String multiDiv) {
        BufferedImage filtered = new BufferedImage(Math.max(image1.getWidth(), image2.getWidth()), Math.max(image1.getHeight(), image2.getHeight()), image1.getType());

        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                int newPixel = 0;
                switch (multiDiv) {
                    case "*":
                        newPixel = (image1.getRGB(i, j) * image2.getRGB(i, j));
                        break;
                    case "/":
                        newPixel = (image1.getRGB(i, j) / image2.getRGB(i, j));
                        break;
                    default:
                        System.out.println("pass correct arg");
                }
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }

    public BufferedImage multiplyImagesWithScale(BufferedImage image1, BufferedImage image2, String multiDiv) {
        BufferedImage filtered = new BufferedImage(Math.max(image1.getWidth(), image2.getWidth()), Math.max(image1.getHeight(), image2.getHeight()), image1.getType());

        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                int newPixel = 0;
                switch (multiDiv) {
                    case "*":
                        newPixel = (image1.getRGB(i, j) * image2.getRGB(i, j) / ConversionsCommon.RBG_MAX);
                        break;
                    case "/":
                        newPixel = (image1.getRGB(i, j) / image2.getRGB(i, j) / ConversionsCommon.RBG_MAX);
                        break;
                    default:
                        System.out.println("pass correct arg");
                }
                filtered.setRGB(i, j, newPixel);
            }
        }
        return filtered;
    }


}
