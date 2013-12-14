package common;

import java.awt.image.BufferedImage;

/**
 * User: SG0219139
 * Date: 10/20/13
 */
public class PictureRunner implements Runnable {
    private Picture picture;

    public PictureRunner(String picName) {
        picture = new Picture(picName);
    }

    public PictureRunner(BufferedImage image) {
        picture = new Picture(image);
    }

    @Override
    public void run() {
        picture.show();
    }
}
