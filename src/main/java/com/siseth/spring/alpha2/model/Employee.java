package com.siseth.spring.alpha2.model;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employee")
//@SequenceGenerator(schema = "public", name = "id_generator", sequenceName = "employee_employee_id_seq", allocationSize = 1)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "job_description")
    private String jobDescription;
    @Column(name = "active",columnDefinition = "boolean default true")
    private boolean active = true;

    @OneToMany(mappedBy = "employee",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Opinion> listOfOpinions;


    @ManyToMany(mappedBy = "listOfEmployees")
    List<User> listOfUsers;


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public List<Opinion> getListOfOpinions() {
        return listOfOpinions;
    }

    public void setListOfOpinions(List<Opinion> listOfOpinions) {
        this.listOfOpinions = listOfOpinions;
    }



    public Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, String jobDescription, boolean active, List<Opinion> listOfOpinions, List<User> listOfUsers) {

        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobDescription = jobDescription;
        this.active = active;
        this.listOfOpinions = listOfOpinions;
        this.listOfUsers = listOfUsers;
    }
}
