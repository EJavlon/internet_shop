package com.company.service;

import javax.swing.border.Border;
import java.awt.*;


public class RoundBtn implements Border {
    private int r;
    public RoundBtn(int r) {
        this.r = r;
    }
    @Override
    public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, r, r);
    }

    @Override
    public Insets getBorderInsets(java.awt.Component c) {
        return new Insets(this.r+1, this.r+1, this.r+2, this.r);
    }
    public boolean isBorderOpaque() {
        return true;
    }

}