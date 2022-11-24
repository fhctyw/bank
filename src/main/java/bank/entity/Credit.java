package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    private Long idConsultant;
    private Long id;
    private Long idClient;
    private BigDecimal amount;
    private int percent;
    private LocalDateTime createDate;
    private LocalDateTime deadlineDate;

    @Override
    public String toString() {
        return id + " " + idConsultant + " " + idClient + " " + amount + " " + percent +
                " " + createDate + " " + deadlineDate;
    }
}
