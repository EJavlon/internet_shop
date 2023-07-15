package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {

    private Integer id;
    private Integer productId;
    private Integer customerId;
    private Integer paymentId;
    private String orderTime;
    private Integer amount;
}
