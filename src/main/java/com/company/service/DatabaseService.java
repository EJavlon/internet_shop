package com.company.service;

import com.company.container.Components;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseService {

    public static void connectToData(){

        try {
            Components.myConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/internet_magazin",
                    "postgres", "0885");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
