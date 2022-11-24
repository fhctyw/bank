package bank.exception;

public class InvalidDepositException extends ServiceException {
    public InvalidDepositException(final int code, final String message, final String details) {
        super(code, message, details);
    }

    public InvalidDepositException(final int code, final String message) {
        super(code, message);
    }

    public InvalidDepositException(final String message, final String details) {
        super(message, details);
    }

    public InvalidDepositException(final String message) {
        super(message);
    }
}
