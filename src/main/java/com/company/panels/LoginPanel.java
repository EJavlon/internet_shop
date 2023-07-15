package com.company.panels;

import com.company.container.Components;
import com.company.service.PanelService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginPanel extends Panel implements ActionListener {

    private final JTextField username;
    private final JPasswordField password;
    private final JLabel lb_username;
    private final JLabel lb_password;
    private final JButton enter;
    private final JLabel userPhoto;
    private final Font font;

    public LoginPanel() {
        super("src/main/resources/image/fon.jpg", 1366, 768);

        font = new Font("Arial", Font.BOLD, 20);

        ImageIcon imageIcon01 = new ImageIcon("src/main/resources/image/user.png");
        Image img01 = imageIcon01.getImage();
        Image img02 = img01.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imageIcon02 = new ImageIcon(img02);

        userPhoto = new JLabel();
        userPhoto.setLocation(590, 120);
        userPhoto.setIcon(imageIcon02);
        userPhoto.setSize(200, 200);
        add(userPhoto);

        lb_username = new JLabel("lb_username");
        lb_username.setText("username");
        lb_username.setSize(100, 30);
        lb_username.setFont(font);
        lb_username.setLocation(500, 350);
        add(lb_username);

        username = new JTextField();
        username.setSize(150, 30);
        username.setFont(font);
        username.setLocation(620, 350);
        username.addActionListener(this);
        add(username);

        lb_password = new JLabel("lb_password");
        lb_password.setText("password");
        lb_password.setSize(100, 30);
        lb_password.setFont(font);
        lb_password.setLocation(500, 400);
        add(lb_password);

        password = new JPasswordField();
        password.setSize(150, 30);
        password.setFont(font);
        password.setLocation(620, 400);
        password.addActionListener(this);
        add(password);


        enter = new JButton("Log In");
        enter.setSize(150, 30);
        enter.setFont(font);
        enter.setLocation(620, 470);
        enter.addActionListener(this);
        enter.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(enter);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(enter)) {
            if (username.getText().isEmpty() || String.valueOf(password.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username or password empty!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String user = PanelService.login(username.getText(), String.valueOf(password.getPassword()));

            if (Objects.isNull(user)) {
                JOptionPane.showMessageDialog(null, "Username or password incorrect!",
                        "Log in", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Welecom " + user, "Log in",
                        JOptionPane.INFORMATION_MESSAGE);
                setEnabled(false);
                Components.form.homePanel();
                username.setText("");
                password.setText("");
            }
        }
    }
}
