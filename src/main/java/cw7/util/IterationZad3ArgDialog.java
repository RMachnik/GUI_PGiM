package cw7.util;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * rafik991@gmai.com
 * 12/8/13
 */
public class IterationZad3ArgDialog extends CommonArgDialog {


    public IterationZad3ArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            String arg = getTextField().getText();

            ConversionsCw7 conversionsCw7 = new ConversionsCw7();
            setVisible(false);
            try {
                PictureCustoms.showImageInNewWindow(conversionsCw7.iterativeMethod(getPicture(),
                        "static/cw7_zad3a.txt", "static/cw7_zad3b.txt", Integer.parseInt(arg)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            setVisible(false);
        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }

    }
}
