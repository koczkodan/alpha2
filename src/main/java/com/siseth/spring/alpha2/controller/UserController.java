package com.siseth.spring.alpha2.controller;

import com.siseth.spring.alpha2.DTO.UserDTO;
import com.siseth.spring.alpha2.model.User;
import com.siseth.spring.alpha2.service.EmployeeService;
import com.siseth.spring.alpha2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/sign-up")
    public String showSignUpPage(UserDTO userDTO){
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String createUser(@Valid @ModelAttribute UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole("ROLE_USER");
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping("/user/employee")
    public String getEmployeesFromUser(Model model){
        model.addAttribute("employees",employeeService.getAllActive(true));
        return "employees";
    }


    @GetMapping("/user/employees")
    public String getEmployeesUser(Model model){
        Long id = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getUserId();
        model.addAttribute("employees",userService.getEmoployeesById(id));
        return "employees";
    }

    @GetMapping("/user")
    public String getUserPage(){
        return "user-page";
    }





}
