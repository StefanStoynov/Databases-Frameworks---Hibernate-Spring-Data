package carshop.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {

    <O> boolean isValid(O object);

    <O> Set<ConstraintViolation<O>> violations(O object);

}
