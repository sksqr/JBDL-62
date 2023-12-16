package com.example.L12UnitTestAndIntegrationTestdemo;

import com.example.L12UnitTestAndIntegrationTestdemo.entry.Address;
import com.example.L12UnitTestAndIntegrationTestdemo.entry.Employee;
import com.example.L12UnitTestAndIntegrationTestdemo.repo.IEmployeeRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = ("classpath:application-it.properties")
)
@AutoConfigureMockMvc
public class EmployeeAPITests {

    @Autowired
    private MockMvc mockMvc;

    private Employee employee;
    private EmployeeDetailDTO employeeDetailDTO;

    @Autowired
    private IEmployeeRepo employeeRepo;

    @BeforeEach
    public void setup(){
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
        employeeRepo.deleteAll();
    }

    @Test
    public void testCreateEmpAPI() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(employeeDetailDTO);
        Integer intialSize = employeeRepo.findAll().size();

        mockMvc.perform(post("/emp")
                .contentType("application/json")
                .content(jsonData))
                .andDo(print()).andExpect(status().isOk());

        Integer finalSize = employeeRepo.findAll().size();
        assertThat(finalSize-intialSize).isEqualTo(1);

    }

    @Test
    public void testGetEmpByEmailSuccess() throws Exception {
        employeeRepo.save(employee);
        mockMvc.perform(get("/emp/byEmail?email=emp1@yopmail.com"))
                .andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void testGetEmpByEmailFailure() throws Exception {
        mockMvc.perform(get("/emp/byEmail?email=random@yopmail.com"))
                .andDo(print()).andExpect(status().isNotFound());
    }
}
