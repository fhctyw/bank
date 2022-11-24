package bank.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CodeCurrencyValidator implements ConstraintValidator<CodeCurrency, String> {
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value != null && value.length() >= 3 && value.equals(value.toUpperCase());
    }
}
