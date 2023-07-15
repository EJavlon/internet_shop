package com.company.service;

import com.company.container.Components;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class ProductService {
    private static String query;
    private static Integer priceId;


    public static Boolean deleteProduct(int id) {
        query = "SELECT price_id FROM product WHERE id=?;";
        priceId = null;
        try (PreparedStatement preparedStatement = Components.myConnection.prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) priceId = resultSet.getInt("price_id");
            Statement statement = Components.myConnection.createStatement();
            if (Objects.nonNull(priceId)){
                query = String.format("DELETE FROM product WHERE id=%s;",id);
                statement.execute(query);

                query = String.format("DELETE FROM  price WHERE id=%s;",priceId);
                if(!statement.execute(query)){
                    statement.close();
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
