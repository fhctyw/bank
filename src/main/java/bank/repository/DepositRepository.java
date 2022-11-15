package bank.repository;

import bank.db.FileDeposit;
import bank.entity.Deposit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class DepositRepository {
    final List<Deposit> deposits = new ArrayList<>();
    private Long id = 0L;
    final FileDeposit fileDeposit = new FileDeposit(this);

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void add(final Deposit deposit) {
        final Deposit finalDeposit = new Deposit();
        finalDeposit.setClient(deposit.getClient());
        finalDeposit.setAmount(deposit.getAmount());
        finalDeposit.setDepositId(deposit.getDepositId());
        finalDeposit.setCardId(deposit.getCardId());
        finalDeposit.setConsultantId(deposit.getConsultantId());
        finalDeposit.setPutTime(deposit.getPutTime());
        finalDeposit.setWithdrawTime(deposit.getWithdrawTime());
        finalDeposit.setPercentage(deposit.getPercentage());

        fileDeposit.write();
    }

    public Deposit findById(final Long id) {
        return deposits.stream()
                .filter(e -> e.getDepositId().equals(id))
                .findFirst()
                .orElseThrow();
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

        fileDeposit.write();
    }

    public void deleteDeposit(final Long id) {
        deposits.removeIf(e->e.getDepositId().equals(id));

        fileDeposit.write();
    }
}
