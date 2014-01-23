package templateMatching;

import common.Picture;
import common.PictureCustoms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * rafik991@gmail.com
 * 1/23/14
 */
public class TemplateMatching {
    Matching matching = new Matching();

    public TemplateMatching(Picture picture) {
        TemplateMatching(picture);
    }

    private void TemplateMatching(final Picture picture) {
        JMenuBar bar = picture.getFrame().getJMenuBar();
        final Image image = picture.getImage();
        JMenu tm = new JMenu("TM");
        bar.add(tm);

        JMenuItem templateMatch = new JMenuItem("match face");
        templateMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Picture template = new Picture("static/template.png");
                PictureCustoms.showImageInNewWindow(matching.matchTemplate(picture, template));
            }
        });

        JMenuItem templateMatchWithNoise = new JMenuItem("match face with noise");
        templateMatchWithNoise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Picture template = new Picture("static/template.png");
                PictureCustoms.showImageInNewWindow(matching.matchTemplateWithNoise(picture, template));
            }
        });

        JMenuItem puzle = new JMenuItem("puzzle");
        puzle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Picture puzleEl = new Picture("static/puzle_EL.png");
                PictureCustoms.showImageInNewWindow(matching.matchTemplate(picture, puzleEl));
            }
        });

        JMenuItem puzleNOR = new JMenuItem("puzzleNOR");
        puzleNOR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Picture puzleEl = new Picture("static/puzle_EL.png");
                PictureCustoms.showImageInNewWindow(matching.matchTemplateWithNOR(picture, puzleEl));
            }
        });


        tm.add(templateMatch);
        tm.add(templateMatchWithNoise);
        tm.add(puzle);
        tm.add(puzleNOR);
    }
}
