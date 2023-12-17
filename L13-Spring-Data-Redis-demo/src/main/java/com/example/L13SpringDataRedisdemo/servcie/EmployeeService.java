package com.example.L13SpringDataRedisdemo.servcie;



import com.example.L13SpringDataRedisdemo.EmployeeDetailDTO;
import com.example.L13SpringDataRedisdemo.entry.Address;
import com.example.L13SpringDataRedisdemo.entry.Employee;
import com.example.L13SpringDataRedisdemo.repo.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeeService {


    private IEmployeeRepo employeeRepo;

    private Map<String,Employee> empLocalCache = new HashMap<>();

    @Autowired
    private RedisTemplate<String, Employee> redisTemplate;

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
        String key = "emp:"+id;
        Employee employee = redisTemplate.opsForValue().get(key);
        if(employee == null){
            employee = employeeRepo.findById(id).orElse(null);
            if(employee != null){
                redisTemplate.opsForValue().set(key,employee, 1, TimeUnit.MINUTES);
            }
        }
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
