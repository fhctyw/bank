package bank.exception;

public class TransferSelfTransactionException extends ServiceException {
    public TransferSelfTransactionException(final int code, final String message, final String details) {
        super(code, message, details);
    }

    public TransferSelfTransactionException(final int code, final String message) {
        super(code, message);
    }

    public TransferSelfTransactionException(final String message, final String details) {
        super(message, details);
    }

    public TransferSelfTransactionException(final String message) {
        super(message);
    }
}
