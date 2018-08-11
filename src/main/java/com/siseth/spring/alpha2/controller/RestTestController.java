package com.siseth.spring.alpha2.controller;

import com.siseth.spring.alpha2.model.Opinion;
import com.siseth.spring.alpha2.model.User;
import com.siseth.spring.alpha2.repository.UserRepository;
import com.siseth.spring.alpha2.service.EmployeeService;
import com.siseth.spring.alpha2.service.OpinionService;
import com.siseth.spring.alpha2.service.UserService;
import com.siseth.spring.alpha2.utilities.GeneratePdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class RestTestController {

    @Autowired
    UserRepository userService;

    @Autowired
    OpinionService opinionService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("admin/userss")
    public List<User> getAll(){
        Optional<User> user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.findAll();
    }

    @GetMapping(value = "/admin/pdfreport/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> opinionReport(@PathVariable Long id) throws IOException{
        List<Opinion> opinions = (List<Opinion>) opinionService.findAllByEmployee(employeeService.findById(id).get());
        Iterator<Opinion> i = opinions.iterator();
        while(i.hasNext()){
            Opinion o = i.next();
            if(!o.isActive()){
                i.remove();
            }
        }
        ByteArrayInputStream bis = GeneratePdfReport.opinionsReport(opinions,id,employeeService);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","inline; filename=opinionsreport_"+
                employeeService.findById(id).get().getFirstName()+"_"+employeeService.findById(id).get().getLastName()+
                ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
