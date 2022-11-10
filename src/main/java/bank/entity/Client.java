package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @NotBlank
    private String fullName;
    @Email
    private String email;
    @NotNull
    private Long id;
    @NotBlank
    private String phoneNumber;
}
