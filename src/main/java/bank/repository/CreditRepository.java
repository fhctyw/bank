package bank.repository;

import bank.entity.Credit;
import bank.exception.ServiceException;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class CreditRepository {

    private final String source = "credits.txt";
    private List<Credit> credits = new ArrayList<>();
    private Long id = 0L;


    public List<Credit> getAll(){
        return credits;
    }

    public Long getId() {
        return id;
    }
    public void setId(){this.id = id;}

    @PostConstruct
    public void postConstructor(){
        final Path file = Paths.get(source);
        try{
            credits = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16),
                    new TypeReference<List<Credit>>() {
                    });
            if(credits == null){
                credits = new ArrayList<>();
                return;
            }

            final long maxId = credits.stream().mapToLong(Credit::getId).max().orElse(1);
            id = maxId;
        }catch (final IOException o){
            System.out.println("File " + source + " doesn't exist");;
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
        creditFinal.setIdClient(credit.getIdClient());
        creditFinal.setAmount(credit.getAmount());
        creditFinal.setCardNumber(credit.getCardNumber());
        creditFinal.setPercent(credit.getPercent());
        creditFinal.setCreateTime(credit.getCreateTime());
        creditFinal.setPayTime(credit.getPayTime());
        credits.add(creditFinal);
    }

    public Credit findById(final Long id) {
        return credits.stream().filter(e -> e.getId().equals(id)).
                findFirst()
                .orElseThrow(() -> new ServiceException("No such id when finding"));
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
