package com.javierfernandez.springboot.form.app.validation;


import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdentidicadorRegexValidador.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface IdentificadorRegex {

    String message() default "Identificador invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
