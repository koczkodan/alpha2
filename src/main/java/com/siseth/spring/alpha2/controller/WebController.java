package com.siseth.spring.alpha2.controller;


import com.siseth.spring.alpha2.model.Opinion;
import com.siseth.spring.alpha2.model.User;
import com.siseth.spring.alpha2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("login")
    public String getLoginPage(){
        Optional<User> user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user.isPresent()){
            if(user.get().getRole().equals("ROLE_USER")) {
                return "redirect:/user";
            }
            if(user.get().getRole().equals("ROLE_ADMIN")){
                return "redirect:/admin";
            }
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping("/")
    public String getHomePage() {
        Optional<User> user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.isPresent()) {
            if (user.get().getRole().equals("ROLE_USER")) {
                return "redirect:/user";
            } else if (user.get().getRole().equals("ROLE_ADMIN")) {
                return "redirect:/admin";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/successlogged")
    public String getDefaultPageByUserRole(HttpServletRequest request){
        if(request.isUserInRole("ROLE_USER")){
            return "redirect:/user";
        }else if(request.isUserInRole("ROLE_ADMIN")){
            return "redirect:/admin";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/about")
    public String getAboutPage(){
        return "about";
    }

}
