package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Price {

    private Integer id;
    private double uzs;
    private double usd;
    private double eur;
}
