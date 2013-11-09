package cw3;

import common.ConversionsCommon;
import common.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User: SG0219139
 * Date: 11/4/13
 */
public class ConversionsCw3 {
    private final String R_MIN = "RMIN";
    private final String R_MAX = "RMAX";
    private final String G_MIN = "GMIN";
    private final String G_MAX = "GMAX";
    private final String B_MIN = "BMIN";
    private final String B_MAX = "BMAX";
    private final String R = "R";
    private final String G = "G";
    private final String B = "B";
    private ConversionsCommon conversionsCommon = new ConversionsCommon();

    public Map<String, Map<Integer, Integer>> createHistogram(Picture p) {
        Map<String, Map<Integer, Integer>> histogram = new HashMap<String, Map<Integer, Integer>>();
        Map<Integer, Integer> histoR = new HashMap<Integer, Integer>();
        Map<Integer, Integer> histoG = new HashMap<Integer, Integer>();
        Map<Integer, Integer> histoB = new HashMap<Integer, Integer>();
        int red, green, blue;
        for (int i = 0; i < p.getImage().getWidth(); i++) {
            for (int j = 0; j < p.getImage().getHeight(); j++) {
                red = new Color(p.getImage().getRGB(i, j)).getRed();
                green = new Color(p.getImage().getRGB(i, j)).getGreen();
                blue = new Color(p.getImage().getRGB(i, j)).getBlue();
                if (histoR.containsKey(red)) {
                    int s = histoR.get(red);
                    histoR.put(red, ++s);
                } else {
                    histoR.put(red, 1);
                }
                if (histoG.containsKey(green)) {
                    int s = histoG.get(green);
                    histoG.put(green, ++s);
                } else {
                    histoG.put(green, 1);
                }
                if (histoB.containsKey(blue)) {
                    int s = histoB.get(blue);
                    histoB.put(blue, ++s);
                } else {
                    histoB.put(blue, 1);
                }
            }
        }
        histogram.put(R, histoR);
        histogram.put(G, histoG);
        histogram.put(B, histoB);
        return histogram;
    }

    private int[] getContrastLutTable(double a) {
        int[] LUT = new int[ConversionsCommon.RBG_MAX];
        if (a > 0)
            a += 0.05;
        else if (a < 0)
            a -= 0.05;

        if (a == 0.00)
            a = 0.00;
        for (int i = 0; i < ConversionsCommon.RBG_MAX; i++) {
            if ((a * (i - ConversionsCommon.RBG_MAX / 2) + ConversionsCommon.RBG_MAX / 2) > ConversionsCommon.RBG_MAX)
                LUT[i] = ConversionsCommon.RBG_MAX;
            else if ((a * (i - ConversionsCommon.RBG_MAX / 2) + ConversionsCommon.RBG_MAX / 2) < 0)
                LUT[i] = 0;
            else
                LUT[i] = ((int) (a * (i - ConversionsCommon.RBG_MAX / 2) + ConversionsCommon.RBG_MAX / 2)) % ConversionsCommon.RBG_MAX;
        }
        return LUT;
    }

    public BufferedImage contrast(Picture p, double a) {
        BufferedImage src = p.getImage();
        BufferedImage result = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue;
        int LUT[] = getContrastLutTable(a);
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                int newPixel = conversionsCommon.colorToRGB(LUT[red % ConversionsCommon.RBG_MAX], LUT[green % ConversionsCommon.RBG_MAX], LUT[blue % ConversionsCommon.RBG_MAX]);
                result.setRGB(i, j, newPixel);

            }
        }
        return result;
    }

    private Map<String, Integer> getMinMax(Picture pic) {

        int rmin, gmin, bmin, rmax, gmax, bmax;
        int rvalue, gvalue, bvalue;
        rmin = gmin = bmin = ConversionsCommon.RBG_MAX;
        rmax = gmax = bmax = 1;
        Map<String, Integer> res = new HashMap<String, Integer>();

        for (int i = 0; i < pic.getImage().getWidth(); i++) {
            for (int j = 0; j < pic.getImage().getHeight(); j++) {
                rvalue = new Color(pic.getImage().getRGB(i, j)).getRed();
                gvalue = new Color(pic.getImage().getRGB(i, j)).getGreen();
                bvalue = new Color(pic.getImage().getRGB(i, j)).getBlue();
                if (rvalue > rmax) rmax = rvalue;
                if (gvalue > gmax) gmax = gvalue;
                if (bvalue > bmax) bmax = bvalue;
                if (rvalue < rmin) rmin = rvalue;
                if (gvalue < gmin) gmin = gvalue;
                if (bvalue < bmin) bmin = bvalue;
            }
        }
        res.put(R_MAX, rmax);
        res.put(R_MIN, rmin);
        res.put(G_MAX, gmax);
        res.put(G_MIN, gmin);
        res.put(B_MAX, bmax);
        res.put(B_MIN, bmin);
        return res;
    }

    private int[] updateElongateLUT(int[] LUT, double a, int b) {
        int i;
        for (i = 0; i < ConversionsCommon.RBG_MAX; i++)
            if ((a * (i + b)) > ConversionsCommon.RBG_MAX)
                LUT[i] = ConversionsCommon.RBG_MAX;
            else if ((a * (i + b)) < 0)
                LUT[i] = 0;
            else
                LUT[i] = (int) (a * (i + b));

        return LUT;
    }

    public BufferedImage elongateHistoRGB(Picture pic) {
        int red, green, blue;
        int LUTr[] = new int[ConversionsCommon.RBG_MAX];
        int LUTg[] = new int[ConversionsCommon.RBG_MAX];
        int LUTb[] = new int[ConversionsCommon.RBG_MAX];
        BufferedImage src = pic.getImage();
        BufferedImage result = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        Map<String, Integer> minMax = getMinMax(pic);
        updateElongateLUT(LUTr, ConversionsCommon.RBG_MAX / (minMax.get(R_MAX) - minMax.get(R_MIN)), -minMax.get(R_MIN));
        updateElongateLUT(LUTg, ConversionsCommon.RBG_MAX / (minMax.get(G_MAX) - minMax.get(G_MIN)), -minMax.get(R_MIN));
        updateElongateLUT(LUTb, ConversionsCommon.RBG_MAX / (minMax.get(B_MAX) - minMax.get(G_MIN)), -minMax.get(R_MIN));

        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                int newPixel = conversionsCommon.colorToRGB(LUTr[red % ConversionsCommon.RBG_MAX], LUTg[green % ConversionsCommon.RBG_MAX], LUTb[blue % ConversionsCommon.RBG_MAX]);
                result.setRGB(i, j, newPixel);
            }
        }
        return result;
    }

    public BufferedImage elongateHistoY(Picture pic) {
        int red, green, blue;
        int LUTgray[] = new int[ConversionsCommon.RBG_MAX];

        BufferedImage src = pic.getImage();
        BufferedImage result = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());

        int grayMin = ConversionsCommon.RBG_MAX;
        int grayMax = 1;
        int y, cb, cr;
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                y = (int) (ConversionsCommon.KR * red + (1 - ConversionsCommon.KR - ConversionsCommon.KB) * green + ConversionsCommon.KB * blue);
                if (grayMin > y) grayMin = y;
                if (grayMax < y) grayMax = y;
            }
        }
        updateElongateLUT(LUTgray, ConversionsCommon.RBG_MAX / (grayMax - grayMin), -grayMin);
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                y = (int) (ConversionsCommon.KR * red + (1 - ConversionsCommon.KR - ConversionsCommon.KB) * green + ConversionsCommon.KB * blue);
                cb = (int) ((0.5) * ((blue - y) / (1 - ConversionsCommon.KB)));
                cr = (int) ((0.5) * ((red - y) / (1 - ConversionsCommon.KR)));
                int newPixel = conversionsCommon.colorToRGB(LUTgray[y % ConversionsCommon.RBG_MAX], LUTgray[y % ConversionsCommon.RBG_MAX], LUTgray[y % ConversionsCommon.RBG_MAX]);
                result.setRGB(i, j, newPixel);
            }
        }
        return result;
    }

    private int[] updateLUTForAlingmentHisto(int D[]) {
        int D0min;
        int i = 0;
        int[] LUT = new int[ConversionsCommon.RBG_MAX];
        while (D[i] == 0 && i < ConversionsCommon.RBG_MAX - 1) i++;
        D0min = D[i];
        for (i = 0; i < ConversionsCommon.RBG_MAX; i++)
            LUT[i] = (((D[i] - D0min) / (1 - D0min)) * (ConversionsCommon.RBG_MAX - 1));
        return LUT;
    }

    public BufferedImage updateAlignmentHisto(Picture pic) {
        BufferedImage src = pic.getImage();
        BufferedImage result = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red, green, blue;
        int Dr[] = new int[ConversionsCommon.RBG_MAX];
        int Dg[] = new int[ConversionsCommon.RBG_MAX];
        int Db[] = new int[ConversionsCommon.RBG_MAX];

        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        int numberOfPixels = src.getWidth() * src.getHeight();
        Map<String, Map<Integer, Integer>> histo = createHistogram(pic);
        Collection<Integer> r = histo.get(R).values();
        Collection<Integer> g = histo.get(G).values();
        Collection<Integer> b = histo.get(B).values();
        Dr = computeDistribuant(numberOfPixels, r);
        Dg = computeDistribuant(numberOfPixels, g);
        Db = computeDistribuant(numberOfPixels, b);

        int LUTr[] = updateLUTForAlingmentHisto(Dr);
        int LUTg[] = updateLUTForAlingmentHisto(Dg);
        int LUTb[] = updateLUTForAlingmentHisto(Db);

        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                int newPixel = conversionsCommon.colorToRGB(LUTr[red % ConversionsCommon.RBG_MAX], LUTg[green % ConversionsCommon.RBG_MAX], LUTb[blue % ConversionsCommon.RBG_MAX]);
                result.setRGB(i, j, newPixel);
            }
        }
        return result;
    }

    public void writeHistoToFiles(Picture picture) {
        BufferedWriter writer = null;
        Map<String, Map<Integer, Integer>> histo = createHistogram(picture);
        Iterator rIt = histo.get(R).values().iterator();
        File r = new File(R + ".txt");
        Iterator gIt = histo.get(G).values().iterator();
        File g = new File(G + ".txt");
        Iterator bIt = histo.get(B).values().iterator();
        File b = new File(B + ".txt");


        try {
            writeToFile(rIt, r);
            writeToFile(gIt, g);
            writeToFile(bIt, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(Iterator rIt, File r) throws IOException {
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(r));
        while (rIt.hasNext()) {
            writer.write(String.valueOf((Integer) rIt.next()));
            writer.newLine();
        }
        writer.close();
    }

    private int[] computeDistribuant(int numberOfPixels, Collection<Integer> r) {
        Iterator it = r.iterator();
        int[] dr = new int[ConversionsCommon.RBG_MAX];
        int sumR = 0;
        int i = 0;
        while (it.hasNext() && i < ConversionsCommon.RBG_MAX - 1) {
            sumR += (numberOfPixels / (Integer) it.next());
            dr[i] += sumR;
            i++;
        }
        return dr;
    }
}