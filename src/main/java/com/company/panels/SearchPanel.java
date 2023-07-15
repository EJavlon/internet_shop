package com.company.panels;

import com.company.container.Components;
import com.company.service.RoundBtn;
import com.company.service.TableService;
import com.company.tdo.QueryDto;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class SearchPanel extends Panel implements ActionListener {

    private JTable table;
    private JScrollPane scrollPane;
    private JTextField name;
    private JXDatePicker addedDate;
    private JXDatePicker toAddedDate;
    private JTextField price1;
    private JTextField price2;
    private JCheckBox nameCheckBox;
    private JCheckBox addedDateCheckBox;
    private JCheckBox toAddedDateChexBox;
    private JCheckBox priceCheckBox1;
    private JCheckBox priceCheckBox2;
    private JButton search;
    private Font font;

    public SearchPanel() {
        super();
        font = new Font("Arial", Font.ITALIC, 17);

        productTable();

        addedDateCheckBox = new JCheckBox("From date");
        addedDateCheckBox.setBounds(200,350,110,30);
        addedDateCheckBox.addActionListener(this);
        addedDateCheckBox.setBackground(Color.decode("#3B96F2"));
        addedDateCheckBox.setFont(font);
        add(addedDateCheckBox);

        addedDate = new JXDatePicker();
        addedDate.setDate(Calendar.getInstance().getTime());
        addedDate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        addedDate.setBounds(320,350,225,30);
        addedDate.setVisible(true);
        addedDate.addActionListener(this);
        addedDate.setEnabled(false);
        add(addedDate);

        toAddedDateChexBox = new JCheckBox("To date");
        toAddedDateChexBox.setBounds(725,350,150,30);
        toAddedDateChexBox.addActionListener(this);
        toAddedDateChexBox.setBackground(Color.decode("#3B96F2"));
        toAddedDateChexBox.setFont(font);
        add(toAddedDateChexBox);

        toAddedDate = new JXDatePicker();
        toAddedDate.setDate(Calendar.getInstance().getTime());
        toAddedDate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        toAddedDate.setBounds(875,350,225,30);
        toAddedDate.setVisible(true);
        toAddedDate.addActionListener(this);
        toAddedDate.setEnabled(false);
        add(toAddedDate);

        priceCheckBox1 = new JCheckBox("From price");
        priceCheckBox1.setBounds(200,450,110,30);
        priceCheckBox1.addActionListener(this);
        priceCheckBox1.setBackground(Color.decode("#3B96F2"));
        priceCheckBox1.setFont(font);
        add(priceCheckBox1);

        price1 = new JTextField();
        price1.setBounds(320,450,225,30);
        price1.setFont(font);
        price1.setEnabled(false);
        add(price1);

        priceCheckBox2 = new JCheckBox("To price");
        priceCheckBox2.setBounds(725,450,110,30);
        priceCheckBox2.addActionListener(this);
        priceCheckBox2.setBackground(Color.decode("#3B96F2"));
        priceCheckBox2.setFont(font);
        add(priceCheckBox2);

        price2 = new JTextField();
        price2.setBounds(875,450,225,30);
        price2.setFont(font);
        price2.setEnabled(false);
        add(price2);

        nameCheckBox = new JCheckBox("Name");
        nameCheckBox.setBounds(200,550,80,30);
        nameCheckBox.addActionListener(this);
        nameCheckBox.setBackground(Color.decode("#3B96F2"));
        nameCheckBox.setFont(font);
        add(nameCheckBox);

        name = new JTextField();
        name.setBounds(320,550,225,30);
        name.setFont(font);
        name.setEnabled(false);
        add(name);

        search = new JButton("Search");
        search.setToolTipText("Qidirish");
        search.setBounds(880,550,150, 30);
        search.setBorder(new RoundBtn(5));
        search.setBackground(Color.ORANGE);
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search.addActionListener(this);
        search.setFont(font);
        add(search);

        Components.searchPanel = this;
    }
    public void productTable() {
        if (Objects.isNull(table)) {
            table = new JTable();
            table.setCellSelectionEnabled(true);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(200, 20, 900, 300);
            table.setFillsViewportHeight(true);
            table.setForeground(Color.black);
            add(scrollPane);
        } else {
            table.setModel(new DefaultTableModel());
            price1.setText("");
            price2.setText("");
            price1.setEnabled(false);
            price2.setEnabled(false);
            name.setText("");
            name.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addedDateCheckBox) || e.getSource().equals(toAddedDateChexBox)){
            if (addedDateCheckBox.isSelected() || toAddedDateChexBox.isSelected()){
                if (addedDateCheckBox.isSelected()) {
                    addedDate.setEnabled(true);
                }else {
                    addedDate.setEnabled(false);
                }
                if (toAddedDateChexBox.isSelected()) {
                    toAddedDate.setEnabled(true);
                }else {
                    toAddedDate.setEnabled(false);
                }
            }
        }else if (e.getSource().equals(priceCheckBox1) || e.getSource().equals(priceCheckBox2)){
            if (priceCheckBox1.isSelected() || priceCheckBox2.isSelected()){
               if (priceCheckBox1.isSelected()){
                   price1.setEnabled(true);
               }else {
                   price1.setEnabled(false);
               }
               if (priceCheckBox2.isSelected()){
                   price2.setEnabled(true);
               }else {
                   price2.setEnabled(false);
               }
            }
        } else if (e.getSource().equals(nameCheckBox)) {
            if (nameCheckBox.isSelected()){
                name.setEnabled(true);
            }else {
                name.setEnabled(false);
            }
        }else if (e.getSource().equals(search)){
            if (!addedDateCheckBox.isSelected() && !toAddedDateChexBox.isSelected() &&
                !priceCheckBox1.isSelected() && !priceCheckBox2.isSelected() && !nameCheckBox.isSelected()){

                JOptionPane.showMessageDialog(null,"Parameter not selected","Message",JOptionPane.INFORMATION_MESSAGE);
            }else {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                QueryDto queryDto = new QueryDto();
                if (addedDateCheckBox.isSelected() && toAddedDateChexBox.isSelected() && addedDate.getDate().after(toAddedDate.getDate())){
                    JOptionPane.showMessageDialog(null,"Dates do not match Dates do not match","Message",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                queryDto.setAddedDate(format.format(addedDate.getDate()));
                queryDto.setToAddedDate(format.format(toAddedDate.getDate()));
                if (priceCheckBox1.isSelected()){
                    try {
                        queryDto.setPrice1(Double.parseDouble(price1.getText()));
                    }catch (NumberFormatException exception){
                        JOptionPane.showMessageDialog(null,"Enter the correct information in the price", "Message",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                if (priceCheckBox2.isSelected()){
                    try {
                        queryDto.setPrice2(Double.parseDouble(price2.getText()));
                    }catch (NumberFormatException exception){
                        JOptionPane.showMessageDialog(null,"Enter the correct information in the price", "Message",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                }

                if (priceCheckBox1.isSelected() && priceCheckBox2.isSelected()){
                    if (queryDto.getPrice1() > queryDto.getPrice2()){
                        JOptionPane.showMessageDialog(null,"Prices don't match","Message",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                queryDto.setName(name.getText());
                queryDto.setAddedDateCheckBox(addedDateCheckBox.isSelected());
                queryDto.setToAddedDateChexBox(toAddedDateChexBox.isSelected());
                queryDto.setPrice1ChexBox(priceCheckBox1.isSelected());
                queryDto.setPrice2ChexBox(priceCheckBox2.isSelected());
                queryDto.setNameChexbox(nameCheckBox.isSelected());
                String query = TableService.getQuery(queryDto);

                int operation = JOptionPane.showConfirmDialog(null,query + "\n\nSo'rovni  tasdiqlaysizmi ?","Message",JOptionPane.YES_NO_OPTION);
                if (operation==0){
                    table.setModel(TableService.search(query).getModel());
                }
            }
        }
    }
}
