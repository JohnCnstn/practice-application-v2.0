package classes.data.validation.impl;

import classes.data.dto.StudentDto;
import classes.data.validation.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return passwordMatches(o);
    }

    private boolean passwordMatches(Object o) {
        StudentDto user = (StudentDto) o;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
