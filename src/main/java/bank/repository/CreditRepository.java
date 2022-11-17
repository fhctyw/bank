package bank.repository;

import bank.entity.Credit;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class CreditRepository {

    private final String source = "credits.txt";
    private List<Credit> credits = new ArrayList<>();
    private Long id;

    @PostConstruct
    public void postConstructorId(){
        final Path file = Paths.get(source);
        try{
            credits = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16),
                    new TypeReference<List<Credit>>() {
                    });
            if(credits == null){
                credits = new ArrayList<>();
                return;
            }
        }catch (final IOException o){
            throw new RuntimeException(o);
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get(source);
        try{
            Files.writeString(file, JacksonUtil.serialize(credits),StandardCharsets.UTF_16);
        }catch (final IOException o){
            o.printStackTrace();
        }
    }


    public void add(final Credit credit) {
        final Credit creditFinal = new Credit();
        creditFinal.setId(++id);
        creditFinal.setPercent(credit.getPercent());
        creditFinal.setAmount(credit.getAmount());
        creditFinal.setIdClient(credit.getIdClient());
        creditFinal.setFirstDate(new Date());
        creditFinal.setSecondDate(credit.getSecondDate());
    }

    public Credit findById(final Long id) {
        return credits.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow();
    }

    public void setCredits(final Long id, final Credit credit) {
        final Credit c = findById(id);
        c.setPercent(credit.getPercent());
        c.setAmount(credit.getAmount());
    }

    public void deleteCredit(final Long id) {
        credits.removeIf(e -> e.getId().equals(id));
    }


}
