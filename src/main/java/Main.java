import common.PictureRunner;

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
        panel.setPreferredSize(new Dimension(300, 300));
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

        JButton figure = new JButton("FIGURE");
        figure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread figure = new Thread(new PictureRunner("static/figury.png"));
                figure.start();
            }
        });

        JButton convex = new JButton("CONVEX");
        convex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread convex = new Thread(new PictureRunner("static/convex.png"));
                convex.start();
            }
        });

        JButton template1 = new JButton("TEMPLATE1");
        template1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread template1 = new Thread(new PictureRunner("static/template1.png"));
                template1.start();
            }
        });
        JButton template2 = new JButton("template2");
        template2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread template2 = new Thread(new PictureRunner("static/template2.png"));
                template2.start();
            }
        });
        JButton template3 = new JButton("template3");
        template3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread template3 = new Thread(new PictureRunner("static/template3.png"));
                template3.start();
            }
        });
        JButton template4 = new JButton("template4");
        template4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread template4 = new Thread(new PictureRunner("static/template4.png"));
                template4.start();
            }
        });

        JButton template5 = new JButton("template5");
        template5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread template5 = new Thread(new PictureRunner("static/template5.png"));
                template5.start();
            }
        });
        JButton template6 = new JButton("template6");
        template6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread template6 = new Thread(new PictureRunner("static/template6.png"));
                template6.start();
            }
        });

        panel.add(lena);
        panel.add(ship);
        panel.add(wir);
        panel.add(women);
        panel.add(peppers);
        panel.add(figure);
        panel.add(convex);
        panel.add(template1);
        panel.add(template2);
        panel.add(template3);
        panel.add(template4);
        panel.add(template5);
        panel.add(template6);

    }

}
