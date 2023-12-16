package com.example.L12UnitTestAndIntegrationTestdemo.repo;

import com.example.L12UnitTestAndIntegrationTestdemo.entry.Address;
import com.example.L12UnitTestAndIntegrationTestdemo.entry.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class IEmployeeRepoTest {

    @Autowired
    private IEmployeeRepo employeeRepo;

    private Employee employee;

    @BeforeEach
    void setup(){
        Address address = Address.builder()
                .id(1l)
                .line1("A-1")
                .line2("Sector-10")
                .city("Delhi")
                .build();
        employee = Employee.builder()
                .id(1l)
                .email("Rahul@yopmail.com")
                .name("Rahul")
                .address(address)
                .build();
        employeeRepo.save(employee);
    }

    @AfterEach
    public void tearDown() throws Exception {
        employeeRepo.deleteAll();
    }

    @Test
    void testFindByEmail(){
        Employee actual = employeeRepo.findByEmail("Rahul@yopmail.com");
        assertThat(actual).isEqualTo(employee);
    }

    @Test
    void testFindByEmailFailure(){
        Employee actual = employeeRepo.findByEmail("random@yopmail.com");
        assertThat(actual).isEqualTo(null);
    }

}
