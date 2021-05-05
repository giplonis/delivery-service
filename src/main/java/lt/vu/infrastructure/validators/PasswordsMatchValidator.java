package lt.vu.infrastructure.validators;

import lt.vu.web.api.v1.dto.security.PasswordDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, PasswordDTO> {

    @Override
    public boolean isValid(PasswordDTO postRegisterDTO, ConstraintValidatorContext constraintValidatorContext) {
        return postRegisterDTO.getPassword().equalsIgnoreCase(postRegisterDTO.getPasswordConfirm());
    }

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
