package softuni.usersystem.validators;

import org.springframework.stereotype.Component;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
    String message() default "Email is invalid!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
