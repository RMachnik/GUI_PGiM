package cw4.util;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;

/**
 * User: SG0219139
 * Date: 11/9/13
 */
public class BinaryRangeDialog extends CommonArgDialog {
    public BinaryRangeDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            String arg = getTextField().getText();
            String[] args = arg.split(",");
            if (args.length == 2) {
                int arg1 = Integer.parseInt(args[0]);
                int arg2 = Integer.parseInt(args[1]);
                ConversionsCw4 conversionsCw4 = new ConversionsCw4();
                setVisible(false);
                PictureCustoms.showImageInNewWindow(conversionsCw4.simpleRangeBinaryConversion(getPicture(), arg1,arg2));
            } else {
                throw new RuntimeException("Pass correct parameter");
            }
        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
