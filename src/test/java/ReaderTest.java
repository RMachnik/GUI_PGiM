import common.ReaderUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * rafik991@gmail.com
 * 11/24/13
 */
public class ReaderTest {
    ReaderUtil util = new ReaderUtil();

    @Test
    public void readingFileTest() {

        try {
            int result[][] = util.getFileMatrix("static/cw6_zad1a.txt");
            Assert.assertEquals(3, result.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void multiplyMatrixTest() {
        try {
            int result[][] = util.getFileMatrix("static/cw6_zad1a.txt");
            int res1[][] =util.multiplyMatrix(result, 100, 100);
            Assert.assertEquals(res1.length, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
