package com.company.service;

import com.company.model.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CurrencyService {

    private static List<Currency> currencyList = new ArrayList<>();
    private static final String URL = "https://cbu.uz/oz/arkhiv-kursov-valyut/json/";

    public static List<Currency> getCurrencyList() {
        try {
            URL url = new URL(URL);
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Type type = new TypeToken<List<Currency>>() {
            }.getType();
            currencyList = gson.fromJson(reader, type);
            return currencyList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
