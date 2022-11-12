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
    private String fullName;
    private String email;
    private Long id;
    private String phoneNumber;

    @Override
    public String toString() {
        return fullName + " " + email + " " + id + " " + phoneNumber;
    }
}
