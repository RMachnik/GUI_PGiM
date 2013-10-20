package common;

/**
 * User: SG0219139
 * Date: 10/20/13
 */
public class ConversionsCommon {
    public int colorToRGB(double red, double green, double blue) {

        int newPixel = 0;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }
}
