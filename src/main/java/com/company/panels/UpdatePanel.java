package com.company.panels;

import com.company.container.Components;
import com.company.service.PanelService;
import com.company.service.RoundBtn;
import com.company.service.TableService;
import com.company.tdo.ProductDto;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UpdatePanel extends Panel implements ActionListener {

    private Font font;
    private JTable table;
    private JScrollPane scrollPane;
    private JComboBox<String> category;
    private JComboBox<String> parentCategory;
    private JComboBox<String> brand;
    private JTextField price;
    private JXDatePicker addedDate;
    private JXDatePicker expirationDate;
    private JTextField name;
    private JTextField amount;

    private JLabel categoryLb;
    private JLabel parentCategoryLb;
    private JLabel brandLb;
    private JLabel priceLb;
    private JLabel addedDateLb;
    private JLabel expirationDateLb;
    private JLabel nameLb;
    private JLabel amountLb;
    private JButton updateButton;

    private Integer productId;

    public UpdatePanel() {
        super();
        font = new Font("Arial", Font.ITALIC, 17);
        productTable();

        //parent category label
        parentCategoryLb = new JLabel("Parent category");
        parentCategoryLb.setBounds(200,350,200,30);
        parentCategoryLb.setFont(font);
        parentCategoryLb.setForeground(Color.WHITE);
        add(parentCategoryLb);

        //parent category combobox
        parentCategory = new JComboBox<>(PanelService.getParentItem());
        parentCategory.setBounds(330, 350,150,30);
        parentCategory.setCursor(new Cursor(Cursor.HAND_CURSOR));
        parentCategory.setSelectedIndex(-1);
        parentCategory.addActionListener(this);
        parentCategory.setEnabled(false);
        add(parentCategory);

        // category label
        categoryLb = new JLabel("Category");
        categoryLb.setBounds(550,350,200,30);
        categoryLb.setFont(font);
        categoryLb.setForeground(Color.WHITE);
        add(categoryLb);

        //category combobox
        category = new JComboBox<>();
        category.setBounds(630, 350,150,30);
        category.setCursor(new Cursor(Cursor.HAND_CURSOR));
        category.setEnabled(false);
        category.addActionListener(this);
        category.setEnabled(false);
        add(category);

        brandLb = new JLabel("Brand");
        brandLb.setBounds(850,350,200,30);
        brandLb.setFont(font);
        brandLb.setForeground(Color.WHITE);
        add(brandLb);

        brand = new JComboBox<>(PanelService.getBrandItem());
        brand.setBounds(900,350,200,30);
        brand.setFont(font);
        brand.addActionListener(this);
        brand.setSelectedIndex(-1);
        brand.setEnabled(false);
        add(brand);

        priceLb = new JLabel("Price");
        priceLb.setBounds(200,450,80,30);
        priceLb.setFont(font);
        priceLb.setForeground(Color.WHITE);
        add(priceLb);

        price = new JTextField();
        price.setBounds(280,450,150,30);
        price.setFont(font);
        price.addActionListener(this);
        price.setEnabled(false);
        add(price);


        addedDateLb = new JLabel("Added date");
        addedDateLb.setBounds(530,450,100,30);
        addedDateLb.setFont(font);
        addedDateLb.setForeground(Color.WHITE);
        add(addedDateLb);


        addedDate = new JXDatePicker();
        addedDate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        addedDate.setBounds(630,450,150,30);
        addedDate.addActionListener(this);
        addedDate.setEnabled(false);
        add(addedDate);

        expirationDateLb = new JLabel("Expiration date");
        expirationDateLb.setBounds(830,450,150,30);
        expirationDateLb.setFont(font);
        expirationDateLb.setForeground(Color.WHITE);
        add(expirationDateLb);

        expirationDate = new JXDatePicker();
        expirationDate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        expirationDate.setBounds(950,450,150,30);
        expirationDate.addActionListener(this);
        expirationDate.setEnabled(false);
        add(expirationDate);

        nameLb = new JLabel("Name");
        nameLb.setBounds(200,550,100,30);
        nameLb.setFont(font);
        nameLb.setForeground(Color.WHITE);
        add(nameLb);

        name = new JTextField();
        name.setBounds(250,550,170,30);
        name.setFont(font);
        name.addActionListener(this);
        name.setEnabled(false);
        add(name);

        amountLb = new JLabel("Amount");
        amountLb.setBounds(520,550,100,30);
        amountLb.setFont(font);
        amountLb.setForeground(Color.WHITE);
        add(amountLb);

        amount = new JTextField();
        amount.setBounds(580,550,170,30);
        amount.setFont(font);
        amount.addActionListener(this);
        amount.setEnabled(false);
        add(amount);

        updateButton = new JButton("Update product");
        updateButton.setBorder(new RoundBtn(5) );
        updateButton.setToolTipText("Yangilash");
        updateButton.setBounds(850,550,150,30);
        updateButton.setFont(font);
        updateButton.setBackground(Color.ORANGE);
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(this);
        updateButton.setEnabled(false);
        add(updateButton);

        Components.updatePanel = this;
    }
    public void productTable() {
        if (Objects.isNull(table)){
            table = TableService.getProductTable();
            table.setCellSelectionEnabled(true);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(200, 20, 900, 300);
            table.setFillsViewportHeight(true);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int rowIndex = table.getSelectedRow();
                    productId = Integer.parseInt(String.valueOf(table.getValueAt(rowIndex, 0)));
                    Integer parentCategoryId = PanelService.getParentId(productId);
                    Integer brandId = PanelService.getBrandId(productId);
                    String price1 = String.valueOf(table.getValueAt(rowIndex, 3));
                    String addedDate1 = String.valueOf(table.getValueAt(rowIndex, 4));
                    String expirationDate1 = String.valueOf(table.getValueAt(rowIndex, 5));
                    String name1 = String.valueOf(table.getValueAt(rowIndex, 6));
                    String amount1 = String.valueOf(table.getValueAt(rowIndex, 7));

                    if (Objects.nonNull(parentCategoryId)) parentCategory.setSelectedIndex(parentCategoryId-1);
                    if (Objects.nonNull(brandId)) brand.setSelectedIndex(brandId-1);
                    price.setText(price1);
                    addedDate.setDate(java.sql.Date.valueOf(addedDate1));
                    expirationDate.setDate(java.sql.Date.valueOf(expirationDate1));
                    name.setText(name1);
                    amount.setText(amount1);

                    parentCategory.setEnabled(true);
                    brand.setEnabled(true);
                    price.setEnabled(true);
                    addedDate.setEnabled(true);
                    expirationDate.setEnabled(true);
                    name.setEnabled(true);
                    amount.setEnabled(true);
                    updateButton.setEnabled(true);
                }
            });
            add(scrollPane);
        }else {
            TableService.getProducts("product_uzs");
            table.setModel(TableService.getProductTable().getModel());
        }

    }
    public void clear(){
        parentCategory.setSelectedIndex(-1);
        parentCategory.setEnabled(false);
        category.setSelectedIndex(-1);
        category.setEnabled(false);
        brand.setSelectedIndex(-1);
        brand.setEnabled(false);
        price.setText("");
        price.setEnabled(false);
        addedDate.setDate(null);
        addedDate.setEnabled(false);
        expirationDate.setDate(null);
        expirationDate.setEnabled(false);
        name.setText("");
        name.setEnabled(false);
        amount.setText("");
        amount.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(parentCategory)){
            if (parentCategory.getSelectedIndex() > -1){
                category.setModel(new DefaultComboBoxModel<>(PanelService.getItem(parentCategory.getSelectedIndex() + 1)));
                category.setEnabled(true);
            }
        } else if (e.getSource().equals(updateButton)) {
            try {
                double checkPrice = Double.parseDouble(price.getText());
                int checkAmount = Integer.parseInt(amount.getText());
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Price or amount does not match!" , "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (parentCategory.getSelectedIndex() < 0){
                JOptionPane.showMessageDialog(null, "Parent category not selected!" , "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if (brand.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Brand not selected!" , "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }else if (name.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name empty!" , "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Date date1 = addedDate.getDate();
            Date date2 = expirationDate.getDate();

            if (!date2.after(date1)){
                JOptionPane.showMessageDialog(null, "Date invalid" , "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            ProductDto productDto = new ProductDto();
            productDto.setProductId(productId);
            productDto.setParentCategoryId(parentCategory.getSelectedIndex()+1);
            productDto.setCategoryName(String.valueOf(category.getSelectedItem()));
            productDto.setBrandId(brand.getSelectedIndex()+1);
            productDto.setPrice(Double.parseDouble(price.getText()));
            productDto.setAddedDate(date1);
            productDto.setExpirationDate(date2);
            productDto.setName(name.getText());
            productDto.setAmount(Integer.parseInt(amount.getText()));
            Boolean isUpdated = TableService.updateProduct(productDto);
            if(isUpdated){
                productTable();
                JOptionPane.showMessageDialog(null, "Product seccesfully updated", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "An error occurred during the query process", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            clear();
        }
    }
}
