package com.siseth.spring.alpha2.service;

import com.siseth.spring.alpha2.model.Employee;
import com.siseth.spring.alpha2.model.User;
import com.siseth.spring.alpha2.repository.EmployeeRepository;
import com.siseth.spring.alpha2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<User> findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public List<User> findAllByRole(String role){
        return userRepository.findAllByRole(role);
    }

    public Optional<User> findById(Long id){
        return this.userRepository.findById(id);
    }

    public void update(User user){
        userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<Employee> getEmoployeesById(Long id){
       Optional<User> user = userRepository.findById(id);
       List<Employee> employees_1 = user.get().getListOfEmployees();
       List<Employee> employees_2 = employeeRepository.getAllByActive(true);
       employees_2.removeAll(employees_1);
       return employees_2;
    }

}
