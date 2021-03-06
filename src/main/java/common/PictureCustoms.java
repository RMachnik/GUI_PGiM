package common;


import cw1.Cw1;
import cw2.Cw2;
import cw3.Cw3;
import cw4.Cw4;
import cw5.Cw5;
import cw6.Cw6;
import cw7.Cw7;
import cw8.Cw8;
import templateMatching.TemplateMatching;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SG0219139 on 10/13/13.
 */
public class PictureCustoms {
    public static void customMenu(final Picture picture) {
        Cw1 cw1 = new Cw1(picture);
        Cw2 cw2 = new Cw2(picture);
        Cw3 cw3 = new Cw3(picture);
        Cw4 cw4 = new Cw4(picture);
        Cw5 cw5 = new Cw5(picture);
        Cw6 cw6 = new Cw6(picture);
        Cw7 cw7 = new Cw7(picture);
        Cw8 cw8 = new Cw8(picture);
        TemplateMatching templateMatching = new TemplateMatching(picture);


    }

    public static void showImageInNewWindow(Image fildered) {

        JFrame frame1 = new JFrame();
        frame1.setContentPane(getJLabel(fildered));
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setResizable(false);
        frame1.pack();
        frame1.setVisible(true);

        frame1.repaint();
    }

    public static JLabel getJLabel(Image customImage) {
        ImageIcon icon = null;
        if (customImage != null) {
            icon = new ImageIcon(customImage);
        }
        return new JLabel(icon);
    }


}
