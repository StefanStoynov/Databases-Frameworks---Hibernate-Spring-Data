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
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "Invalid password!";
    int minLength();
    int maxLength();
    boolean containsDigit() default false;
    boolean containsLowercase() default false;
    boolean containsUppercase() default false;
    boolean containsSpecialSymbol() default false;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
