package bank.repository;

import bank.dto.DepositDTO;
import bank.entity.Deposit;
import bank.exception.InvalidDeposit;
import bank.exception.ServiceException;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Repository
public class DepositRepository {

    private final String source = "deposit.txt";
    private List<Deposit> deposits = new ArrayList<>();
    private Long id = 0L;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate ignoreUntil;

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
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
            Files.writeString(file, JacksonUtil.serialize(deposits), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Deposit deposit) {
        final Deposit finalDeposit = new Deposit();
        finalDeposit.setAmount(deposit.getAmount());
        finalDeposit.setId(++id);
        finalDeposit.setCardId(deposit.getCardId());
        finalDeposit.setConsultantId(deposit.getConsultantId());
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
        update.setConsultantId(dto.getConsultantId());
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
        c.setId(deposit.getId());
        c.setCardId(deposit.getCardId());
        c.setConsultantId(deposit.getConsultantId());
        c.setPutTime(deposit.getPutTime());
        c.setWithdrawTime(deposit.getWithdrawTime());
        c.setPercentage(deposit.getPercentage());
    }

    public void deleteDeposit(final Long id) {
        setDeposits(deposits.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList()));
    }

    /*public double percentage(final double percentage, BigDecimal amount) {
        if (percentage == 0.0) {
            InvalidDeposit depositError = new InvalidDeposit("Invalid percentage");
        } else {
            BigDecimal amt =  new BigDecimal(percentage).multiply(amount);
        }

    }*/

    public Deposit putDeposit(BigDecimal balance, BigDecimal amount, final LocalDateTime putTime, final LocalDateTime withdrawTime) {
        final Deposit deposit = new Deposit();

        Scanner s = new Scanner(System.in);

        System.out.print("Enter the amount to be deposited: ");
        amount = s.nextBigDecimal();

        System.out.println("For which term?");
        LocalDateTime term = LocalDateTime.parse(putTime.toString());
        LocalDateTime term1 = LocalDateTime.parse(withdrawTime.toString());
        System.out.println("Term of Deposit = [" + term.until(term1, ChronoUnit.MONTHS) + "];");

        if (amount.signum() <= 0) {
            InvalidDeposit depositError = new InvalidDeposit("Invalid Deposit Amount");
            System.out.println(depositError.getMessage());
        } else {
            balance = balance.add(amount);
            System.out.println("Amount deposited Successfully");
            System.out.println(" ");
            System.out.println("Total Balance: [" + balance + "]; Deposit beginning time [" + putTime + "];");
            System.out.println(" ");
        }

        return deposit;
    }

    public Deposit withdrawDeposit(BigDecimal balance, final BigDecimal amount, final LocalDateTime putTime, final LocalDateTime withdrawTime) {
        final Deposit deposit = new Deposit();
        System.out.println(" ");
        if (amount.signum() < 0) {
            InvalidDeposit depositError = new InvalidDeposit("Invalid Withdrawal Amount");
            System.out.println(depositError.getMessage());
        } else if (amount.signum() == 0){
            continueDeposit(withdrawTime);
        } else {
            balance = balance.subtract(amount);
            System.out.println("Please Collect your [" + amount + "];");
            System.out.println(" ");
            System.out.println("Available Balance: [" + balance + "];");
            System.out.println(" ");
        }
        return deposit;
    }

    public Deposit continueDeposit(final LocalDateTime withdrawTime) {
        final Deposit deposit = new Deposit();
        return deposit;
    }
}
