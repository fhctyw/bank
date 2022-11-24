package bank.repository;

import bank.dto.DepositDTO;
import bank.entity.Deposit;
import bank.exception.ServiceException;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

@Repository
public class DepositRepository {

    private final String source = "deposit.txt";
    private List<Deposit> deposits = new ArrayList<>();
    private Long id = 0L;

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @PostConstruct
    public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            deposits = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<>() {
            });

            if (deposits == null) {
                deposits = new ArrayList<>();
                return;
            }

            final long maxId = deposits.stream().mapToLong(Deposit::getId).max().orElse(1);

            id = maxId;

        } catch (final IOException e) {
            System.out.println("file " + source + " doesn't exist");
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(deposits)), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Deposit deposit) {
        final Deposit finalDeposit = new Deposit();
        finalDeposit.setAmount(deposit.getAmount());
        finalDeposit.setId(++id);
        finalDeposit.setCardId(deposit.getCardId());
        finalDeposit.setPutTime(deposit.getPutTime());
        finalDeposit.setWithdrawTime(deposit.getWithdrawTime());
        finalDeposit.setPercentage(deposit.getPercentage());
        deposits.add(finalDeposit);
    }

    public void update(final Long id, final DepositDTO dto) {
        final Deposit update = findById(id);
        update.setAmount(dto.getAmount());
        update.setId(dto.getId());
        update.setCardId(dto.getCardId());
        update.setPutTime(dto.getPutTime());
        update.setWithdrawTime(dto.getWithdrawTime());
        update.setPercentage(dto.getPercentage());
    }

    public Deposit findById(final Long id) {
        return deposits.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException("No such id when finding"));
    }

    public Deposit get(final Long id) {
        return findById(id);
    }

    public void setDeposit(final Long id, final Deposit deposit) {
        final Deposit c = findById(id);
        c.setAmount(deposit.getAmount());
        c.setId(deposit.getId());
        c.setCardId(deposit.getCardId());
        c.setPutTime(deposit.getPutTime());
        c.setWithdrawTime(deposit.getWithdrawTime());
        c.setPercentage(deposit.getPercentage());
    }

    public void deleteDeposit(final Long id) {
        setDeposits(deposits.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList()));
    }

}
