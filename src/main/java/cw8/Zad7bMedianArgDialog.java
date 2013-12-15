package cw8;

import common.ConversionsCommon;
import common.Picture;
import common.PictureCustoms;
import cw1.ConversionsCw1;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

/**
 * rafik991@gmai.com
 * 12/12/13
 */
public class Zad7bMedianArgDialog extends MedianArgDialog {
    public Zad7bMedianArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConversionsCw1 conversionsCw1 = new ConversionsCw1();
        ConversionsCw8 conversionsCw8 = new ConversionsCw8();
        BufferedImage y = conversionsCw1.transformRGBToVHS(getPicture().getImage(), ConversionsCommon.KR,
                ConversionsCommon.KB)[0];

        if (getYesButton() == e.getSource()) {
            String arg = getTextField().getText();
            String[] args = arg.split(",");
            if (args.length != 1) {
                throw new RuntimeException("Pass correct parameters!");
            }
            PictureCustoms.showImageInNewWindow(conversionsCw8.medianFilter(new Picture(y),
                    Integer.parseInt(args[0]), ""));

            setVisible(false);
        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
