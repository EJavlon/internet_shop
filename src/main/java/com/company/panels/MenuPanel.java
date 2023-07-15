package com.company.panels;

import com.company.container.Components;
import com.company.service.RoundBtn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuPanel extends JPanel implements ActionListener{

    private final JButton homeButton;
    private final JButton openAndClose;
    private final JButton  create;
    private final JButton delete;
    private final JButton update;
    private final JButton search;
    private final JButton logOut;
    private final Font font;
    private boolean isActive;

    private ImageIcon icon;
    private Image img01;
    private Image img02;

    public MenuPanel() {
        super();

        font = new Font("Arial",Font.BOLD,22);
        setFont(font);

        icon = new ImageIcon("src/main/resources/image/home.jpeg");
        img01 = icon.getImage();
        img02 = img01.getScaledInstance(50, 50,40);
        homeButton = new JButton();
        homeButton.setIcon(new ImageIcon(img02));
        homeButton.setSize(50,50);
        homeButton.setLocation(10,10);
        homeButton.setBackground(Color.orange);
        homeButton.addActionListener(this);
        homeButton.setToolTipText("Home");
        homeButton.setVisible(false);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(homeButton);

        icon = new ImageIcon("src/main/resources/image/menu.gif");
        img01 = icon.getImage();
        img02 = img01.getScaledInstance(100, 100,40);
        openAndClose = new JButton();
        openAndClose.setIcon(new ImageIcon(img02));
        openAndClose.setSize(50,50);
        openAndClose.setLocation(10,10);
        openAndClose.setBackground(Color.orange);
        openAndClose.addActionListener(this);
        openAndClose.setToolTipText("Menu");
        openAndClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(openAndClose);

        create = new JButton("Add");
        create.setBorder(new RoundBtn(10) );
        create.setSize(200,30);
        create.setLocation(100,150);
        create.addActionListener(this);
        create.setToolTipText("Qo'shish");
        create.setCursor(new Cursor(Cursor.HAND_CURSOR));
        create.setFont(font);
        add(create);

        delete = new JButton("Delete");
        delete.setBorder(new RoundBtn(10) );
        delete.setSize(200,30);
        delete.setLocation(100,230);
        delete.addActionListener(this);
        delete.setToolTipText("O'chirish");
        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        delete.setFont(font);
        add(delete);

        update = new JButton("Update");
        update.setBorder(new RoundBtn(10) );
        update.setSize(200,30);
        update.setLocation(100,310);
        update.addActionListener(this);
        update.setToolTipText("Yangilash");
        update.setCursor(new Cursor(Cursor.HAND_CURSOR));
        update.setFont(font);
        add(update);

        search = new JButton("Search");
        search.setBorder(new RoundBtn(10) );
        search.setSize(200,30);
        search.setLocation(100,390);
        search.addActionListener(this);
        search.setToolTipText("Qidirish");
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search.setFont(font);
        add(search);

        logOut = new JButton("Log out");
        logOut.setBorder(new RoundBtn(10) );
        logOut.setSize(200,30);
        logOut.setLocation(100,470);
        logOut.addActionListener(this);
        logOut.setToolTipText("Tizimdan chiqish");
        logOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logOut.setFont(font);
        add(logOut);

        setLayout(null);
        setBackground(Color.orange);
        setSize(70,768);
        setLocation(0,0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(homeButton)){
            if(Objects.nonNull(Components.homePanel)){
                Components.homePanel.productTable();
            }
            Components.form.homePanel();
        }else  if(e.getSource().equals(openAndClose)){
            if(isActive){
                setSize(70,768);
                openAndClose.setLocation(10,10);
                homeButton.setVisible(false);
                isActive = false;
            }else {
                setSize(400,768);
                openAndClose.setLocation(330,10);
                homeButton.setVisible(true);
                isActive = true;
            }
        }else if(e.getSource().equals(create)){
            if (Objects.nonNull(Components.insertPanel)){
                Components.insertPanel.productTable();
            }
            Components.form.insertPanel();
        }else if(e.getSource().equals(delete)){
            if (Objects.nonNull(Components.deletionPanel)){
                Components.deletionPanel.productTable();
            }
            Components.form.deletiontPanel();
        }else if(e.getSource().equals(update)){
            Components.form.updatePanel();
        }else if(e.getSource().equals(search)){
            Components.searchPanel.productTable();
            Components.form.searchPanel();
        }else if(e.getSource().equals(logOut)){
            setVisible(false);
            Components.form.loginPanel();
            setSize(70,768);
            openAndClose.setLocation(10,10);
            homeButton.setVisible(false);
            isActive = false;
        }
    }
}

