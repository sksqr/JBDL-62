package com.example.L10JPATransactiondemo;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDetailDTO {

    private String email;
    private String name;

    private String line1;
    private String line2;

    private String city;
}
