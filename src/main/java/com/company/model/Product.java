package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Integer id;
    private String category;
    private String brand;
    private Double price;
    private LocalDate addedDate;
    private LocalDate expirationDate;
    private String name;
    private Integer amount;
}
