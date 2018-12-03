package carshop.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


public class ValidatorUtilImpl implements ValidatorUtil {

    private Validator validator;

    public ValidatorUtilImpl() {
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
