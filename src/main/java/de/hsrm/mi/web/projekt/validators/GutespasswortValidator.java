package de.hsrm.mi.web.projekt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GutespasswortValidator implements ConstraintValidator<GutesPasswort,String> {

    protected String passwort;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if (value.contains("17") || value.toUpperCase().contains("SIEBZEHN") || value.isBlank())  {
            return true;
        }
        return false;
    }

}
    

