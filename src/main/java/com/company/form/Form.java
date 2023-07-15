package com.company.form;

import com.company.container.Components;
import com.company.panels.*;
import com.company.panels.Panel;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {

    private LoginPanel loginPanel;
    private HomePanel homePanel;
    private MenuPanel menuPanel;
    private InsertPanel insertPanel;
    private DeletionPanel deletionPanel;
    private UpdatePanel updatePanel;
    private SearchPanel searchPanel;

    public Form() throws HeadlessException {
        super();

        menuPanel = Components.menuPanel;
        loginPanel = new LoginPanel();
        homePanel = new HomePanel();
        insertPanel = new InsertPanel();
        deletionPanel = new DeletionPanel();
        updatePanel = new UpdatePanel();
        searchPanel = new SearchPanel();

        add(menuPanel);
        add(loginPanel);
        add(homePanel);
        add(insertPanel);
        add(deletionPanel);
        add(updatePanel);
        add(searchPanel);

        loginPanel();
    }

    public void loginPanel() {
        menuPanel.setVisible(false);
        disablePanel(loginPanel);
        loginPanel.setVisible(true);
        setTitle("LOG IN");
    }
    public void homePanel() {
        disablePanel(homePanel);
        menuPanel.setVisible(true);
        homePanel.setVisible(true);
        setTitle("HOME");
    }
    public void insertPanel() {
        disablePanel(insertPanel);
        insertPanel.setVisible(true);
        setTitle("INSERT PANEL");
    }
    public void deletiontPanel() {
        disablePanel(deletionPanel);
        deletionPanel.setVisible(true);
        setTitle("DELETION PANEL");
    }
    public void updatePanel() {
        disablePanel(updatePanel);
        updatePanel.setVisible(true);
        setTitle("UPDATE PANEL");
    }
    public void searchPanel() {
        disablePanel(searchPanel);
        searchPanel.setVisible(true);
        setTitle("SEARCH PANEL");
    }
    private void disablePanel(Panel currentPanel) {
        if(!currentPanel.equals(loginPanel)){
            loginPanel.setVisible(false);
        }
        if(!currentPanel.equals(homePanel)){
            homePanel.setVisible(false);
        }
        if(!currentPanel.equals(insertPanel)){
            insertPanel.setVisible(false);
        }
        if(!currentPanel.equals(deletionPanel)){
            deletionPanel.setVisible(false);
        }
        if(!currentPanel.equals(updatePanel)){
            updatePanel.setVisible(false);
        }
        if(!currentPanel.equals(searchPanel)){
            searchPanel.setVisible(false);
        }
    }


}
