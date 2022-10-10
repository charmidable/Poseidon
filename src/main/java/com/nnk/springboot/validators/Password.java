package com.nnk.springboot.validators;



import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordRuleValidator.class)
public @interface Password
{
    String message() default "Password must adhere to the specified rules: - At least a length of 8. - At least 1 upper case character. - At least 1 special character. - At least 1 digit character.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}