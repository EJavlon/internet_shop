package com.company;

import com.company.database.Database;
import com.company.container.Components;
import com.company.form.Form;
import com.company.panels.MenuPanel;
import com.company.service.DatabaseService;

import javax.swing.*;
import java.awt.*;

public class InternetMagazin {

    public static void main(String[] args) {

        DatabaseService.connectToData();
        Database.load();

        Components.menuPanel = new MenuPanel();

        Form form = new Form();
        form.setLayout(null);
        form.setSize(1366, 768);
        form.setMinimumSize(new Dimension(1366, 768));
        form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        form.setVisible(true);

        Components.form = form;
    }

}
