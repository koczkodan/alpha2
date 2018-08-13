package com.siseth.spring.alpha2.service;

import com.siseth.spring.alpha2.model.Employee;
import com.siseth.spring.alpha2.model.User;
import com.siseth.spring.alpha2.repository.EmployeeRepository;
import com.siseth.spring.alpha2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;


    public Employee GetEmployee(Long id) {
        return employeeRepository.getOne(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> getAllActive(boolean active) {
        return employeeRepository.getAllByActive(active);
    }

    public void addUser(){
        List<User> users = new ArrayList<>();
        users.add(userRepository.findById(new Long(2)).get());

        Optional<Employee> employee = employeeRepository.findById(new Long(2));
        employee.get().setListOfUsers(users);
        employeeRepository.save(employee.get());
    }
}


