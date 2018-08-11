package com.siseth.spring.alpha2.service;

import com.siseth.spring.alpha2.model.Employee;
import com.siseth.spring.alpha2.model.Opinion;
import com.siseth.spring.alpha2.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpinionService {
    @Autowired
    private OpinionRepository opinionRepository;


    public Opinion save(Opinion employee){
        return opinionRepository.save(employee);
    }

    public Optional<Opinion> findById(Long id){
        return opinionRepository.findById(id);
    }

    public List<Opinion> getAll(){
        return opinionRepository.findAll();
    }

    public List<Opinion> findAllByEmployee(Employee employee){
        return opinionRepository.findAllByEmployee(employee);
    }

}
