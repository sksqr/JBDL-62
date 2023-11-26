package com.example.L08SpringJPAdemo.controller;


import ch.qos.logback.core.model.conditional.ElseModel;
import com.example.L08SpringJPAdemo.EmployeeDetailDTO;
import com.example.L08SpringJPAdemo.entry.Employee;
import com.example.L08SpringJPAdemo.servcie.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmp(@RequestBody EmployeeDetailDTO employeeDetailDTO){
        Employee response = employeeService.create(employeeDetailDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Employee> getEmp(@PathVariable Long id){
        Employee employee = employeeService.getEmp(id);
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping("/byEmail")
    public  ResponseEntity<Employee> getEmpByEmail(@RequestParam String email){
        Employee employee = employeeService.getEmpByEmail(email);
        return ResponseEntity.ok().body(employee);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Employee> deleteEmp(@PathVariable Long id){
        Employee employee = employeeService.deleteEmp(id);
        return ResponseEntity.ok().body(employee);
    }

}
