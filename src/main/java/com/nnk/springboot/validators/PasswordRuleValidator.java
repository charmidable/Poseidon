package com.nnk.springboot.validators;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.passay.*;


public class PasswordRuleValidator implements ConstraintValidator<Password, String>
{

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // at least 8 characters
                new LengthRule(8, 125),

                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),

                // no whitespace
                new WhitespaceRule()

        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = messages.stream()
                .collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

//    private static final int MIN_COMPLEX_RULES = 2;
//    private static final int MAX_REPETITIVE_CHARS = 3;
//    private static final int MIN_SPECIAL_CASE_CHARS = 1;
//    private static final int MIN_UPPER_CASE_CHARS = 1;
//    private static final int MIN_LOWER_CASE_CHARS = 1;
//    private static final int MIN_DIGIT_CASE_CHARS = 1;
//
//    @Override
//    public boolean isValid(String password, ConstraintValidatorContext context)
//    {
//        List<Rule> passwordRules = new ArrayList<>();
//        passwordRules.add(new LengthRule(8, 30));
//        CharacterCharacteristicsRule characterCharacteristicsRule =
//                new CharacterCharacteristicsRule(MIN_COMPLEX_RULES,
//                        new CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS),
//                        new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
//                        new CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
//                        new CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS));
//        passwordRules.add(characterCharacteristicsRule);
//        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETITIVE_CHARS));
//        PasswordValidator passwordValidator = new PasswordValidator(passwordRules);
//        PasswordData passwordData = new PasswordData(password);
//        RuleResult ruleResult = passwordValidator.validate(passwordData);
//        return ruleResult.isValid();
//    }
}