package cw4;

import common.ConversionsCommon;
import common.Picture;
import cw3.ConversionsCw3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * User: SG0219139
 * Date: 11/9/13
 */
public class ConversionsCw4 {
    private ConversionsCommon conversionsCommon = new ConversionsCommon();
    private ConversionsCw3 conversionsCw3 = new ConversionsCw3();

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

    BufferedImage otsuBinaryConversion(Picture picture) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, y;

        Map<String, Map<Integer, Integer>> histo = conversionsCw3.createHistogram(picture);
        int hR[] = fullRangeHisto(histo.get("R"));
        int hG[] = fullRangeHisto(histo.get("G"));
        int hB[] = fullRangeHisto(histo.get("B"));
        int hY[] = fullRangeHisto(histo.get("Y"));
        int otsu = otsu(hY, src.getHeight() * src.getWidth());
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                y = (int) (ConversionsCommon.KR * red + (1 - ConversionsCommon.KR - ConversionsCommon.KB) * green + ConversionsCommon.KB * blue);
                if (y < otsu) {
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

    private int[] fullRangeHisto(Map<Integer, Integer> histo) {
        int[] histogramFull = new int[ConversionsCommon.RBG_MAX];
        for (int i = 0; i < ConversionsCommon.RBG_MAX; i++) {
            if (histo.get(i) != null) {
                histogramFull[i] = histo.get(i);
            } else {
                histogramFull[i] = 0;
            }
        }
        return histogramFull;
    }

    private int otsu(int[] histData, int total) {
        float sum = 0;
        for (int t = 0; t < ConversionsCommon.RBG_MAX; t++) sum += t * histData[t];

        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        float threshold = 0;

        for (int t = 0; t < ConversionsCommon.RBG_MAX; t++) {
            wB += histData[t];               // Weight Background
            if (wB == 0) continue;

            wF = total - wB;                 // Weight Foreground
            if (wF == 0) break;

            sumB += (float) (t * histData[t]);

            float mB = sumB / wB;            // Mean Background
            float mF = (sum - sumB) / wF;    // Mean Foreground

            // Calculate Between Class Variance
            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            // Check if new maximum found
            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = t;
            }
        }
        return (int) threshold;
    }

    private int getBrensenThreshold(int[][] y, int x0, int y0, int w, int h) {
        int max = 0;
        int min = ConversionsCommon.RBG_MAX;
        for (int i = x0; (i < w && i < y.length); i++) {
            for (int j = y0; (j < h && j < y[i].length); j++) {
                max = Math.max(max, y[i][j]);
                min = Math.min(min, y[i][j]);
            }
        }
        double localContrast = max - min;
        double midGray = (max + min) / 2;
        double output;

        if (midGray >= ConversionsCommon.RBG_MAX / 2)
            output = midGray;
        else
            output = localContrast;


        return (int) output;
    }

    public BufferedImage brensenBinaryConversion(Picture picture, int arg) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, y, bransen;
        int yTab[][] = new int[src.getWidth()][src.getHeight()];
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                y = (int) (ConversionsCommon.KR * red + (1 - ConversionsCommon.KR - ConversionsCommon.KB) * green + ConversionsCommon.KB * blue);
                yTab[i][j] = y;
            }
        }
        int x0, y0;
        x0 = y0 = 0;
        bransen = getBrensenThreshold(yTab, x0, y0, x0 + arg, y0 + arg);
        for (int i = 0; i < src.getWidth(); i++) {
            if (i % arg == 0 && i + arg < src.getWidth())
                x0 += arg;
            for (int j = 0; j < src.getHeight(); j++) {
                if (j % arg == 0 && j + arg < src.getHeight()) {
                    y0 += arg;
                    bransen = getBrensenThreshold(yTab, x0, y0, x0 + arg, y0 + arg);
                }
                if (yTab[i][j] < bransen) {
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

    public BufferedImage brensenAndOtsuBinaryConversion(Picture picture, int arg, int dim) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue, y, bransen;
        int yTab[][] = new int[src.getWidth()][src.getHeight()];
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                y = (int) (ConversionsCommon.KR * red + (1 - ConversionsCommon.KR - ConversionsCommon.KB) * green + ConversionsCommon.KB * blue);
                yTab[i][j] = y;
            }
        }
        int x0, y0;
        x0 = y0 = 0;
        Map<String, Map<Integer, Integer>> histogram = conversionsCw3.createHistogram(picture);
        int otsu = otsu(fullRangeHisto(histogram.get("Y")), src.getHeight() * src.getWidth());
        bransen = getBrensenThreshold(yTab, x0, y0, x0 + arg, y0 + arg);
        for (int i = 0; i < src.getWidth(); i++) {
            if (i % arg == 0 && i + arg < src.getWidth())
                x0 += arg;
            for (int j = 0; j < src.getHeight(); j++) {
                if (j % arg == 0 && j + arg < src.getHeight()) {
                    y0 += arg;
                    bransen = getBrensenThreshold(yTab, x0, y0, x0 + arg, y0 + arg);
                }
                if (bransen < otsu - dim || bransen > otsu - dim) {
                    if (yTab[i][j] < otsu) {
                        y = 0;
                    } else {
                        y = ConversionsCommon.RBG_MAX;
                    }
                } else {
                    if (yTab[i][j] < bransen) {
                        y = 0;
                    } else {
                        y = ConversionsCommon.RBG_MAX;
                    }
                }

                int newPixel = conversionsCommon.colorToRGB(y, y, y);
                filtered.setRGB(i, j, newPixel);


            }
        }
        return filtered;
    }

    public BufferedImage greenPeppersEffect(Picture picture) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                int newPixel;
                if (green > red && green > blue) {
                    newPixel = conversionsCommon.colorToRGB(red, green, blue);
                    filtered.setRGB(i, j, newPixel);

                } else {
                    red = green = blue = 0;
                    newPixel = conversionsCommon.colorToRGB(red, green, blue);
                    filtered.setRGB(i, j, newPixel);
                }

            }
        }
        return filtered;
    }


}
