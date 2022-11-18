package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultant {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
}
