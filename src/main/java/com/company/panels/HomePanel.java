package com.company.panels;

import com.company.enums.Currency;
import com.company.container.Components;
import com.company.service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HomePanel extends Panel implements ActionListener {

    private static JTable table;
    private final JComboBox comboBox;
    private final Font font;
    private final JLabel lb_currency;
    private final JRadioButton uzs;
    private final JRadioButton usd;
    private final JRadioButton eur;
    private final ButtonGroup currency;
    private final String[] column;
    private static Currency currentCurrency;
    private JScrollPane scrollPane;

    public HomePanel() {
        super();

        font = new Font("Arial", Font.PLAIN, 17);
        currency = new ButtonGroup();
        lb_currency = new JLabel("Valute: ");

        productTable();
        currentCurrency = Currency.UZS;

        column = new String[]{"Product table", "Brand table", "Category table", "Customer table", "Maneger table",
                "Orders table", "Payment table", "Price table", "Super users table"};
        comboBox = new JComboBox(column);
        comboBox.setBounds(800, 480, 400, 50);
        comboBox.setFont(font);
        comboBox.addActionListener(this);
        add(comboBox);

        lb_currency.setLocation(100, 480);
        lb_currency.setSize(100, 30);
        lb_currency.setFont(font);
        add(lb_currency);

        uzs = new JRadioButton("UZS");
        uzs.setBackground(Color.decode("#3B96F2"));
        uzs.setFont(font);
        uzs.setSize(100, 30);
        uzs.setLocation(200, 480);
        uzs.addActionListener(this);
        uzs.setSelected(true);
        currency.add(uzs);

        usd = new JRadioButton("USD");
        usd.setBackground(Color.decode("#3B96F2"));
        usd.setFont(font);
        usd.setSize(100, 30);
        usd.setLocation(300, 480);
        usd.addActionListener(this);
        currency.add(usd);

        eur = new JRadioButton("EUR");
        eur.setBackground(Color.decode("#3B96F2"));
        eur.setFont(font);
        eur.setSize(100, 30);
        eur.setLocation(400, 480);
        eur.addActionListener(this);
        currency.add(eur);

        add(uzs);
        add(usd);
        add(eur);

        Components.homePanel = this;
    }

    public void productTable() {
        if (Objects.isNull(table)) {
            table = TableService.getProductTable();
            table.setCellSelectionEnabled(true);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(100, 20, 1100, 450);
            table.setFillsViewportHeight(true);
            table.setForeground(Color.black);
            table.setBackground(Color.getHSBColor(1,4,255));
            add(scrollPane);
        } else {
            if (currentCurrency.equals(Currency.UZS)){
                TableService.getProducts("product_uzs");
            } else if (currentCurrency.equals(Currency.USD)) {
                TableService.getProducts("product_usd");
            }else{
                TableService.getProducts("product_eur");
            }
            table.setModel(TableService.getProductTable().getModel());
        }
    }

    private void brandTable() {
        table.setModel(TableService.getBrandTable().getModel());
    }

    private void categoryTable() {
        table.setModel(TableService.getCategoryTable().getModel());
    }
    private void customerTable() {
        table.setModel(TableService.getCustomerTable().getModel());
    }
    private void manegerTable() {
        table.setModel(TableService.getManegerTable().getModel());
    }
    private void orderTable() {
        table.setModel(TableService.getOrderTable().getModel());
    }
    private void paymentTable() {
        table.setModel(TableService.getPaymentTable().getModel());
    }
    private void priceTable() {
        table.setModel(TableService.getPriceTable().getModel());
    }
    private void superUserTable() {
        table.setModel(TableService.getSuperUsersTable().getModel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(comboBox.getSelectedIndex(), 0)) {
            TableService.getProducts("product_uzs");
            productTable();
        } else if (Objects.equals(comboBox.getSelectedIndex(), 1)) {
            TableService.getBrand();
            brandTable();
        } else if (Objects.equals(comboBox.getSelectedIndex(), 2)) {
            TableService.getCategory();
            categoryTable();
        } else if (Objects.equals(comboBox.getSelectedIndex(), 3)) {
            TableService.getCustomers();
            customerTable();
        } else if (Objects.equals(comboBox.getSelectedIndex(), 4)) {
            TableService.getManegers();
            manegerTable();
        } else if (Objects.equals(comboBox.getSelectedIndex(), 5)) {
            TableService.getOrders();
            orderTable();
        } else if (Objects.equals(comboBox.getSelectedIndex(), 6)) {
            TableService.getPayments();
            paymentTable();
        } else if (Objects.equals(comboBox.getSelectedIndex(), 7)) {
            TableService.getPrice();
            priceTable();
        }else if (Objects.equals(comboBox.getSelectedIndex(),8)){
            TableService.getSuperUsers();
            superUserTable();
        }

        if (uzs.isSelected() && currentCurrency != Currency.UZS) {
            TableService.getProducts("product_uzs");
            productTable();
            currentCurrency = Currency.UZS;
        } else if (usd.isSelected() && currentCurrency != Currency.USD) {
            TableService.getProducts("product_usd");
            productTable();
            currentCurrency = Currency.USD;
        } else if (eur.isSelected() && currentCurrency != Currency.EUR) {
            TableService.getProducts("product_eur");
            productTable();
            currentCurrency = Currency.EUR;
        }

    }
}
