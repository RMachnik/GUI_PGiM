package cw3;

import common.ConversionsCommon;
import common.Picture;
import cw1.ConversionsCw1;
import cw2.ConversionsCw2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: SG0219139
 * Date: 10/25/13
 */
public class Cw3 {

    private ConversionsCw2 conversions = new ConversionsCw2();
    private ConversionsCw1 conversionsCw1 = new ConversionsCw1();
    private ConversionsCommon conversionsCommon = new ConversionsCommon();
    private ConversionsCw3 conversionsCw3 = new ConversionsCw3();

    public Cw3(Picture picture) {
        cw3(picture);
    }

    private void cw3(final Picture picture) {
        JMenuBar menuBar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu menuCw3 = new JMenu("CW3");
        menuBar.add(menuCw3);

        JMenuItem conversion = new JMenuItem("Conversion");
        conversion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContrastArgDialog contrastArgDialog = new ContrastArgDialog(picture, false, "Pass contrast arg");
                contrastArgDialog.repaint();
            }
        });

        JMenuItem conversionY = new JMenuItem("ConversionY");
        conversionY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContrastArgDialog contrastArgDialog = new ContrastArgDialog(new Picture(conversionsCw1.transformVHSToRgb(picture, ConversionsCommon.KR, ConversionsCommon.KB)), true, "Pass contrast arg");
                contrastArgDialog.repaint();
            }
        });
        menuCw3.add(conversion);
        menuCw3.add(conversionY);
    }
}
