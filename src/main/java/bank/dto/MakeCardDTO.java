package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeCardDTO {
@NotNull
        private Long IdClient;
        @NotNull
        private String codeCurrency;
}
