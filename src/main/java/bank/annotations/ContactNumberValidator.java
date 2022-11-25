package bank.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumber, String> {

    @Override
    public void initialize(final ContactNumber contactNumber) {
    }

    @Override
    public boolean isValid(final String contactField, final ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9]+")
                && (contactField.length() > 8) && (contactField.length() < 14);
    }

}

