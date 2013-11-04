package cw3;

import common.ConversionsCommon;
import common.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * User: SG0219139
 * Date: 11/4/13
 */
public class ConversionsCw3 {
    private ConversionsCommon conversionsCommon = new ConversionsCommon();

    public Map<Integer, Integer> createHistogram(Picture p) {
        Map<Integer, Integer> histogram = new HashMap<Integer, Integer>();

        for (int i = 0; i < p.getImage().getWidth(); i++) {
            for (int j = 0; j < p.getImage().getHeight(); j++) {
                if (!histogram.containsKey(p.getImage().getRGB(i, j))) {
                    histogram.put(p.getImage().getRGB(i, j), 1);
                } else {
                    int nVar = histogram.get(p.getImage().getRGB(i, j));
                    nVar++;
                    histogram.put(p.getImage().getRGB(i, j), nVar);
                }
            }
        }
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
                LUT[i] = ((int) (a * (i - ConversionsCommon.RBG_MAX / 2) + ConversionsCommon.RBG_MAX / 2))%ConversionsCommon.RBG_MAX;
        }
        return LUT;
    }

    public BufferedImage contrast(Picture p, double a) {
        BufferedImage src = p.getImage();
        BufferedImage result = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int red,green,blue;
        int LUT[] = getContrastLutTable(a);
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                red = new Color(src.getRGB(i, j)).getRed();
                green = new Color(src.getRGB(i, j)).getGreen();
                blue = new Color(src.getRGB(i, j)).getBlue();
                int newPixel = conversionsCommon.colorToRGB(LUT[red%ConversionsCommon.RBG_MAX],LUT[green%ConversionsCommon.RBG_MAX],LUT[blue%ConversionsCommon.RBG_MAX]);
                result.setRGB(i,j,newPixel );

            }
        }
        return result;
    }


}
