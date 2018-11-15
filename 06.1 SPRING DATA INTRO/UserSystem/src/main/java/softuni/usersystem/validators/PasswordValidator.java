package softuni.usersystem.validators;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {
    private int minLength;
    private int maxLength;
    private boolean containsDigit;
    private boolean containsUppercase;
    private boolean containsLowercase;
    private boolean containsSpecialSymbols;


    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.containsDigit = password.containsDigit();
        this.containsLowercase = password.containsLowercase();
        this.containsUppercase = password.containsUppercase();
        this.containsSpecialSymbols = password.containsSpecialSymbol();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password.length() < this.minLength || password.length() > this.maxLength) {
            return false;
        }

        if (this.containsLowercase && !password.matches("[a-z]")) {
            return false;
        }

        if (this.containsUppercase && !password.matches("[A-Z]")) {
            return false;
        }

        if (this.containsDigit && !password.matches("[0-9]")) {
            return false;
        }

        if (this.containsSpecialSymbols && !password.matches("[!@#$%^&*()_+<>?]")) {
            return false;
        }

        return true;
    }
}
