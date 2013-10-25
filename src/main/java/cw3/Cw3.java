package cw3;

import common.ConversionsCommon;
import common.Picture;
import cw1.ConversionsCw1;
import cw2.ConversionsCw2;

import javax.swing.*;
import java.awt.*;

/**
 * User: SG0219139
 * Date: 10/25/13
 */
public class Cw3 {

    private ConversionsCw2 conversions = new ConversionsCw2();
    private ConversionsCw1 conversionsCw1 = new ConversionsCw1();
    private ConversionsCommon conversionsCommon = new ConversionsCommon();

    public Cw3(Picture picture) {
        cw3(picture);
    }

    private void cw3(final Picture picture) {
        JMenuBar menuBar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu menuCw2 = new JMenu("CW3");
        menuBar.add(menuCw2);
    }
}
