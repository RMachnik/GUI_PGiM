package cw5.util;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;

/**
 * User: SG0219139
 * Date: 11/16/13
 */
public class AddArgDialog extends CommonArgDialog {
    public AddArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            String arg = getTextField().getText();

            ConversionsCw5 conversionsCw5 = new ConversionsCw5();
            setVisible(false);
            PictureCustoms.showImageInNewWindow(conversionsCw5.addValueToColours(getPicture(), Integer.parseInt(arg)));

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
