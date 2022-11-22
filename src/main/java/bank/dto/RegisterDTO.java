package bank.dto;

import bank.annotations.ContactNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotBlank
    private String fullName;
    @Email
    private String email;
    @NotBlank
    @ContactNumber
    private String phoneNumber;
    @NotBlank
    private String codeCurrency;
}
