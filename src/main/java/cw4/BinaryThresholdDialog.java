package cw4;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;

/**
 * User: SG0219139
 * Date: 11/9/13
 */
public class BinaryThresholdDialog extends CommonArgDialog {
    public BinaryThresholdDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            int arg = Integer.parseInt(getTextField().getText());
            ConversionsCw4 conversionsCw4 = new ConversionsCw4();
            setVisible(false);
            PictureCustoms.showImageInNewWindow(conversionsCw4.simpleBinaryConversion(getPicture(), arg));

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
