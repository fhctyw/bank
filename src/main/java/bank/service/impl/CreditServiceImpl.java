package bank.service.impl;

import bank.dto.CreditDTO;
import bank.entity.Credit;
import bank.mapper.MapperCredit;
import bank.repository.CardRepository;
import bank.repository.CreditRepository;
import bank.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    final MapperCredit mapperCredit = new MapperCredit();
    @Autowired
    final CreditRepository creditRepository = new CreditRepository();


    @Override
    public void getCredit(){

    }

    @Override
    public void create(final CreditDTO dto){
        final Credit credit = mapperCredit.toEntity(dto);
        creditRepository.add(credit);
    }

    @Override
    public CreditDTO read(final Long id){
        return mapperCredit.toDTO(creditRepository.findById(id));
    }

    @Override
    public void update(final CreditDTO dto){
        creditRepository.setCredits(dto.getId(), mapperCredit.toEntity(dto));
    }

    @Override
    public void delete(final Long id){
        creditRepository.deleteCredit(id);
    }

}
