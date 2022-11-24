package bank.exception;

public class InvalidPayCreditException extends ServiceException {

    public InvalidPayCreditException(final int code, final String message, final String details) {
        super(code, message, details);
    }

    public InvalidPayCreditException(final int code, final String message) {
        super(code, message);
    }

    public InvalidPayCreditException(final String message, final String details) {
        super(message, details);
    }

    public InvalidPayCreditException(final String message) {
        super(message);
    }
}
