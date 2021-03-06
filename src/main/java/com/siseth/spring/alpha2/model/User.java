package com.siseth.spring.alpha2.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "uzytkownik")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "email",unique = true)
    @Email
    private String email;
    @Column(name = "password")
    @Size(max = 64)
    private String password;
    @Column(name = "role", columnDefinition = "VARCHAR(255) default 'ROLE_USER'")
    private String role;
    private boolean enabled = true;
//DOTO ZMIENIC TABELKI

    /*
    @ManyToMany
    @JoinTable(name = "uzytkownik_employee", joinColumns = {
            @JoinColumn(name ="user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "employee_id", nullable = false, updatable = false)})
    private List<Employee> listOfEmployees;
    */

    public User(String email, String password, String role,boolean enabled) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled =enabled;
    }

    public User() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}