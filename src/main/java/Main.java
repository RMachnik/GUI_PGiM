import common.Picture;

/**
 * Created by SG0219139 on 10/13/13.
 */
public class Main {

    public static void main(String args[]){
        Picture pic = new Picture("LENA_512.jpg");
        System.out.printf("%d-by-%d\n", pic.width(), pic.height());
        pic.show();

    }
}
