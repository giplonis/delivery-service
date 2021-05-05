package lt.vu.infrastructure.validators;

import lt.vu.web.api.v1.dto.security.PostRegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, PostRegisterDTO> {

    @Override
    public boolean isValid(PostRegisterDTO postRegisterDTO, ConstraintValidatorContext constraintValidatorContext) {
        return postRegisterDTO.getPassword().equalsIgnoreCase(postRegisterDTO.getPasswordConfirm());
    }

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
