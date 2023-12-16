package com.example.L12UnitTestAndIntegrationTestdemo.servcie;


import com.example.L12UnitTestAndIntegrationTestdemo.EmployeeDetailDTO;
import com.example.L12UnitTestAndIntegrationTestdemo.entry.Address;
import com.example.L12UnitTestAndIntegrationTestdemo.entry.Employee;
import com.example.L12UnitTestAndIntegrationTestdemo.repo.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    private IEmployeeRepo employeeRepo;

    public EmployeeService(IEmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee create(EmployeeDetailDTO employeeDetailDTO){
        Employee employee =   Employee.builder().name(employeeDetailDTO.getName())
                .email(employeeDetailDTO.getEmail()).build();
        Address address = Address.builder().line1(employeeDetailDTO.getLine1())
                        .line2(employeeDetailDTO.getLine2())
                                .city(employeeDetailDTO.getCity())
                                        .build();
        employee.setAddress(address);
        Employee emp = employeeRepo.save(employee);
        return emp;
    }

    public Employee getEmp(Long id){
        Employee employee = employeeRepo.findById(id).orElse(null);
         employee = employeeRepo.findById(id).orElse(null);
        employee = employeeRepo.findById(id).orElse(null);
        employee = employeeRepo.findById(id).orElse(null);
        return employee;
    }

    public Employee getEmpByEmail(String  email){
        return employeeRepo.findByEmail(email);
    }


    public Employee deleteEmp(Long id){
        Employee emp = getEmp(id);
        employeeRepo.delete(emp);
        return emp;
    }

}
