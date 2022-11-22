package bank.dto;

import bank.annotations.ContactNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultantDTO {
    private Long id;
    @NotBlank
    private String fullName;
    @Email
    private String email;
    @NotBlank
    @ContactNumber
    private String phoneNumber;
}
