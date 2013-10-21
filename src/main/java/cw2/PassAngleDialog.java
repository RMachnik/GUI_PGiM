package cw2;

import com.google.common.base.Function;
import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: SG0219139
 * Date: 10/21/13
 */
public class PassAngleDialog extends CommonArgDialog {
    ConversionsCw2 conversionsCw2 = new ConversionsCw2();
    public PassAngleDialog(Picture pic, boolean modal, String myMessage) {
        super(pic, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            Double arg = Double.parseDouble(getTextField().getText());

            setVisible(false);
            PictureCustoms.showImageInNewWindow(conversionsCw2.transformUsingAngle(getPicture(), arg));

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
