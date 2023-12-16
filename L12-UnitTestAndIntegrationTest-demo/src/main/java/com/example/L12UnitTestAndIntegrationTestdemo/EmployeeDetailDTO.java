package com.example.L12UnitTestAndIntegrationTestdemo;


import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDTO {

    private String email;
    private String name;

    private String line1;
    private String line2;

    private String city;
}
