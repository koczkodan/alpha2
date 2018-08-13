package com.siseth.spring.alpha2.controller;

import com.siseth.spring.alpha2.model.Employee;
import com.siseth.spring.alpha2.model.Opinion;
import com.siseth.spring.alpha2.model.User;
import com.siseth.spring.alpha2.service.EmployeeService;
import com.siseth.spring.alpha2.service.OpinionService;
import com.siseth.spring.alpha2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Controller
public class OpinionController {

    @Autowired
    private OpinionService opinionService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/user/employee/{id}/new-opinion")
    public String getCreateOpinionPage(@PathVariable Long id, Opinion opinion){
        Optional<Employee> employee = employeeService.findById(id);
        if(employee.isPresent())
            return "opinion-new";
        else return "redirect:/user/employee";

    }

    @PostMapping("/user/employee/{id}/new-opinion")
    public String createOpinion(@ModelAttribute Opinion opinion, @PathVariable(value="id") Long id){
        Optional<User> user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<Employee> employee = employeeService.findById(id);
        Opinion newOpinion = new Opinion();
        if(employee.isPresent()) {
            newOpinion.setActive(true);
            newOpinion.setBody(opinion.getBody());
            newOpinion.setData(new Date(System.currentTimeMillis()));
            newOpinion.setEmployee(employee.get());
        }
        opinionService.save(newOpinion);
        user.get().getListOfEmployees().add(employee.get());
        userService.save(user.get());
        return "redirect:/user";
    }


    @GetMapping("admin/employee/{id}/opinions")
    public String getEmployeeOpinions(@PathVariable Long id, Model model){
        Optional<Employee> employee = employeeService.findById(id);
        model.addAttribute("opinions",opinionService.findAllByEmployee(employee.get()));
        return "employee-opinions";
    }

    @GetMapping("admin/opinions")
    public String getAllOpinions(Model model){
        model.addAttribute("opinions",opinionService.getAll());
        return("opinions");
    }

}
