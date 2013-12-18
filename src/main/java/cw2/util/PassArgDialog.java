package cw2.util;

import com.google.common.base.Function;
import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;
import javafx.util.Pair;

import java.awt.event.ActionEvent;

/**
 * User: SG0219139
 * Date: 10/21/13
 */
public class PassArgDialog extends CommonArgDialog {
     private ConversionsCw2 conversions = new ConversionsCw2();
     private Function<Pair<Integer,Integer> ,Integer> function;
    public PassArgDialog(Picture picture,Function<Pair<Integer,Integer>,Integer> function, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
        this.function = function;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            Integer arg = Integer.parseInt(getTextField().getText());

            setVisible(false);
            PictureCustoms.showImageInNewWindow(conversions.transformUsingFunction(getPicture(), function, arg));

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}

