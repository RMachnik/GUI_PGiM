package cw1;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: SG0219139
 */
public class ChessSizeDialog extends CommonArgDialog {


    public ChessSizeDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            String[] size = getTextField().getText().split("X");
            if (size.length == 2) {
                setVisible(false);
                ConversionsCw1 conversionsCw1 = new ConversionsCw1();
                PictureCustoms.showImageInNewWindow(conversionsCw1.createCheckerBoard(Integer.parseInt(size[0]), Integer.parseInt(size[1])));
            }
        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }

}
