package cw7;

import common.Picture;
import common.ReaderUtil;
import cw4.ConversionsCw4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by SG0219139 on 12/5/13.
 */
public class ConversionsCw7 {
    ConversionsCw4 conversionsCw4 = new ConversionsCw4();
    ReaderUtil readerUtil = new ReaderUtil();

    public BufferedImage erosion(Picture picture, String file) throws IOException {

        BufferedImage src = conversionsCw4.otsuBinaryConversion(picture);
        int matrix[][] = readerUtil.getFileMatrix(file);
        int half = matrix.length / 2;
        int matrixSize = getMatrixSizeWithoutEmptyFields(matrix);
        BufferedImage filtered = new BufferedImage(src.getWidth() + 2 * matrix.length, src.getHeight() + 2 * matrix.length,
                src.getType());

        fillFilteredImage(src, matrix, filtered);

        for (int i = 2 * matrix.length; i < filtered.getWidth() - 2 * matrix.length; i++) {
            for (int j = 2 * matrix.length; j < filtered.getHeight() - 2 * matrix.length; j++) {
                if (countPassing(matrix, half, filtered, i, j) == matrixSize) {
                    src.setRGB(i, j, 0);
                } else {
                    //filtered.setRGB(i, j, src.getRGB(i, j));
                }
            }
        }
        return src;
    }

    public BufferedImage erosion(BufferedImage srcPic, int matrix[][]) throws IOException {
        Picture picture = new Picture(srcPic);
        BufferedImage src = picture.getImage();
        int half = matrix.length / 2;
        int matrixSize = getMatrixSizeWithoutEmptyFields(matrix);
        BufferedImage filtered = new BufferedImage(src.getWidth() + 2 * matrix.length, src.getHeight() + 2 * matrix.length,
                src.getType());

        fillFilteredImage(src, matrix, filtered);

        for (int i = 2 * matrix.length; i < filtered.getWidth() - 2 * matrix.length; i++) {
            for (int j = 2 * matrix.length; j < filtered.getHeight() - 2 * matrix.length; j++) {
                if (countPassing(matrix, half, filtered, i, j) == matrixSize) {
                    src.setRGB(i, j, 0);
                } else {
                    //filtered.setRGB(i, j, src.getRGB(i, j));
                }
            }
        }
        return src;
    }

    public BufferedImage iterativeMethod(Picture picture, String file) throws IOException {
        BufferedImage first = erosion(picture, file);
        int matrix[][] = readerUtil.getFileMatrix(file);
        int[][] clock90 = rotateClockwise(matrix);
        BufferedImage second = erosion(first, clock90);
        int[][] clock180 = rotateClockwise(clock90);
        BufferedImage three = erosion(second, clock180);
        int[][] clock270 = rotateClockwise(clock180);
        BufferedImage four = erosion(three, clock270);
        return four;
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

    private int countPassing(int[][] matrix, int half, BufferedImage filtered, int i, int j) {
        int passing = 0;
        for (int s = 0; s < matrix.length; s++) {
            for (int c = 0; c < matrix[s].length; c++) {
                if (filtered.getRGB(i - half + s, j - half + c) == matrix[s][c]) {
                    passing++;
                }
            }
        }
        return passing;
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

    private int getMatrixSizeWithoutEmptyFields(int matrix[][]) {
        int count = 0;
        for (int s = 0; s < matrix.length; s++) {
            for (int c = 0; c < matrix[s].length; c++) {
                if (matrix[s][c] != Integer.MIN_VALUE) {
                    count++;

                }
            }
        }
        return count;
    }

    private int[][] rotateClockwise(int[][] matrix) {
        int[][] rotatedMatrix = new int[matrix.length][matrix[0].length];
        int ro = 0, co = 0;
        for (int i = rotatedMatrix.length - 1; i >= 0; i--) {
            for (int j = 0; j < rotatedMatrix[i].length; j++)
                rotatedMatrix[j][i] = matrix[ro][co++];
            co = 0;
            ro++;
        }
        return rotatedMatrix;
    }
}
