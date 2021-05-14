package lt.vu.infrastructure.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidOrderStatusValidator.class)
public @interface ValidOrderStatus {

    String message() default "The provided value is not a valid order status";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
