package bank.service.impl;

import bank.Bank;
import bank.dto.DepositDTO;
import bank.entity.Deposit;
import bank.mapper.MapperDeposit;
import bank.repository.DepositRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bank.class)
@AutoConfigureMockMvc
public class DepositServiceImplTest {
    @TEST
    public void test_create() throws Exception {
        final MapperDeposit mapperDeposit = Mockito.mock(MapperDeposit.class);
        final DepositRepository depositRepository = Mockito.mock(DepositRepository.class);
        final DepositServiceImpl depositService = new DepositServiceImpl(mapperDeposit, depositRepository);

        final String amount = "20000";
        final String id = "1";
        final String cardId = "1";
        final String putTime = "2012-12-10T10:09:10";
        final String withdrawTime = "2013-12-10T10:09:10";
        final String percentage = "0.3";

        final DepositDTO depositDTO = new DepositDTO();
        depositDTO.setAmount(amount);
        depositDTO.setId(Id);
        depositDTO.setCardId(cardId);
        depositDTO.setPutTime(putTime);
        depositDTO.setWithdrawTime(withdrawTime);
        depositDTO.setPercentage(percentage);

        final Deposit deposit = new Deposit();
        deposit.setAmount(amount);
        deposit.setId(1L);
        deposit.setCardId(cardId);
        deposit.setPutTime(putTime);
        deposit.setWithdrawTime(withdrawTime);
        deposit.setPercentage(percentage);

        when(mapperDeposit.toEntity(any())).thenCallRealMethod();
        when(mapperDeposit.toDto(any())).thenCallRealMethod();
        when(depositRepository.findById(any())).thenReturn(consultant);

        final DepositDTO resultDeposit = depositService.create(depositDTO);

        assertEquals(resultDeposit.getAmount(), amount);
        assertEquals(resultDeposit.getCardId(), cardId);
    }
}