import common.Picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SG0219139 on 10/13/13.
 */
public class Main {

    public static void main(String args[]) {

        JFrame frame = new JFrame("Simple GUI_PGiM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.setContentPane(panel);

        JLabel textLabel = new JLabel("Choose what you want!", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        panel.add(textLabel);
        createButtons(panel);
        frame.pack();
        frame.setVisible(true);

        frame.repaint();


    }

    private static void createButtons(JPanel panel) {
        JButton lena = new JButton("LENA");
        lena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread lena = new Thread(new PictureRunner("static/LENA_512.jpg"));
                lena.start();
            }
        });

        JButton ship = new JButton("SHIP");
        ship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread ship = new Thread(new PictureRunner("static/statek_640_505.jpg"));
                ship.start();
            }
        });

        JButton wir = new JButton("WIR");
        wir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread wir = new Thread(new PictureRunner("static/WIR_360.jpg"));
                wir.start();
            }
        });

        JButton women = new JButton("WOMEN");
        women.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread women = new Thread(new PictureRunner("static/kobieta.jpg"));
                women.start();
            }
        });

        JButton peppers = new JButton("PEPPERS");
        peppers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread peppers = new Thread(new PictureRunner("static/peppers.png"));
                peppers.start();
            }
        });

        panel.add(lena);
        panel.add(ship);
        panel.add(wir);
        panel.add(women);
        panel.add(peppers);
    }

    /**
     * User: SG0219139
     * Date: 10/20/13
     */
    private static class PictureRunner implements Runnable {
        private String pictureName;

        public PictureRunner(String picName) {
            pictureName = picName;
        }

        @Override
        public void run() {
            Picture picture = new Picture(pictureName);
            picture.show();
        }
    }
}
