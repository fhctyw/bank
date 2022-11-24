package bank.annotations;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = CodeCurrencyValidator.class)
public @interface CodeCurrency {
    String message() default "Invalid code currency";
}
