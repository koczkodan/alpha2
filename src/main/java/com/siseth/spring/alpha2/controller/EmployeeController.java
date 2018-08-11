package com.siseth.spring.alpha2.controller;

import com.siseth.spring.alpha2.model.Employee;
import com.siseth.spring.alpha2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/admin/employee/new")
    public String GetEmployeePage(Employee employee){
        return "employee-new";
    }

    @PostMapping(value = "/admin/employee/new")
    public String newEmployee(@Valid @ModelAttribute Employee employee) {
       employeeService.save(employee);
       return "redirect:/admin";
    }


}

