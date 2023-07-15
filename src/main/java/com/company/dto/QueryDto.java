package com.company.tdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QueryDto {
    private String addedDate;
    private String toAddedDate;
    private Double price1;
    private Double price2;
    private String name;
    private Boolean addedDateCheckBox;
    private Boolean toAddedDateChexBox;
    private Boolean price1ChexBox;
    private Boolean price2ChexBox;
    private Boolean nameChexbox;
}
