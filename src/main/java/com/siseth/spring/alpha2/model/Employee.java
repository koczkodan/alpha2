package com.siseth.spring.alpha2.model;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "employee_seq_gen")

    @SequenceGenerator(name = "employee_seq_gen", sequenceName = "employee_id_seq")
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


/*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User uzytkownik;
*/

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

    public List<Opinion> getListOfOpinions() {
        return listOfOpinions;
    }

    public void setListOfOpinions(List<Opinion> listOfOpinions) {
        this.listOfOpinions = listOfOpinions;
    }


    public Employee() {

    }

    public Employee(String firstName, String lastName, String jobDescription, boolean active, List<Opinion> listOfOpinions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobDescription = jobDescription;
        this.active = active;
        this.listOfOpinions = listOfOpinions;
       // this.uzytkownik = uzytkownik;
    }
}
