package com.siseth.spring.alpha2.controller;

import com.siseth.spring.alpha2.model.User;
import com.siseth.spring.alpha2.service.EmployeeService;
import com.siseth.spring.alpha2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin-page";
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model){
        model.addAttribute("users",userService.findAll());
        return "users";
    }

    @GetMapping("/admin/employee")
    public String getEmployeesFromAdmin(Model model){
        model.addAttribute("employees",employeeService.getAllActive(true));
        return "employees";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String editUsersRole(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if(id==userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getUserId()){
            return("redirect:/admin/users");
        }
        if(user.get().getRole().equals("ROLE_USER")){
            System.out.print("LOL");
            user.get().setRole("ROLE_ADMIN");
        }else{
            user.get().setRole("ROLE_USER");
        }
        userService.update(user.get());

        return "redirect:/admin/users";
    }

    @PutMapping("/admin/users/{id}/edit")
    public String editEmployee(@PathVariable Long id, @Valid @ModelAttribute User user){
        Optional<User> newUser = userService.findById(id);
        newUser.get().setRole(user.getRole());
        newUser.get().setEnabled(user.isEnabled());
        userService.update(newUser.get());
        return "redirect:/admin";
    }

    @GetMapping("/admin/users/{id}/deactivate")
    public String deactivateUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if(id==userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getUserId()){
            return("redirect:/admin/users");
        }
        user.get().setEnabled(!user.get().isEnabled());
        userService.save(user.get());
        return("redirect:/admin/users");
    }
}
