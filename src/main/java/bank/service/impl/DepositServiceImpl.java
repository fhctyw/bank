package bank.service.impl;

import bank.dto.DepositDTO;
import bank.entity.Deposit;
import bank.exception.InvalidDeposit;
import bank.mapper.MapperDeposit;
import bank.repository.DepositRepository;
import bank.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    @Autowired
    final MapperDeposit mapperDeposit = new MapperDeposit();

    @Autowired
    final DepositRepository depositRepository = new DepositRepository();

    @Override
    public void create(DepositDTO dto) {
        final Deposit deposit = mapperDeposit.toEntity(dto);
        depositRepository.add(deposit);
    }

    @Override
    public DepositDTO read(final Long id) {
        final Deposit deposit = depositRepository.get(id);
        final DepositDTO dto = mapperDeposit.toDTO(deposit);
        return dto;
    }

    @Override
    public void update(final DepositDTO dto) {
        depositRepository.setDeposit(dto.getId(), mapperDeposit.toEntity(dto));
    }

    @Override
    public void delete(final Long id) {
        depositRepository.deleteDeposit(id);
    }

    @Override
    public List<DepositDTO> getAll() {
        return depositRepository.getDeposits().stream().map(mapperDeposit::toDTO).collect(Collectors.toList());
    }

//    public double percentage() {
//        final DepositDTO dto = new DepositDTO();
//        final BigDecimal p;
//        final double percentage;
//        if (dto.getPercentage() == 0.0) {
//            InvalidDeposit depositError = new InvalidDeposit("Invalid percentage");
//        } else {
//            p = BigDecimal.valueOf(dto.getPercentage()).multiply(dto.getAmount());
//            dto.setAmount(p);
//
//        }
//        return
//    }

//    public DepositDTO putDeposit(final DepositDTO dto) {
//
//        if (dto.getAmount().signum() <= 0) {
//            InvalidDeposit depositError = new InvalidDeposit("Invalid Deposit Amount");
//            System.out.println(depositError.getMessage());
//        } else {
//        }
//    }
}
