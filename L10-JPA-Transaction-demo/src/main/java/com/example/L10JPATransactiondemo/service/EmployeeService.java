package com.example.L10JPATransactiondemo.service;

import com.example.L10JPATransactiondemo.EmployeeDetailDTO;
import com.example.L10JPATransactiondemo.aspect.LogExecutionTime;
import com.example.L10JPATransactiondemo.entry.Address;
import com.example.L10JPATransactiondemo.entry.Employee;
import com.example.L10JPATransactiondemo.exception.CardNotCreatedException;
import com.example.L10JPATransactiondemo.exception.LaptopNotAllocatedException;
import com.example.L10JPATransactiondemo.repo.IEmployeeRepo;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private IEmployeeRepo employeeRepo;

    private boolean cardCreated = true;

    private boolean laptopAllocated = true;

    @Transactional(rollbackOn = {CardNotCreatedException.class}, dontRollbackOn={LaptopNotAllocatedException.class})
    public Employee create(EmployeeDetailDTO employeeDetailDTO) throws CardNotCreatedException, LaptopNotAllocatedException {
        Employee employee =   Employee.builder().name(employeeDetailDTO.getName())
                .email(employeeDetailDTO.getEmail()).build();
        Address address = Address.builder().line1(employeeDetailDTO.getLine1())
                        .line2(employeeDetailDTO.getLine2())
                                .city(employeeDetailDTO.getCity())
                                        .build();
        employee.setAddress(address);
        Employee emp = employeeRepo.save(employee);
        emp.setName("NextName");
        //Service call for cardCreation
        if(!cardCreated){
            throw new CardNotCreatedException();
        }
        //Allocate laptop
        if(!laptopAllocated){
            throw new LaptopNotAllocatedException();
        }
        LOGGER.info("Created Employee : {}",employee);
        return emp;
    }

    @LogExecutionTime
    public Employee getEmp(Long id){
        Employee employee = employeeRepo.findById(id).orElse(null);
         employee = employeeRepo.findById(id).orElse(null);
        employee = employeeRepo.findById(id).orElse(null);
        employee = employeeRepo.findById(id).orElse(null);
        return employee;
    }

    @LogExecutionTime
    public Employee getEmpByEmail(String  email){
        return employeeRepo.findByEmail(email);
    }


    public Employee deleteEmp(Long id){
        Employee emp = getEmp(id);
        employeeRepo.delete(emp);
        return emp;
    }


    @Transactional
    public Employee updateEmp(Long id){
        Employee emp = getEmp(id);
        emp.setName("NewName");
        //employeeRepo.save(emp);
        return emp;
    }

}
