package com.javierfernandez.springboot.form.app.validation;


import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequeridoValidador implements ConstraintValidator <Requerido, String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        /*value.isEmpty()|| value.isBlank() es lo mismo que el string uttils*/
        if (value==null|| !StringUtils.hasText(value)){
            return false;
        }
        return true;
    }
}
