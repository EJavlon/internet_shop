package com.company.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel {

    private BufferedImage image;
    private int w, h;

    public Panel() {
        setLayout(null);
        setBackground(Color.decode("#3B96F2"));
        setSize(1366, 768);
        setLocation(70, 0);
    }

    public Panel(String fname, int width, int heigth) {
        super();
        setLayout(null);
        setSize(width, heigth);
        setLocation(0, 0);
        //reads the image
        try {
            image = ImageIO.read(new File(fname));
            w = image.getWidth();
            h = image.getHeight();

        } catch (IOException ioe) {
            System.out.println("Could not read in the pic");
            //System.exit(0);
        }

    }

    public Dimension getPreferredSize () {
        return new Dimension(w, h);
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
