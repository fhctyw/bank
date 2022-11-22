package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    @NotNull
    private LocalDateTime time;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private Long idReceiver;
    @NotNull
    private Long idSender;
    @NotBlank
    private String message;
}
