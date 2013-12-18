package cw7.util;

import common.ConversionsCommon;
import common.Picture;
import common.ReaderUtil;
import cw4.util.ConversionsCw4;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by SG0219139 on 12/5/13.
 */
public class ConversionsCw7 {
    private ConversionsCw4 conversionsCw4 = new ConversionsCw4();
    private ReaderUtil readerUtil = new ReaderUtil();
    private ConversionsCommon conversionsCommon = new ConversionsCommon();

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
                if (conversionsCommon.countPassing(matrix, half, filtered, i, j) == matrixSize) {
                    src.setRGB(i - 2 * matrix.length, j - 2 * matrix.length, conversionsCommon.colorToRGB24Bits(1, 1, 1
                    ));
                } else {
                  /*  src.setRGB(i - 2 * matrix.length, j - 2 * matrix.length, conversionsCommon.colorToRGB24Bits(1,
                            1, 1
                    ));*/
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
                if (conversionsCommon.countPassing(matrix, half, filtered, i, j) == matrixSize) {
                    src.setRGB(i - 2 * matrix.length, j - 2 * matrix.length, conversionsCommon.colorToRGB24Bits(1, 1, 1
                    ));
                } else {
                   /*src.setRGB(i - 2 * matrix.length, j - 2 * matrix.length, conversionsCommon.colorToRGB24Bits(1,
                            1, 1
                    ));*/
                }
            }
        }
        return src;
    }

    public BufferedImage iterativeMethod(Picture picture, String file1, String file2,
                                         int iterationSize) throws IOException {
        BufferedImage first = conversionsCw4.otsuBinaryConversion(picture);
        int matrix1[][] = readerUtil.getFileMatrix(file1);
        int matrix2[][] = readerUtil.getFileMatrix(file2);
        for (int k = 0; k < iterationSize; k++) {
            for (int i = 0; i < 4; i++) {
                matrix1 = rotateClockwise(matrix1);
                first = erosion(first, matrix1);
            }
            for (int i = 0; i < 4; i++) {
                matrix2 = rotateClockwise(matrix2);
                first = erosion(first, matrix2);
            }
        }

        return first;
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
            for (int j = 0; j < rotatedMatrix[i].length; j++) {
                rotatedMatrix[j][i] = matrix[ro][co++];

            }
            co = 0;
            ro++;

        }
        return rotatedMatrix;
    }
}
