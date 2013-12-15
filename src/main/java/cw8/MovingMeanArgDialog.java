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
public class MovingMeanArgDialog extends CommonArgDialog {
    private String argTab[];
   private BufferedImage filtered;
    public MovingMeanArgDialog(Picture picture, boolean modal, String myMessage) {
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

            PictureCustoms.showImageInNewWindow(conversionsCw8.meanFilter(new Picture(filtered),
                    Integer.parseInt(argTab[0]), argTab[1]));
            setVisible(false);
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
