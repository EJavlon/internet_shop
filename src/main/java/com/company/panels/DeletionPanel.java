package com.company.panels;

import com.company.container.Components;
import com.company.service.ProductService;
import com.company.service.TableService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class DeletionPanel extends Panel {

    private JTable table;
    private JScrollPane scrollPane;

    public DeletionPanel() {
        super();
        productTable();
    }

    public void productTable() {
        if (Objects.isNull(table)) {
            table = TableService.getProductTable();
            table.setCellSelectionEnabled(true);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(200, 20, 900, 600);
            table.setFillsViewportHeight(true);
            table.setForeground(Color.black);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int rowIndex = table.getSelectedRow();
                    int operation = JOptionPane.showConfirmDialog(DeletionPanel.this, String.format("Id => %s\nCategory => %s\nBrand => %s\n Price=> %s\nAdded Date => %s\nExpiration date => %s\nName => %s\nAmount => %s\n\nProductni o'chirasizmi ?",
                            table.getValueAt(rowIndex, 0), table.getValueAt(rowIndex, 1), table.getValueAt(rowIndex, 2), table.getValueAt(rowIndex, 3), table.getValueAt(rowIndex, 4), table.getValueAt(rowIndex, 5), table.getValueAt(rowIndex, 6), table.getValueAt(rowIndex, 7)), "Tanlangan product ma'lumotlari", JOptionPane.YES_NO_OPTION);
                    if (operation == 0) {
                        int id = Integer.parseInt(String.valueOf(table.getModel().getValueAt(rowIndex, 0)));
                        if (ProductService.deleteProduct(id)){
                            TableService.getProducts("product_uzs");
                            table.setModel(TableService.getProductTable().getModel());
                            Components.insertPanel.productTable();
                            JOptionPane.showMessageDialog(DeletionPanel.this, "Ma'lumotlar muffaqiyatli o'chirildi!","Message",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(DeletionPanel.this, "Bunday product topilmadi!","Message",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            add(scrollPane);
        } else {
            TableService.getProducts("product_uzs");
            table.setModel(TableService.getProductTable().getModel());
        }
    }
}
