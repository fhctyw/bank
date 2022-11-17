package bank.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServiceExceptionDTO {
    private int code;
    private String message;
    private String details;

    public ServiceExceptionDTO(final int code, final String message) {
        this(code, message, "");
    }
    public ServiceExceptionDTO(final ServiceException ex) {
        this(ex.getCode(), ex.getMessage(), ex.getDetails());
    }
}

