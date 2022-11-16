package bank.repository;

import bank.dto.DepositDTO;
import bank.entity.Deposit;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DepositRepository {

    private final String source = "deposit.txt";
    private List<Deposit> deposits = new ArrayList<>();
    private Long id = 0L;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate ignoreUntil;
    public List<Deposit> getDeposits() {
        return deposits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            deposits = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<List<Deposit>>() {
            });

            if (deposits == null) {
                deposits = new ArrayList<>();
                return;
            }

            final long maxId = deposits.stream().mapToLong(Deposit::getDepositId).max().orElse(1);

            id = maxId;

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, JacksonUtil.serialize(deposits), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Deposit deposit) {
        final Deposit finalDeposit = new Deposit();
        finalDeposit.setClient(deposit.getClient());
        finalDeposit.setAmount(deposit.getAmount());
        finalDeposit.setDepositId(++id);
        finalDeposit.setCardId(deposit.getCardId());
        finalDeposit.setConsultantId(deposit.getConsultantId());
        finalDeposit.setPutTime(deposit.getPutTime());
        finalDeposit.setWithdrawTime(deposit.getWithdrawTime());
        finalDeposit.setPercentage(deposit.getPercentage());
        deposits.add(finalDeposit);
    }

    public void update(final Long id, final DepositDTO dto) {
        final Deposit update = findById(id);
        update.setClient(dto.getClient());
        update.setAmount(dto.getAmount());
        update.setDepositId(dto.getDepositId());
        update.setCardId(dto.getCardId());
        update.setConsultantId(dto.getConsultantId());
        update.setPutTime(dto.getPutTime());
        update.setWithdrawTime(dto.getWithdrawTime());
        update.setPercentage(dto.getPercentage());
    }

    public Deposit findById(final Long id) {
        return deposits.stream()
                .filter(e -> e.getDepositId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public Deposit get(final Long id) {
        return findById(id);
    }

    public void setDeposit(final Long id, final Deposit deposit) {
        final Deposit c = findById(id);
        c.setClient(deposit.getClient());
        c.setClient(deposit.getClient());
        c.setDepositId(deposit.getDepositId());
        c.setCardId(deposit.getCardId());
        c.setConsultantId(deposit.getConsultantId());
        c.setPutTime(deposit.getPutTime());
        c.setWithdrawTime(deposit.getWithdrawTime());
        c.setPercentage(deposit.getPercentage());
    }

    public void deleteDeposit(final Long id) {
        deposits.removeIf(e->e.getDepositId().equals(id));
    }
}
