package com.startup.vanguard.validation.validator;

import com.startup.vanguard.validation.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if (password == null) {
            buildMessage(context, "A senha não pode ser nula");
            return false;
        }

        if (password.length() < 8 || password.length() > 18) {
            buildMessage(context, "A senha deve conter entre 8 e 18 caracteres");
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            buildMessage(context, "A senha deve conter pelo menos uma letra maiúscula");
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            buildMessage(context, "A senha deve conter pelo menos uma letra minúscula");
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            buildMessage(context, "A senha deve conter pelo menos um número");
            return false;
        }

        if (!password.matches(".*[@$!%*?#&].*")) {
            buildMessage(context, "A senha deve conter pelo menos um caractere especial (@$!%*?#&)");
            return false;
        }

        return true;
    }

    private void buildMessage(ConstraintValidatorContext context, String msg) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
    }
}