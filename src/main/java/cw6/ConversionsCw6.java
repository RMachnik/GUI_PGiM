package cw6;

import common.ConversionsCommon;
import common.Picture;
import cw4.ConversionsCw4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * User: rafik991@gmail.com
 * Date: 11/24/13
 */
public class ConversionsCw6 {
    public static final String R = "R";
    public static final String G = "G";
    public static final String B = "B";
    private ConversionsCommon conversionsCommon = new ConversionsCommon();
    private ConversionsCw4 conversionsCw4 = new ConversionsCw4();
    private ReaderUtil readerUtil = new ReaderUtil();

    public BufferedImage erosion(Picture picture, String file) throws IOException {
        BufferedImage src = conversionsCw4.otsuBinaryConversion(picture);
        int matrix[][] = readerUtil.getFileMatrix(file);
        int half = matrix.length / 2;
        int matrixSize = matrix.length * matrix[0].length;
        BufferedImage filtered = new BufferedImage(src.getWidth() + 2 * matrix.length, src.getHeight() + 2 * matrix.length,
                src.getType());

        fillFilteredImage(src, matrix, filtered);

        for (int i = 2 * matrix.length; i < filtered.getWidth() - 2 * matrix.length; i++) {
            for (int j = 2 * matrix.length; j < filtered.getHeight() - 2 * matrix.length; j++) {
                if (checkPassing(matrix, half, filtered, i, j)) {
                    filtered.setRGB(i, j, 0);
                } else {
                    //filtered.setRGB(i, j, src.getRGB(i, j));
                }
            }
        }
        return filtered;
    }

    private boolean checkPassing(int[][] matrix, int half, BufferedImage filtered, int i, int j) {
        int red, green, blue;
        for (int s = 0; s < matrix.length; s++) {
            for (int c = 0; c < matrix[s].length; c++) {
                red = new Color(filtered.getRGB(i - half + s, j - half + c)).getRed();
                green = new Color(filtered.getRGB(i - half + s, j - half + c)).getGreen();
                blue = new Color(filtered.getRGB(i - half + s, j - half + c)).getBlue();
                if (red == matrix[s][c]) {
                    return true;
                }
            }
        }
        return false;
    }

    private void fillFilteredImage(BufferedImage src, int[][] matrix, BufferedImage filtered) {
        for (int k = 0; k < filtered.getWidth(); k++) {
            for (int w = 0; w < filtered.getHeight(); w++) {
                if (k >= 2 * matrix.length && w >= 2 * matrix.length) {
                    filtered.setRGB(k, w, src.getRGB(k - matrix.length * 2, w - matrix.length * 2));
                } else {
                    filtered.setRGB(k, w, -1);
                }
            }
        }
    }

    public BufferedImage circleErosion(Picture picture, int r) {
        BufferedImage src = conversionsCw4.otsuBinaryConversion(picture);
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        if (r * 2 > src.getWidth() || r * 2 > src.getHeight()) {
            throw new RuntimeException("R is too large");
        }
        int[][] matrix = defineCircleMatrix(r, r + 10, r + 10);
        int[][] se = readerUtil.multiplyMatrix(matrix, src.getWidth(), src.getHeight());
        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                if (se[i][j] == 0) {
                    filtered.setRGB(i, j, 0);
                } else {
                    filtered.setRGB(i, j, src.getRGB(i, j));
                }
            }
        }
        return filtered;

    }

    public BufferedImage dilatation(Picture picture, String file) throws IOException {
        BufferedImage src = conversionsCw4.otsuBinaryConversion(picture);
        int matrix[][] = readerUtil.getFileMatrix(file);
        int half = matrix.length / 2;
        int matrixSize = matrix.length * matrix[0].length;
        BufferedImage filtered = new BufferedImage(src.getWidth() + 2 * matrix.length, src.getHeight() + 2 * matrix.length,
                src.getType());
        fillFilteredImage(src, matrix, filtered);
        for (int i = 2 * matrix.length; i < filtered.getWidth() - 2 * matrix.length; i++) {
            for (int j = 2 * matrix.length; j < filtered.getHeight() - 2 * matrix.length; j++) {

                if (checkPassing(matrix, half, filtered, i, j)) {
                    filtered.setRGB(i, j, 1);
                } else {
                    // filtered.setRGB(i, j, src.getRGB(i, j));
                }
            }
        }

        return filtered;
    }

    public BufferedImage erosionForRGB(Picture picture, String file, String rgb) throws IOException {
        BufferedImage src = conversionsCw4.otsuBinaryConversion(picture);
        int matrix[][] = readerUtil.getFileMatrix(file);
        int half = matrix.length / 2;
        int matrixSize = matrix.length * matrix[0].length;
        BufferedImage filtered = new BufferedImage(src.getWidth() + 2 * matrix.length, src.getHeight() + 2 * matrix.length,
                src.getType());
        fillFilteredImage(src, matrix, filtered);
        int red, green, blue;
        for (int i = 2 * matrix.length; i < filtered.getWidth() - 2 * matrix.length; i++) {
            for (int j = 2 * matrix.length; j < filtered.getHeight() - 2 * matrix.length; j++) {

                red = new Color(filtered.getRGB(i, j)).getRed();
                green = new Color(filtered.getRGB(i, j)).getGreen();
                blue = new Color(filtered.getRGB(i, j)).getBlue();
                if (checkPassing(matrix, half, filtered, i, j)) {
                    switch (rgb) {
                        case R:
                            red = 0;
                            break;
                        case G:
                            green = 0;
                            break;
                        case B:
                            blue = 0;
                            break;
                    }

                    filtered.setRGB(i, j, conversionsCommon.colorToRGB(red, green, blue));
                } else {
                    filtered.setRGB(i, j, src.getRGB(i, j));
                }
            }
        }
        return filtered;
    }

    public BufferedImage dilatationForRGB(Picture picture, String file, String rgb) throws IOException {
        BufferedImage src = conversionsCw4.otsuBinaryConversion(picture);
        int matrix[][] = readerUtil.getFileMatrix(file);
        int half = matrix.length / 2;
        int matrixSize = matrix.length * matrix[0].length;
        BufferedImage filtered = new BufferedImage(src.getWidth() + 2 * matrix.length, src.getHeight() + 2 * matrix.length,
                src.getType());
        fillFilteredImage(src, matrix, filtered);
        int red, green, blue;
        for (int i = 2 * matrix.length; i < filtered.getWidth() - 2 * matrix.length; i++) {
            for (int j = 2 * matrix.length; j < filtered.getHeight() - 2 * matrix.length; j++) {
                red = new Color(filtered.getRGB(i, j)).getRed();
                green = new Color(filtered.getRGB(i, j)).getGreen();
                blue = new Color(filtered.getRGB(i, j)).getBlue();

                if (checkPassing(matrix, half, filtered, i, j)) {
                    switch (rgb) {
                        case R:
                            red = 1;
                            break;
                        case G:
                            green = 1;
                            break;
                        case B:
                            blue = 1;
                            break;
                    }
                    filtered.setRGB(i, j, conversionsCommon.colorToRGB(red, green, blue));
                } else {
                    filtered.setRGB(i, j, src.getRGB(i, j));
                }
            }
        }
        return filtered;
    }

    private int[][] defineCircleMatrix(int r, int w, int h) {
        int[][] matrix = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (i * i + j * j < r * r) {
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }
}
