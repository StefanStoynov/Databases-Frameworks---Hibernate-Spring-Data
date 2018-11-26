package productshop.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtilImpl implements ValidatorUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <O> boolean isValid(O object) {
        return this.validator.validate(object).size() == 0;
    }

    @Override
    public <O> Set<ConstraintViolation<O>> violations(O object) {
        return this.validator.validate(object);
    }
}
