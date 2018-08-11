package com.siseth.spring.alpha2.validators;

import com.siseth.spring.alpha2.DTO.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation){
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDTO userDTO = (UserDTO) obj;

        return userDTO.getPassword().equals(userDTO.getRepeatPassword());
    }
}