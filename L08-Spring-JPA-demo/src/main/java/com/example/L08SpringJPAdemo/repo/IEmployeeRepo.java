package com.example.L08SpringJPAdemo.repo;

import com.example.L08SpringJPAdemo.entry.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee,Long> {

    Employee findByEmail(String email);
}
