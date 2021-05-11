package lt.vu.infrastructure.validators;

import lt.vu.persistence.orm.entities.OrderStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidOrderStatusValidator implements ConstraintValidator<ValidOrderStatus, String> {

    @Override
    public boolean isValid(String enumValue, ConstraintValidatorContext constraintValidatorContext) {
        try {
            OrderStatus.valueOf(enumValue);

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public void initialize(ValidOrderStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
