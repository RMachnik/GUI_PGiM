package cw6;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * rafik991@gmail.com
 * 11/28/13
 */
public class RGBArgDialog extends CommonArgDialog {
    public static final String DYL = "DYL";
    public static final String ER = "ER";
    private String dylEr;
    private String matrixFile;

    public RGBArgDialog(Picture picture, boolean modal, String myMessage, String arg, String mtrx) {
        super(picture, modal, myMessage);
        dylEr = arg;
        matrixFile = mtrx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getYesButton() == e.getSource()) {
            String arg = getTextField().getText();

            ConversionsCw6 conversionsCw6 = new ConversionsCw6();
            setVisible(false);
            switch (dylEr) {
                case DYL:
                    try {
                        PictureCustoms.showImageInNewWindow(conversionsCw6.dilatationForRGB(getPicture(), matrixFile, arg));
                        setVisible(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case ER:
                    try {
                        PictureCustoms.showImageInNewWindow(conversionsCw6.erosionForRGB(getPicture(), matrixFile, arg));
                        setVisible(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
            }


        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
}
