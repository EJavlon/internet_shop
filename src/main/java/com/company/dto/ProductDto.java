package com.company.tdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Integer productId;
    private Integer parentCategoryId;
    private String categoryName;
    private Integer brandId;
    private Double price;
    private Date addedDate;
    private Date expirationDate;
    private String name;
    private Integer amount;
}
