package bank.exception;

public class InvalidDeposit {
    String errorMessage;
    public InvalidDeposit(String message)
    {
        errorMessage = message;
    }
    public String getMessage()
    {
        return errorMessage;
    }
}
