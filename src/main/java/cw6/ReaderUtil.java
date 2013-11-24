package cw6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * rafik991@gmail.com
 * 11/24/13
 */
public class ReaderUtil {

    public int[][] getFileMatrix(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        int result[][];
        int k = 0;
        if (line != null) {
            String[] numbers = line.split("\\t");
            result = new int[numbers.length][numbers.length];
        } else {
            System.out.println("Wrong file!");
            return null;
        }
        while (line != null) {
            String numbers[] = line.split("\\t");
            for (int i = 0; i < numbers.length; i++) {
                result[k][i] = Integer.parseInt(numbers[i]);
            }
            k++;
            line = reader.readLine();
        }

        return result;
    }

    public int[][] multiplyMatrix(int[][] src, int width, int height) {
        int result[][] = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[i][j] = src[i % src.length][j % src[i % src.length].length];
            }
        }

        return result;
    }
}
