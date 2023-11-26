package com.example.L08SpringJPAdemo.servcie;

import com.example.L08SpringJPAdemo.EmployeeDetailDTO;
import com.example.L08SpringJPAdemo.entry.Address;
import com.example.L08SpringJPAdemo.entry.Employee;
import com.example.L08SpringJPAdemo.repo.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private IEmployeeRepo employeeRepo;
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
