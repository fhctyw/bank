package bank.repository;

import bank.entity.Consultant;
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
public class ConsultantRepository {
    private final String source = "consultants.txt";
    private List<Consultant> consultants = new ArrayList<>();
    private Long id = 0L;

    public Long getId() {
        return id;
    }

    public List<Consultant> getConsultants() {
        return consultants;
    }

    public void setConsultants(final List<Consultant> consultants) {
        this.consultants = consultants;
    }

    @PostConstruct
    public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            consultants = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<>() {
            });

            if (consultants == null) {
                consultants = new ArrayList<>();
                return;
            }

            final long maxId = consultants.stream().mapToLong(Consultant::getId).max().orElse(1);

            id = maxId + 1;

        } catch (final IOException ex) {
            System.out.println("file " + source + " doesn't exist");
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(consultants)), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Consultant consultant) {
        final Consultant finalConsultant = new Consultant();
        finalConsultant.setId(++id);
        finalConsultant.setFullName(consultant.getFullName());
        finalConsultant.setEmail(consultant.getEmail());
        finalConsultant.setPhoneNumber(consultant.getPhoneNumber());
        consultants.add(finalConsultant);
    }

    public Consultant findById(final Long id) {
        return consultants.stream().filter(e -> e.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ServiceException("No such id when finding"));
    }

    public void set(final Long id, final Consultant consultant) {
        final Consultant c = findById(id);
        c.setId(consultant.getId());
        c.setFullName(consultant.getFullName());
        c.setPhoneNumber(consultant.getPhoneNumber());
        c.setEmail(consultant.getEmail());
    }

    public void delete(final Long id) {
        setConsultants(consultants.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList()));
    }
}
