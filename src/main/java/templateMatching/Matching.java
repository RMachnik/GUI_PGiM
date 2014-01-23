package templateMatching;

import common.Picture;
import cw4.util.ConversionsCw4;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * rafik991@gmail.com
 * 1/23/14
 */
public class Matching {

    public BufferedImage matchTemplate(Picture picture, Picture template) {
        int minSAD = Integer.MAX_VALUE;
        BufferedImage src = picture.getImage();
        BufferedImage tpl = template.getImage();
        BufferedImage result = new BufferedImage(tpl.getWidth(), tpl.getHeight(), tpl.getType());
        int bestX = 0, bestY = 0, bestSAD;
// loop through the search image
        for (int x = 0; x <= src.getWidth() - tpl.getWidth(); x++) {
            for (int y = 0; y <= src.getHeight() - tpl.getHeight(); y++) {
                int SAD = 0;

                // loop through the template image

                for (int j = 0; j < tpl.getWidth(); j++)
                    for (int i = 0; i < tpl.getHeight(); i++) {

                        int p_SearchIMG = src.getRGB(x + j, y + i);
                        int p_TemplateIMG = tpl.getRGB(j, i);

                        if (p_SearchIMG == p_TemplateIMG) {
                            SAD++;
                        }
                    }

                // save the best found position
                if (SAD == tpl.getHeight() * tpl.getWidth()) {
                    for (int i = 0; i < result.getWidth(); i++) {
                        for (int j = 0; j < result.getHeight(); j++) {
                            result.setRGB(i, j, src.getRGB(x + i, y + j));
                        }
                    }
                    return result;
                }

            }
        }
        for (int i = 0; i < result.getWidth(); i++)
            for (int j = 0; j < result.getHeight(); j++) {
                result.setRGB(i, j, src.getRGB(bestX + i, bestY + j));
            }
        return result;
    }

    public BufferedImage matchTemplateWithNoise(Picture picture, Picture template) {
        int minSAD = Integer.MAX_VALUE;
        BufferedImage src = picture.getImage();
        BufferedImage tpl = template.getImage();
        BufferedImage result = new BufferedImage(tpl.getWidth(), tpl.getHeight(), tpl.getType());
        int bestX = 0, bestY = 0, bestSAD;
// loop through the search image
        for (int x = 0; x <= src.getWidth() - tpl.getWidth(); x++) {
            for (int y = 0; y <= src.getHeight() - tpl.getHeight(); y++) {
                int SAD = 0;

                // loop through the template image

                for (int j = 0; j < tpl.getWidth(); j++) {
                    for (int i = 0; i < tpl.getHeight(); i++) {

                        int p_SearchIMG = new Color(src.getRGB(x + j, y + i)).getRed();
                        int p_TemplateIMG = new Color(tpl.getRGB(j, i)).getRed();
                        SAD += Math.abs(p_SearchIMG - p_TemplateIMG);

                    }
                }
                if (minSAD > SAD) {
                    minSAD = SAD;
                    // give me min SAD
                    bestX = x;
                    bestY = y;
                    bestSAD = SAD;
                }

                // save the best found position

            }
        }
        for (int i = 0; i < result.getWidth(); i++) {
            for (int j = 0; j < result.getHeight(); j++) {
                result.setRGB(i, j, src.getRGB(bestX + i, bestY + j));
            }
        }
        return result;

    }

    public BufferedImage matchTemplateWithNOR(Picture picture, Picture template) {
        int minSAD = Integer.MAX_VALUE;
        ConversionsCw4 conversionsCw4 = new ConversionsCw4();
        BufferedImage src = conversionsCw4.otsuBinaryConversion(picture);
        BufferedImage tpl = conversionsCw4.otsuBinaryConversion(template);
        BufferedImage result = new BufferedImage(tpl.getWidth(), tpl.getHeight(), tpl.getType());
        int bestX = 0, bestY = 0, bestSAD;
        boolean srcTab[][], templateTab[][];
        srcTab = new boolean[src.getWidth()][src.getHeight()];
        templateTab = new boolean[tpl.getWidth()][tpl.getHeight()];
        for (int i = 0; i < srcTab.length; i++)
            for (int j = 0; j < srcTab[i].length; j++) {
                if (new Color(src.getRGB(i, j)).getRed() > 200)
                    srcTab[i][j] = true;
            }
        for (int i = 0; i < templateTab.length; i++)
            for (int j = 0; j < templateTab[i].length; j++) {
                if (new Color(tpl.getRGB(i, j)).getRed() > 200)
                    templateTab[i][j] = true;
            }
// loop through the search image
        for (int x = 0; x <= src.getWidth() - tpl.getWidth(); x++) {
            for (int y = 0; y <= src.getHeight() - tpl.getHeight(); y++) {
                int SAD = 0;

                // loop through the template image

                for (int j = 0; j < tpl.getWidth(); j++) {
                    for (int i = 0; i < tpl.getHeight(); i++) {
                        SAD += (srcTab[j + x][i + y] || templateTab[j][i]) ? 0 : 1;

                    }
                }
                if (minSAD > SAD) {
                    minSAD = SAD;
                    // give me min SAD
                    bestX = x;
                    bestY = y;
                    bestSAD = SAD;
                }

                // save the best found position

            }
        }
        for (int i = 0; i < result.getWidth(); i++) {
            for (int j = 0; j < result.getHeight(); j++) {
                result.setRGB(i, j, src.getRGB(bestX + i, bestY + j));
            }
        }
        return result;

    }
}
 
