package com.company.service;

import com.company.database.Database;
import com.company.container.Components;
import com.company.model.SuperUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelService {

    public static String login(String username, String password) {
        String query = "SELECT first_name, last_name FROM super_users WHERE  username= '" + username
                + "'  AND password='" + password + "';" ;

        for (SuperUser superUser : Database.superUsers) {
            if(superUser.getUsername().equals(username) &&
                    superUser.getPassword().equals(password)){
                return superUser.getFirstName()+ " " + superUser.getLastName();
            }
        }
        return null;
    }

    public static String[] getItem(Integer id) {
        String query = "SELECT name FROM category WHERE parent_id = ?;";
        List<String>row = new ArrayList<>();
        try (PreparedStatement preparedStatement = Components.myConnection.prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               row.add(resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String rows[] = new String[row.size()];
        for (int i = 0; i < row.size(); i++) {
            rows[i] = row.get(i);
        }
        return rows;
    }

    public static String[] getParentItem() {
        String query = "SELECT name FROM category WHERE id <= 5;";
        List<String>row = new ArrayList<>();
        try (Statement statement = Components.myConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                row.add(resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String rows[] = new String[row.size()];
        for (int i = 0; i < row.size(); i++) {
            rows[i] = row.get(i);
        }
        return rows;
    }

    public static String[] getBrandItem() {
        String query = "SELECT name FROM brand;";
        List<String>row = new ArrayList<>();
        try (Statement statement = Components.myConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                row.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String rows[] = new String[row.size()];
        for (int i = 0; i < row.size(); i++) {
            rows[i] = row.get(i);
        }
        return rows;
    }

    public static Integer getParentId(int productId) {
        String query = String.format("SELECT category_id FROM product WHERE id=%s;",productId);
        Integer categoryId = null;
        try (Statement statement = Components.myConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) categoryId = resultSet.getInt("category_id");
            if (Objects.nonNull(categoryId)){
                query = String.format("SELECT parent_id FROM category WHERE id=%s;",categoryId);
                resultSet = statement.executeQuery(query);
                if (resultSet.next()) return resultSet.getInt("parent_id");
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Integer getBrandId(int productId) {
        String query = String.format("SELECT brand_id FROM product WHERE id=%s;",productId);
        try (Statement statement = Components.myConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) return resultSet.getInt("brand_id");
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
