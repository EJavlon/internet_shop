package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuperUser {
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
