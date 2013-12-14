package cw8;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;
import common.PictureRunner;

import java.awt.event.ActionEvent;

/**
 * rafik991@gmai.com
 * 12/8/13
 */
public class PassRGBArgDialog extends CommonArgDialog {
    private ConversionsCw8 conversionsCw8;
    private CommonArgDialog commonArgDialog;

    public PassRGBArgDialog(Picture picture, boolean modal, String myMessage,CommonArgDialog commonArgDialog) {
        super(picture, modal, myMessage);
        conversionsCw8 = new ConversionsCw8();
        this.commonArgDialog = commonArgDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {

            String arg = getTextField().getText();
            String args[] = arg.split(",");
            if (args.length != 2) {
                throw new RuntimeException("Pass correct parameter R or G or B and window size");
            }

            setPicture(new Picture(conversionsCw8.meanFilter(commonArgDialog.getPicture(), Integer.parseInt(args[0]),
                    args[1]
            )));
            Thread pic = new Thread(new PictureRunner(getPicture().getImage()));
            pic.start();


        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
