package cw3;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;

/**
 * User: SG0219139
 * Date: 11/4/13
 */
public class ContrastArgDialog extends CommonArgDialog {
    public ContrastArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConversionsCw3 conversionsCw3 = new ConversionsCw3();
        if (getYesButton() == e.getSource()) {
            double arg = Double.parseDouble(getTextField().getText());

            setVisible(false);
            PictureCustoms.showImageInNewWindow(conversionsCw3.contrast(getPicture(), arg));

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
