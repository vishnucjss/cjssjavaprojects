package com.dabbawala.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class ValidateAge1 implements
        ConstraintValidator<ValidateAge, String> {

    @Override
    public void initialize(ValidateAge dateOfBirth) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {


        if(!(contactField.isEmpty()||contactField==null)&& Pattern.matches("[1-2][0-9]{3}[-]{1}[0-1][0-9][-]{1}[0-3][0-9]",contactField ))
        {
        LocalDate today = LocalDate.now();

        LocalDate birthday =LocalDate.parse(contactField);

        Period p = Period.between(birthday, today);
            return ((p.getYears()>18)||(p.getMonths()==18)&&(p.getMonths()>1||p.getDays()>1))?true:false;
        }


         else
         return false;


    }

}
