package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultant {
    private Long id;
    private String fullName;

    @Override
    public String toString() {
        return id + " " + fullName;
    }
}
