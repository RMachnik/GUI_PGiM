package cw8.util;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;

/**
 * rafik991@gmai.com
 * 12/12/13
 */
public class MedianArgDialog extends CommonArgDialog {

     private String argTab[];
    public MedianArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConversionsCw8 conversionsCw8 = new ConversionsCw8();
        String args = getTextField().getText();
        if (getYesButton() == e.getSource()) {
            if (args.isEmpty()) {
                throw new RuntimeException("Pass correct parameter!");
            }
          argTab = args.split(",");
            PictureCustoms.showImageInNewWindow(conversionsCw8.medianFilter(getPicture(),
                    Integer.parseInt(argTab[1]), argTab[0]));

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
