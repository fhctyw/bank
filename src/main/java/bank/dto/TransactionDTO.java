package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
        @NotNull
        private Long id;
        @NotNull
        private LocalDateTime time;
        @NotNull
        private BigDecimal amount_of_transaction;
        @NotNull
        private Long idReceiver;
        @NotNull
        private Long idSender;
        @NotBlank
        private String message;
}
