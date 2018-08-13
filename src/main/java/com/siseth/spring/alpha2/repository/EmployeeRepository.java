package com.siseth.spring.alpha2.repository;

import com.siseth.spring.alpha2.model.Employee;
import com.siseth.spring.alpha2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  // public Optional<Employee> findById(Long id);
    List<Employee> getAllByActive(boolean active);
}
