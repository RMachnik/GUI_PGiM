package cw8;

import common.CommonArgDialog;
import common.Picture;
import common.PictureCustoms;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

/**
 * rafik991@gmai.com
 * 12/12/13
 */
public class MedianArgDialog extends CommonArgDialog {

    private BufferedImage filtered;
     private String argTab[];
    public MedianArgDialog(Picture picture, boolean modal, String myMessage) {
        super(picture, modal, myMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConversionsCw8 conversionsCw8 = new ConversionsCw8();
        String args = getTextField().getText();
        if (getYesButton() == e.getSource()) {
            if (args.isEmpty()) {
                throw new RuntimeException("Pass correct parameter!");
            }
          argTab = args.split(",");

            switch (argTab[0]) {
                case ("S"):
                    filtered = conversionsCw8.steadyDysfunction(getPicture(), Integer.parseInt(argTab[3]),
                            Double.parseDouble(argTab[4]));
                    break;
                case ("N"):
                    filtered = conversionsCw8.normalDysfunction(getPicture(), Integer.parseInt(argTab[3]), Integer
                            .parseInt
                                    (argTab[4]),
                            Double.parseDouble(argTab[5]));
                    break;
                case ("SP"):
                    filtered = conversionsCw8.saltAndPepperNoise(getPicture(), Double.parseDouble(argTab[3]));
                    break;
                default:
                    throw new RuntimeException("pass correct value!");
            }
            PictureCustoms.showImageInNewWindow(conversionsCw8.medianFilter(new Picture(filtered),
                    Integer.parseInt(argTab[1]), argTab[2]));

        } else if (getNoButton() == e.getSource()) {
            System.err.println("User chose no.");
            setVisible(false);
        }
    }
    public BufferedImage getFiltered() {
        return filtered;
    }

    public String[] getArgTab() {
        return argTab;
    }
}
