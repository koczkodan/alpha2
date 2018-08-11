package com.siseth.spring.alpha2.service;

import com.siseth.spring.alpha2.model.Employee;
import com.siseth.spring.alpha2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee GetEmployee(Long id){
        return employeeRepository.getOne(id);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public List<Employee> getAllActive(boolean active){
        return employeeRepository.getAllByActive(active);
    }
}
