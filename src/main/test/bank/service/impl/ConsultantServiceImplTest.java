package bank.service.impl;

import bank.Bank;
import bank.dto.ConsultantDTO;
import bank.entity.Consultant;
import bank.mapper.MapperConsultant;
import bank.repository.ConsultantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bank.class)
@AutoConfigureMockMvc
public class ConsultantServiceImplTest {
    @Test
    public void test_create() throws Exception {
        final MapperConsultant mapperConsultant = Mockito.mock(MapperConsultant.class);
        final ConsultantRepository consultantRepository = Mockito.mock(ConsultantRepository.class);
        final ConsultantServiceImpl consultantService = new ConsultantServiceImpl(mapperConsultant, consultantRepository);

        final String fullName = "David Thomson";
        final String email = "davidthom144@gmail.com";
        final String phoneNumber = "380 95 231 95 04";

        final ConsultantDTO consultantDTO = new ConsultantDTO();
        consultantDTO.setId(null);
        consultantDTO.setFullName(fullName);
        consultantDTO.setEmail(email);
        consultantDTO.setPhoneNumber(phoneNumber);

        final Consultant consultant = new Consultant();
        consultant.setId(1L);
        consultant.setFullName(fullName);
        consultant.setEmail(email);
        consultant.setPhoneNumber(phoneNumber);

        when(mapperConsultant.toEntity(any())).thenCallRealMethod();
        when(mapperConsultant.toDto(any())).thenCallRealMethod();
        when(consultantRepository.findById(any())).thenReturn(consultant);

        final ConsultantDTO resultConsultant = consultantService.create(consultantDTO);

        assertEquals(resultConsultant.getFullName(), fullName);
        assertEquals(resultConsultant.getEmail(), email);
        assertEquals(resultConsultant.getPhoneNumber(), phoneNumber);
    }

    @Test
    public void test_read() throws Exception {
        final MapperConsultant mapperConsultant = Mockito.mock(MapperConsultant.class);
        final ConsultantRepository consultantRepository = Mockito.mock(ConsultantRepository.class);
        final ConsultantServiceImpl consultantService = new ConsultantServiceImpl(mapperConsultant, consultantRepository);

        final String fullName = "David Thomson";
        final String email = "davidthom144@gmail.com";
        final String phoneNumber = "380 95 231 95 04";

        final Long fountId = 1L;

        final Consultant consultant = new Consultant();
        consultant.setId(fountId);
        consultant.setFullName(fullName);
        consultant.setEmail(email);
        consultant.setPhoneNumber(phoneNumber);

        when(mapperConsultant.toDto(any())).thenCallRealMethod();
        when(consultantRepository.findById(fountId)).thenReturn(consultant);

        consultantService.read(fountId);

        assertEquals(consultant.getFullName(), fullName);
        assertEquals(consultant.getEmail(), email);
        assertEquals(consultant.getPhoneNumber(), phoneNumber);
        assertEquals(consultant.getId(), fountId);
    }

    @Test
    public void test_put() throws Exception {
        final MapperConsultant mapperConsultant = Mockito.mock(MapperConsultant.class);
        final ConsultantRepository consultantRepository = Mockito.mock(ConsultantRepository.class);
        final ConsultantServiceImpl consultantService = new ConsultantServiceImpl(mapperConsultant, consultantRepository);

        final Long id = 1L;
        final String fullName = "David Thomson";
        final String email = "davidthom144@gmail.com";
        final String phoneNumber = "380 95 231 95 04";

        final ConsultantDTO consultantDTO = new ConsultantDTO();
        consultantDTO.setId(id);
        consultantDTO.setFullName(fullName);
        consultantDTO.setEmail(email);
        consultantDTO.setPhoneNumber(phoneNumber);

        final String newFullName = "David Simon";
        final String newEmail = "sim33@ukr.ua";

        final Consultant newConsultant = new Consultant();
        newConsultant.setId(id);
        newConsultant.setFullName(newFullName);
        newConsultant.setEmail(newEmail);
        newConsultant.setPhoneNumber(phoneNumber);

        final ConsultantDTO newConsultantDTO = new ConsultantDTO();
        newConsultantDTO.setId(id);
        newConsultantDTO.setFullName(newFullName);
        newConsultantDTO.setEmail(newEmail);
        newConsultantDTO.setPhoneNumber(phoneNumber);

        when(mapperConsultant.toEntity(any())).thenCallRealMethod();
        when(mapperConsultant.toDto(any())).thenCallRealMethod();
        when(consultantRepository.findById(id)).thenReturn(newConsultant);

        final ConsultantDTO resultConsultant = consultantService.update(consultantDTO);

        assertEquals(resultConsultant.getFullName(), newFullName);
        assertEquals(resultConsultant.getEmail(), newEmail);
        assertEquals(resultConsultant.getPhoneNumber(), phoneNumber);
        assertEquals(resultConsultant.getId(), id);
    }
}
