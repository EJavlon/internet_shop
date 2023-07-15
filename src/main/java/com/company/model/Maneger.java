package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Maneger {

    private Integer id;
    private Integer brandId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
