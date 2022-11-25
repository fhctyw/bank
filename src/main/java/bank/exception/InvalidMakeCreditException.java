package bank.exception;

public class InvalidMakeCreditException extends ServiceException {
    public InvalidMakeCreditException(int code, String message, String details) {
        super(code, message, details);
    }

    public InvalidMakeCreditException(int code, String message) {
        super(code, message);
    }

    public InvalidMakeCreditException(String message, String details) {
        super(message, details);
    }

    public InvalidMakeCreditException(String message) {
        super(message);
    }
}
