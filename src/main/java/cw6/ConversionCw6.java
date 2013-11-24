package cw6;

import common.Picture;
import cw4.ConversionsCw4;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * User: rafik991@gmail.com
 * Date: 11/24/13
 */
public class ConversionCw6 {

    private ConversionsCw4 conversionsCw4 = new ConversionsCw4();
    private ReaderUtil readerUtil = new ReaderUtil();

    public BufferedImage erosion(Picture picture, String file) throws IOException {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int matrix[][] = readerUtil.getFileMatrix(file);
        int se[][] = readerUtil.multiplyMatrix(matrix, filtered.getWidth(), filtered.getHeight());

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

    public BufferedImage circleErosion(Picture picture, int r) {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        if (r * 2 > src.getWidth() || r * 2 > src.getHeight()) {
            throw new RuntimeException("R is too large");
        }
        int[][] matrix = defineStructure(r, r + 10, r + 10);
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
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int matrix[][] = readerUtil.getFileMatrix(file);
        int se[][] = readerUtil.multiplyMatrix(matrix, filtered.getWidth(), filtered.getHeight());

        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                if (se[i][j] == 1) {
                    filtered.setRGB(i, j, 1);
                } else {
                    filtered.setRGB(i, j, src.getRGB(i, j));
                }
            }
        }
        return filtered;
    }

    public BufferedImage erosionForRGB(Picture picture, String file) throws IOException {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int matrix[][] = readerUtil.getFileMatrix(file);
        int se[][] = readerUtil.multiplyMatrix(matrix, filtered.getWidth(), filtered.getHeight());
        int r, g, b;
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

    public BufferedImage dilatationForRGB(Picture picture, String file) throws IOException {
        BufferedImage src = picture.getImage();
        BufferedImage filtered = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int matrix[][] = readerUtil.getFileMatrix(file);
        int se[][] = readerUtil.multiplyMatrix(matrix, filtered.getWidth(), filtered.getHeight());

        for (int i = 0; i < filtered.getWidth(); i++) {
            for (int j = 0; j < filtered.getHeight(); j++) {
                if (se[i][j] == 1) {
                    filtered.setRGB(i, j, 1);
                } else {
                    filtered.setRGB(i, j, src.getRGB(i, j));
                }
            }
        }
        return filtered;
    }

    private int[][] defineStructure(int r, int w, int h) {
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
