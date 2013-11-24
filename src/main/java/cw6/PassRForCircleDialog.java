package cw6;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;

/**
 * rafik991@gmail.com
 * 11/24/13
 */
public class PassRForCircleDialog extends CommonArgDialog {
    public PassRForCircleDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            String arg = getTextField().getText();

            ConversionCw6 conversionCw6 = new ConversionCw6();
            setVisible(false);
            PictureCustoms.showImageInNewWindow(conversionCw6.circleErosion(getPicture(), Integer.parseInt(arg)));

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }

    }
}
