package com.siseth.spring.alpha2.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "opinion")
public class Opinion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinion_id")
    private Long opinionId;
    @Column(nullable = false, name = "body",columnDefinition = "TEXT")
    private String body;
    @Column(nullable = false, name = "data")
    private Date data;
    @Column(nullable = false, name = "active", columnDefinition = "boolean default true")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Opinion(String body, Date data, boolean active, Employee employee) {
        this.body = body;
        this.data = data;
        this.active = active;
        this.employee = employee;
    }

    public Opinion() {
    }

    public Long getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Long opinionId) {
        this.opinionId = opinionId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
