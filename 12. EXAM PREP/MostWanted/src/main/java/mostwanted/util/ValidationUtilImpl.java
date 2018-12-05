package mostwanted.util;

import javax.validation.Validator;

public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).size() == 0;
    }
}
