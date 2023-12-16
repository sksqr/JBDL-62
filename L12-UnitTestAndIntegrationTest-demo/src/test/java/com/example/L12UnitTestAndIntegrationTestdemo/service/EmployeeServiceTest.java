package com.example.L12UnitTestAndIntegrationTestdemo.service;

import com.example.L12UnitTestAndIntegrationTestdemo.EmployeeDetailDTO;
import com.example.L12UnitTestAndIntegrationTestdemo.entry.Address;
import com.example.L12UnitTestAndIntegrationTestdemo.entry.Employee;
import com.example.L12UnitTestAndIntegrationTestdemo.repo.IEmployeeRepo;
import com.example.L12UnitTestAndIntegrationTestdemo.servcie.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    private AutoCloseable autoCloseable;
    @Mock
    private IEmployeeRepo employeeRepo;

    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDetailDTO employeeDetailDTO;

    @BeforeEach
    public void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        employeeService = employeeService = new EmployeeService(employeeRepo);
        Address address = Address.builder()
                .id(1l)
                .line1("A-1")
                .line2("Sector-10")
                .city("Delhi")
                .build();

        employee = Employee.builder()
                .id(1l)
                .email("emp1@yopmail.com")
                .name("Rahul")
                .address(address)
                .build();

        employeeDetailDTO = EmployeeDetailDTO.builder()
                .email("emp1@yopmail.com")
                .name("Rahul")
                .line1("A-1")
                .line2("Sector-10")
                .city("Delhi")
                .build();

    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();;
    }

    @Test
    public void testCreateEmp(){
        when(employeeRepo.save(any())).thenReturn(employee);
        Employee actual = employeeService.create(employeeDetailDTO);
        assertThat(actual).isEqualTo(employee);
    }


}
