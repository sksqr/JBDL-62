package com.example.L13SpringDataRedisdemo.repo;



import com.example.L13SpringDataRedisdemo.entry.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee,Long> {

    Employee findByEmail(String email);
}
