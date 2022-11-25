package bank.exception;

public class NoTransactionsException extends ServiceException {
    public NoTransactionsException(final int code, final String message, final String details) {
        super(code, message, details);
    }

    public NoTransactionsException(final int code, final String message) {
        super(code, message);
    }

    public NoTransactionsException(final String message, final String details) {
        super(message, details);
    }

    public NoTransactionsException(final String message) {
        super(message);
    }
}
