package com.company.service;

import com.company.database.Database;
import com.company.container.Components;
import com.company.model.*;
import com.company.tdo.ProductDto;
import com.company.tdo.QueryDto;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TableService {

    private static JTable table;
    private static String query;

    public static void getBrand() {

        query = "SELECT * FROM brand ORDER BY id;";
        Brand brand;
        Database.brands.clear();
        try (Statement statement = Components.myConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                brand = new Brand();
                brand.setId(resultSet.getInt("id"));
                brand.setSuperUserId(resultSet.getInt("sp_user_id"));
                brand.setName(resultSet.getString("name"));
                Database.brands.add(brand);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getCustomers() {
        query = "SELECT * FROM customer ORDER BY id;";
        Customer customer;
        Database.customers.clear();
        try (Statement statement = Components.myConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setTell(resultSet.getString("tell"));
                Database.customers.add(customer);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getProducts(String view) {

        Database.products.clear();
        query = "SELECT * FROM " + view + " ORDER BY id";
        Product product;
        try (Statement statement = Components.myConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setCategory(resultSet.getString("category"));
                product.setBrand(resultSet.getString("brand"));
                product.setPrice(resultSet.getDouble("price"));
                product.setAddedDate(LocalDate.parse(resultSet.getString("added_date")));
                product.setExpirationDate(LocalDate.parse(resultSet.getString("expiration_date")));
                product.setName(resultSet.getString("name"));
                product.setAmount(resultSet.getInt("amount"));
                Database.products.add(product);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getSuperUsers() {
        query = "SELECT * FROM super_users ORDER BY id;";
        SuperUser superUser;
        Database.superUsers.clear();

        try (Statement statement = Components.myConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                superUser = new SuperUser();
                superUser.setId(resultSet.getInt("id"));
                superUser.setUsername(resultSet.getString("username"));
                superUser.setPassword(resultSet.getString("password"));
                superUser.setFirstName(resultSet.getString("first_name"));
                superUser.setLastName(resultSet.getString("last_name"));
                Database.superUsers.add(superUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getCategory() {
        query = "SELECT * FROM category ORDER BY id ASC;";
        Category category;
        Database.categories.clear();

        try (Statement statement = Components.myConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setParentId(resultSet.getInt("parent_id"));
                category.setName(resultSet.getString("name"));
                Database.categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getManegers() {
        query = "SELECT * FROM maneger ORDER BY id ASC;";
        Maneger maneger;
        Database.manegers.clear();

        try (Statement statement = Components.myConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                maneger = new Maneger();
                maneger.setId(resultSet.getInt("id"));
                maneger.setBrandId(resultSet.getInt("brand_id"));
                maneger.setFirstName(resultSet.getString("first_name"));
                maneger.setLastName(resultSet.getString("last_name"));
                maneger.setUsername(resultSet.getString("username"));
                maneger.setPassword(resultSet.getString("password"));
                Database.manegers.add(maneger);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getOrders() {
        query = "SELECT * FROM orders ORDER BY id ASC;";
        Orders orders;
        Database.orders.clear();

        try (Statement statement = Components.myConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                orders = new Orders();
                orders.setId(resultSet.getInt("id"));
                orders.setProductId(resultSet.getInt("product_id"));
                orders.setCustomerId(resultSet.getInt("customer_id"));
                orders.setPaymentId(resultSet.getInt("payment_id"));
                orders.setOrderTime(resultSet.getString("order_time"));
                orders.setId(resultSet.getInt("amount"));
                Database.orders.add(orders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getPayments() {
        query = "SELECT * FROM payment ORDER BY id ASC;";
        Payment payment;
        Database.payments.clear();

        try (Statement statement = Components.myConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                payment = new Payment();
                payment.setId(resultSet.getInt("id"));
                payment.setType(resultSet.getString("type"));
                Database.payments.add(payment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getPrice() {
        query = "SELECT * FROM price;";
        Price price;
        Database.prices.clear();

        try (Statement statement = Components.myConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                price = new Price();
                price.setId(resultSet.getInt("id"));
                price.setUzs(resultSet.getDouble("uzs"));
                price.setUsd(resultSet.getDouble("usd"));
                price.setEur(resultSet.getDouble("eur"));
                Database.prices.add(price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean addProduct(ProductDto productDto) {
        Integer categoryId = getCategoryId(productDto.getParentCategoryId(), productDto.getCategoryName());
        Integer price_id = addPrice(productDto.getPrice());
        String query = "INSERT INTO product(category_id,brand_id,price_id,added_date,expiration_date,name,amount) " +
                "VALUES(?,?,?,?,?,?,?);";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement preparedStatement = Components.myConnection.prepareStatement(query)) {
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, productDto.getBrandId());
            preparedStatement.setInt(3, price_id);
            preparedStatement.setDate(4, Date.valueOf(format.format(productDto.getAddedDate())));
            preparedStatement.setDate(5, Date.valueOf(format.format(productDto.getExpirationDate())));
            preparedStatement.setString(6, productDto.getName());
            preparedStatement.setInt(7, productDto.getAmount());
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Integer getCategoryId(Integer parentCategoryId, String categoryName) {
        query = "SELECT id FROM category WHERE parent_id=? AND name=?;";
        try (PreparedStatement preparedStatement = Components.myConnection.prepareStatement(query)) {
            preparedStatement.setInt(1, parentCategoryId);
            preparedStatement.setString(2, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                resultSet.close();
                return id;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static Integer addPrice(Double price) {
        List<Currency> currencyList = CurrencyService.getCurrencyList();
        Currency usd = currencyList.get(0);
        Currency eur = currencyList.get(1);

        query = "INSERT INTO price(uzs,usd,eur) values(?,?,?);";
        try (PreparedStatement preparedStatement1 = Components.myConnection.prepareStatement(query);
             Statement statement = Components.myConnection.createStatement()) {
            preparedStatement1.setDouble(1,price);
            preparedStatement1.setDouble(2,price / Double.parseDouble(usd.getRate()));
            preparedStatement1.setDouble(3,price / Double.parseDouble(eur.getRate()));
            preparedStatement1.execute();
            preparedStatement1.close();

            query = "SELECT id FROM price ORDER BY id DESC LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                resultSet.close();
                return id;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private static Integer updatePrice(ProductDto productDto) {
        List<Currency> currencyList = CurrencyService.getCurrencyList();
        Currency usd = currencyList.get(0);
        Currency eur = currencyList.get(1);

        query = String.format("SELECT price_id FROM product WHERE id=%s;",productDto.getProductId());
        Integer priceId = null;

        try (PreparedStatement preparedStatement1 = Components.myConnection.prepareStatement(query);
             Statement statement = Components.myConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) priceId = resultSet.getInt("price_id");
            preparedStatement1.close();

            query = "UPDATE price SET uzs=?,usd=?,eur=? WHERE id=?;";
            PreparedStatement preparedStatement = Components.myConnection.prepareStatement(query);
            preparedStatement.setDouble(1, productDto.getPrice());
            preparedStatement.setDouble(2, productDto.getPrice() / Double.parseDouble(usd.getRate()));
            preparedStatement.setDouble(3, productDto.getPrice() / Double.parseDouble(eur.getRate()));
            preparedStatement.setInt(4,priceId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return priceId;
    }
    public static Boolean updateProduct(ProductDto productDto) {
        Integer categoryId = getCategoryId(productDto.getParentCategoryId(), productDto.getCategoryName());
        Integer priceId = updatePrice(productDto);
        String query = "UPDATE product SET category_id=?, brand_id=?, price_id=?,added_date=?,expiration_date=?,name=?,amount=? WHERE id =?;";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement preparedStatement = Components.myConnection.prepareStatement(query)) {
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, productDto.getBrandId());
            preparedStatement.setInt(3, priceId);
            preparedStatement.setDate(4, Date.valueOf(format.format(productDto.getAddedDate())));
            preparedStatement.setDate(5, Date.valueOf(format.format(productDto.getExpirationDate())));
            preparedStatement.setString(6, productDto.getName());
            preparedStatement.setInt(7, productDto.getAmount());
            preparedStatement.setInt(8,productDto.getProductId());
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static JTable getProductTable() {
        String[] column = {"Id", "Category", "Brand", "Price", "Added date", "Expiration date", "Name", "Amount"};
        String[][] products = new String[Database.products.size()][column.length];

        for (int i = 0; i < products.length; i++) {
            products[i][0] = String.valueOf(Database.products.get(i).getId());
            products[i][1] = String.valueOf(Database.products.get(i).getCategory());
            products[i][2] = String.valueOf(Database.products.get(i).getBrand());
            products[i][3] = String.valueOf(Database.products.get(i).getPrice());
            products[i][4] = String.valueOf(Database.products.get(i).getAddedDate());
            products[i][5] = String.valueOf(Database.products.get(i).getExpirationDate());
            products[i][6] = String.valueOf(Database.products.get(i).getName());
            products[i][7] = String.valueOf(Database.products.get(i).getAmount());
        }

        table = new JTable(products, column);
        table.setCellSelectionEnabled(true);

        return table;
    }

    public static JTable getBrandTable() {
        String[] column = {"Id", "Super user id", "Name"};
        String[][] brands = new String[Database.brands.size()][column.length];

        for (int i = 0; i < brands.length; i++) {
            brands[i][0] = String.valueOf(Database.brands.get(i).getId());
            brands[i][1] = String.valueOf(Database.brands.get(i).getSuperUserId());
            brands[i][2] = String.valueOf(Database.brands.get(i).getName());
        }
        table = new JTable(brands, column);
        table.setCellSelectionEnabled(true);
        return table;
    }

    public static JTable getCategoryTable() {
        String[] column = {"Id", "Parent id", "Name"};
        String[][] category = new String[Database.categories.size()][column.length];

        for (int i = 0; i < Database.categories.size(); i++) {
            category[i][0] = String.valueOf(Database.categories.get(i).getId());
            category[i][1] = String.valueOf(Database.categories.get(i).getParentId());
            category[i][2] = String.valueOf(Database.categories.get(i).getName());
        }
        table = new JTable(category, column);
        table.setCellSelectionEnabled(true);
        return table;
    }

    public static JTable getCustomerTable() {
        String[] column = {"Id", "First name", "Last name", "Tell"};
        String[][] customer = new String[Database.customers.size()][column.length];

        for (int i = 0; i < Database.customers.size(); i++) {
            customer[i][0] = String.valueOf(Database.customers.get(i).getId());
            customer[i][1] = String.valueOf(Database.customers.get(i).getFirstName());
            customer[i][2] = String.valueOf(Database.customers.get(i).getLastName());
            customer[i][3] = String.valueOf(Database.customers.get(i).getTell());
        }
        table = new JTable(customer, column);
        table.setCellSelectionEnabled(true);
        return table;
    }

    public static JTable getManegerTable() {
        String[] column = {"Id", "Brand id", "First name", "Last name", "Username", "Password"};
        String[][] maneger = new String[Database.manegers.size()][column.length];

        for (int i = 0; i < Database.manegers.size(); i++) {
            maneger[i][0] = String.valueOf(Database.manegers.get(i).getId());
            maneger[i][1] = String.valueOf(Database.manegers.get(i).getBrandId());
            maneger[i][2] = String.valueOf(Database.manegers.get(i).getFirstName());
            maneger[i][3] = String.valueOf(Database.manegers.get(i).getLastName());
            maneger[i][4] = String.valueOf(Database.manegers.get(i).getUsername());
            maneger[i][5] = String.valueOf(Database.manegers.get(i).getPassword());
        }
        table = new JTable(maneger, column);
        return table;
    }

    public static JTable getOrderTable() {
        String[] column = {"Id", "Product id", "Customer id", "Payment id", "Order time", "Amount"};
        String[][] order = new String[Database.orders.size()][column.length];

        for (int i = 0; i < Database.orders.size(); i++) {
            order[i][0] = String.valueOf(Database.orders.get(i).getId());
            order[i][1] = String.valueOf(Database.orders.get(i).getProductId());
            order[i][2] = String.valueOf(Database.orders.get(i).getCustomerId());
            order[i][3] = String.valueOf(Database.orders.get(i).getPaymentId());
            order[i][4] = String.valueOf(Database.orders.get(i).getOrderTime());
            order[i][5] = String.valueOf(Database.orders.get(i).getAmount());
        }
        table = new JTable(order, column);
        return table;
    }

    public static JTable getPaymentTable() {
        String[] column = {"Id", "Type"};
        String[][] payment = new String[Database.payments.size()][column.length];

        for (int i = 0; i < Database.payments.size(); i++) {
            payment[i][0] = String.valueOf(Database.payments.get(i).getId());
            payment[i][1] = String.valueOf(Database.payments.get(i).getType());
        }
        table = new JTable(payment, column);
        return table;
    }

    public static JTable getPriceTable() {
        String[] column = {"Id", "Uzs", "Usd", "Eur"};
        String[][] price = new String[Database.prices.size()][column.length];

        for (int i = 0; i < Database.prices.size(); i++) {
            price[i][0] = String.valueOf(Database.prices.get(i).getId());
            price[i][1] = String.valueOf(Database.prices.get(i).getUzs());
            price[i][2] = String.valueOf(Database.prices.get(i).getUsd());
            price[i][3] = String.valueOf(Database.prices.get(i).getEur());
        }
        table = new JTable(price, column);
        return table;
    }

    public static JTable getSuperUsersTable() {
        String[] column = {"Id", "Username", "Password", "First name", "Last name"};
        String[][] user = new String[Database.superUsers.size()][column.length];

        for (int i = 0; i < Database.superUsers.size(); i++) {
            user[i][0] = String.valueOf(Database.superUsers.get(i).getId());
            user[i][1] = String.valueOf(Database.superUsers.get(i).getUsername());
            user[i][2] = String.valueOf(Database.superUsers.get(i).getPassword());
            user[i][3] = String.valueOf(Database.superUsers.get(i).getFirstName());
            user[i][4] = String.valueOf(Database.superUsers.get(i).getLastName());
        }
        table = new JTable(user, column);
        return table;
    }

    public static String getQuery(QueryDto queryDto) {
        query = "SELECT * FROM product_uzs WHERE ";
        if (queryDto.getAddedDateCheckBox()){
            if (queryDto.getToAddedDateChexBox()){
                query += " added_date BETWEEN '" + queryDto.getAddedDate() + "' AND '" +  queryDto.getToAddedDate() + "' ";
            }else {
                query += " added_date >= '" + queryDto.getAddedDate() + "' ";
            }
        }

        if (!queryDto.getAddedDateCheckBox() && queryDto.getToAddedDateChexBox()){
            query += " added_date <= '" + queryDto.getToAddedDate() + "' ";
        }
        // price 1 tanlangan va sanalarda birortasi tanlangan
        if (queryDto.getPrice1ChexBox() && (queryDto.getAddedDateCheckBox() || queryDto.getToAddedDateChexBox())){
            if (queryDto.getPrice2ChexBox()){
                query += " AND price >= " + queryDto.getPrice1() + " AND price <= " + queryDto.getPrice2() + " ";
            }else {
                query += " AND price >= " + queryDto.getPrice1() + " ";
            }
        }
        //price 1 tanlangan va sanalar tanlanmagan
        if (queryDto.getPrice1ChexBox() && !queryDto.getAddedDateCheckBox() && !queryDto.getToAddedDateChexBox()) {
            if (queryDto.getPrice2ChexBox()){
                query += " price >= " + queryDto.getPrice1() + " AND price <= " + queryDto.getPrice2() + " ";
            }else {
                query += " price >= " + queryDto.getPrice1() + " ";
            }
        }

        //price2 tanlangan va price1 tanlanmagan
        if (queryDto.getPrice2ChexBox() && !queryDto.getPrice1ChexBox()){
            if (queryDto.getAddedDateCheckBox() || queryDto.getToAddedDateChexBox()){
                query += " AND price <= " + queryDto.getPrice2() + " ";
            }else {
                query += " price <= " + queryDto.getPrice2() + " ";
            }
        }

        //name tanlangan va namegacha birortasi tanlangan bosla
        if (queryDto.getNameChexbox()){
            if ((queryDto.getToAddedDateChexBox() || queryDto.getAddedDateCheckBox() || queryDto.getPrice1ChexBox() || queryDto.getPrice2ChexBox())){
                query += " AND name ILIKE '%" + queryDto.getName() + "%';";
            }else {
                query += "name ILIKE '%" + queryDto.getName() + "%';";
            }
        }
        return query;
    }

    public static JTable search(String query) {

        List<Product> searchProduct = new ArrayList<>();
        Product product;
        try (Statement statement = Components.myConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setCategory(resultSet.getString("category"));
                product.setBrand(resultSet.getString("brand"));
                product.setPrice(resultSet.getDouble("price"));
                product.setAddedDate(LocalDate.parse(resultSet.getString("added_date")));
                product.setExpirationDate(LocalDate.parse(resultSet.getString("expiration_date")));
                product.setName(resultSet.getString("name"));
                product.setAmount(resultSet.getInt("amount"));
                searchProduct.add(product);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String[] column = {"Id", "Category", "Brand", "Price", "Added date", "Expiration date", "Name", "Amount"};
        String[][] products = new String[searchProduct.size()][column.length];

        for (int i = 0; i < products.length; i++) {
            products[i][0] = String.valueOf(searchProduct.get(i).getId());
            products[i][1] = String.valueOf(searchProduct.get(i).getCategory());
            products[i][2] = String.valueOf(searchProduct.get(i).getBrand());
            products[i][3] = String.valueOf(searchProduct.get(i).getPrice());
            products[i][4] = String.valueOf(searchProduct.get(i).getAddedDate());
            products[i][5] = String.valueOf(searchProduct.get(i).getExpirationDate());
            products[i][6] = String.valueOf(searchProduct.get(i).getName());
            products[i][7] = String.valueOf(searchProduct.get(i).getAmount());
        }

        table = new JTable(products, column);
        table.setCellSelectionEnabled(true);

        return table;
    }
}
