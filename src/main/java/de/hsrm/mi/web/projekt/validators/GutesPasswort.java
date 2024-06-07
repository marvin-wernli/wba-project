package de.hsrm.mi.web.projekt.validators;

import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=GutespasswortValidator.class)
public @interface GutesPasswort {
    Class<? extends Payload>[] payload() default { };

    Class<?>[] groups() default { };

    String message() default "String entweder “17” oder das Wort “siebzehn” bitte danke :=)";
}