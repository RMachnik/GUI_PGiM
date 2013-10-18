package cw1;

import common.Picture;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by SG0219139 on 10/13/13.
 */
public class Conversions
{
    public static BufferedImage rgb(BufferedImage original, String fileName, int color)
    {

        int red, green, blue;
        int newPixel;
        BufferedImage rgb = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        int[] pixel = new int[3];
        int newPixelTab[] = {0, 0, 0};

        for (int i = 0; i < original.getWidth(); i++)
        {
            for (int j = 0; j < original.getHeight(); j++)
            {

                red = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue = new Color(original.getRGB(i, j)).getBlue();

                pixel[0] = red;
                pixel[1] = green;
                pixel[2] = blue;


                int newval = pixel[color];

                newPixelTab[color] = newval;
                newPixel = colorToRGB(newPixelTab[0], newPixelTab[1], newPixelTab[2]);

                rgb.setRGB(i, j, newPixel);
            }
        }

        pictureToTxt(rgb, fileName);
        return rgb;


    }

    // Convert R, G, B, Alpha to standard 8 bit
    private static int colorToRGB(double red, double green, double blue)
    {

        int newPixel = 0;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

    public static BufferedImage toGrayScale(Picture picture)
    {

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage filtered = op.filter(picture.getImage(), null);
        pictureToTxt(filtered, "gray_scale.txt");
        return filtered;
    }

    private static void writeToFile(BufferedWriter writer, int[] data) throws IOException
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(data[0]).append(" ").append(data[1]).append(" ").append
                (data[2])
                .append
                        (System
                                .lineSeparator());
        writer.write(stringBuffer.toString());
    }

    public static void pictureToTxt(BufferedImage filtered, String fileName)
    {
        int red, green, blue;

        BufferedWriter writer = null;

        File file = new File(fileName);
        try
        {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < filtered.getWidth(); i++)
            {
                for (int j = 0; j < filtered.getHeight(); j++)
                {
                    red = new Color(filtered.getRGB(i, j)).getRed();
                    green = new Color(filtered.getRGB(i, j)).getGreen();
                    blue = new Color(filtered.getRGB(i, j)).getBlue();
                    writeToFile(writer, new int[]{red, green, blue});
                    writer.flush();
                }
            }
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    public static BufferedImage createCheckerBoard(int sizeOfBoard, int sizeOfBlock)
    {
        BufferedImage rgb = new BufferedImage(sizeOfBoard, sizeOfBoard, BufferedImage.TYPE_INT_RGB);
        if (sizeOfBoard % sizeOfBlock != 0)
        {
            throw new RuntimeException("Missed size of board and checker board!");
        }
        // 0 -if black, 1 if white
        int col = 0;
        boolean translation = false;
        for (int i = 0; i < sizeOfBoard; i++)
        {
            if (i % sizeOfBlock == 0)
            {
                if (translation)
                {
                    translation = false;
                }
                else
                {
                    translation = true;
                }
            }
            for (int j = 0; j < sizeOfBoard; j++)
            {
                if (j % sizeOfBlock == 0)
                {
                    if (col == 0)
                    {
                        col = 1;
                    }
                    else
                    {
                        col = 0;
                    }
                }
                if (translation)
                {
                    if (col == 0)
                    {
                        rgb.setRGB(i, j, white());
                    }
                    else
                    {
                        rgb.setRGB(i, j, black());
                    }
                }
                else
                {
                    if (col == 0)
                    {
                        rgb.setRGB(i, j, black());
                    }
                    else
                    {
                        rgb.setRGB(i, j, white());
                    }

                }


            }
        }
        return rgb;
    }

    private static int black()
    {
        return colorToRGB(0, 0, 0);
    }

    private static int white()
    {
        return colorToRGB(255, 255, 255);
    }

    public static BufferedImage transformRGBToVHS(BufferedImage original, double kr, double kb)
    {
        BufferedImage vhs = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        double y, cb, cr;
        int red, green, blue;
        for (int i = 0; i < original.getWidth(); i++)
        {
            for (int j = 0; j < original.getHeight(); j++)
            {
                red = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue = new Color(original.getRGB(i, j)).getBlue();
                y = kr * red + (1 - kr - kb) * green + kb * blue;
                cb = (0.5) * (blue - y) / (1 - kb);
                cr = (0.5) * (red - y) / (1 - kr);
                int newPixel = colorToRGB(y, cb, cr);
                vhs.setRGB(i, j, newPixel);
            }
        }
        return vhs;
    }

    public static BufferedImage transformVHSToRgb(BufferedImage original, double kr, double kb)
    {
        BufferedImage rgb = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        double y, cb, cr;
        int red, green, blue;
        for (int i = 0; i < original.getWidth(); i++)
        {
            for (int j = 0; j < original.getHeight(); j++)
            {
                y = new Color(original.getRGB(i, j)).getRed();
                cb = new Color(original.getRGB(i, j)).getGreen();
                cr = new Color(original.getRGB(i, j)).getBlue();
                blue = (int) (2 * cb * (1 - kb) + y);
                red = (int) (2 * cr * (1 - kr) + y);
                green = (int) ((-(cr * red) - kb * blue) / (1 - kr - kb));
                int newPixel = colorToRGB(red, green, blue);
                rgb.setRGB(i, j, newPixel);
            }
        }
        return rgb;
    }
}
