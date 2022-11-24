package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeCreditResponseDTO {
    @NotNull
    private CreditDTO creditDTO;
    @NotNull
    private CardDTO cardDTO;
}
