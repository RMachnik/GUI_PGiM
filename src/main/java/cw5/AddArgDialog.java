package cw5;

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
            String[] args = arg.split(",");
            if (args.length == 2) {
                int arg1 = Integer.parseInt(args[0]);
                ConversionCw5 conversionCw5 = new ConversionCw5();
                setVisible(false);
                PictureCustoms.showImageInNewWindow(conversionCw5.addValueToColours(getPicture(), arg1));
            } else {
                throw new RuntimeException("Pass correct parameter");
            }
        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
