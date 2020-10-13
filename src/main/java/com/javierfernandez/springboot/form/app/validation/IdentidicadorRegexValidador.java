package com.javierfernandez.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentidicadorRegexValidador implements ConstraintValidator <IdentificadorRegex, String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext Context) {

        if (value.matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")){
            return true;
        }
        return false;
    }
}
