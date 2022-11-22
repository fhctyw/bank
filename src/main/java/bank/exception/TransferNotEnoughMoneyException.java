package bank.exception;

import java.math.BigDecimal;

public class TransferNotEnoughMoneyException extends ServiceException {

    public TransferNotEnoughMoneyException(final int code, final String message, final String details) {
        super(code, message, details);
    }

    public TransferNotEnoughMoneyException(final int code, final String message) {
        super(code, message);
    }

    public TransferNotEnoughMoneyException(final String message, final String details) {
        super(message, details);
    }

    public TransferNotEnoughMoneyException(final String message) {
        super(message);
    }
}
