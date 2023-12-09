package com.example.L10JPATransactiondemo.repo;


import com.example.L10JPATransactiondemo.entry.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee,Long> {

    Employee findByEmail(String email);
}
