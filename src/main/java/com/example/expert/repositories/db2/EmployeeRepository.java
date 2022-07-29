package com.example.expert.repositories.db2;

import com.example.expert.domain.entities.db2.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
