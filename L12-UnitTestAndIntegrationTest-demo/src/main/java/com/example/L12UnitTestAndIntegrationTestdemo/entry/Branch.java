package com.example.L12UnitTestAndIntegrationTestdemo.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("branch")
    private Set<Employee> employeeSet;
}
/*
branch
id name
1  Noida

employee
id name    branch_id
1  Shashi    1
1  Ravi    1


 */