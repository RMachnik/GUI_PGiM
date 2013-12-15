package cw8;

import common.CommonArgDialog;
import common.Picture;
import common.PictureRunner;

import java.awt.event.ActionEvent;

/**
 * rafik991@gmai.com
 * 12/8/13
 */
public class NormalFilterArgDialog extends CommonArgDialog {
    private ConversionsCw8 conversionsCw8;

    public NormalFilterArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
        conversionsCw8 = new ConversionsCw8();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            String arg = getTextField().getText();
            String[] args = arg.split(",");
            if (args.length != 3) {
                throw new RuntimeException("Pass correct parameters!");
            }
            setPicture(new Picture(conversionsCw8.normalDysfunction(getPicture(),
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]), Double.parseDouble(args[2]))));
            Thread pic = new Thread(new PictureRunner(getPicture().getImage()));
            pic.start();

            setVisible(false);
        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }

    }
}
