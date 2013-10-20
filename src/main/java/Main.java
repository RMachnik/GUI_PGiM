import common.Picture;

/**
 * Created by SG0219139 on 10/13/13.
 */
public class Main {

    public static void main(String args[]) {
        Thread lena = new Thread(new PictureRunner("LENA_512.jpg"));
        Thread ship = new Thread(new PictureRunner("statek_640_505.jpg"));
        lena.start();
        ship.start();


    }

    /**
     * User: SG0219139
     * Date: 10/20/13
     */
    private static class PictureRunner implements Runnable {
        private String pictureName;

        public PictureRunner(String picName) {
            pictureName = picName;
        }

        @Override
        public void run() {
            Picture picture = new Picture(pictureName);
            picture.show();
        }
    }
}
