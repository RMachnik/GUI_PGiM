package cw8;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;

/**
 * rafik991@gmai.com
 * 12/8/13
 */
public class SaltPepperArgDialog extends CommonArgDialog {
    ConversionsCw8 conversionsCw8;

    public SaltPepperArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
        conversionsCw8 = new ConversionsCw8();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {

            String arg = getTextField().getText();
            if (arg.isEmpty()) {
                throw new RuntimeException("Pass correct parameter!");
            }
              setPicture(new Picture(conversionsCw8.pepperSaltDysfunction(getPicture(),
                      Double.parseDouble(arg)
              )));
            PictureCustoms.showImageInNewWindow(getPicture().getImage());


        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }

    }
}