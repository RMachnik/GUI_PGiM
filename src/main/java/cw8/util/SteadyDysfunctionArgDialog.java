package cw8.util;

import common.CommonArgDialog;
import common.Picture;
import common.PictureRunner;

import java.awt.event.ActionEvent;

/**
 * rafik991@gmai.com
 * 12/8/13
 */
public class SteadyDysfunctionArgDialog extends CommonArgDialog {
    ConversionsCw8 conversionsCw8;

    public SteadyDysfunctionArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
        conversionsCw8 = new ConversionsCw8();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            String arg = getTextField().getText();
            String[] args = arg.split(",");
            if (args.length != 2) {
                throw new RuntimeException("Pass correct parameters!");
            }

            Thread pic = new Thread(new PictureRunner(new Picture(conversionsCw8.steadyDysfunction(getPicture(),
                    Integer.parseInt(args[0]),
                    Double.parseDouble(args[1]))).getImage()));
            pic.start();
            setVisible(false);
        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }

    }
}
