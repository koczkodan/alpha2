package com.siseth.spring.alpha2.repository;

import com.siseth.spring.alpha2.model.Employee;
import com.siseth.spring.alpha2.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<Opinion> findAllByEmployee(Employee employee);
}
