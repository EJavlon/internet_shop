package com.company.database;

import com.company.model.*;

import com.company.service.TableService;

import java.util.ArrayList;
import java.util.List;

public abstract class Database {

    public static List<Brand> brands = new ArrayList<>();
    public static List<Category> categories = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static List<Maneger> manegers = new ArrayList<>();
    public static List<Orders> orders = new ArrayList<>();
    public static List<Payment> payments = new ArrayList<>();
    public static List<Price> prices = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<SuperUser> superUsers = new ArrayList<>();

    public static void load() {
        TableService.getSuperUsers();
        TableService.getProducts("product_uzs");
        TableService.getBrand();
    }
}
