package bank.repository;

import bank.db.FileConsultant;
import bank.entity.Consultant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsultantRepository {
    private final List<Consultant> consultants = new ArrayList<>();
    private Long id = 0L;

    final FileConsultant fileConsultant = new FileConsultant(this);

    public ConsultantRepository() {
        fileConsultant.read();
    }

    public List<Consultant> getConsultants() {
        return consultants;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void add(final Consultant consultant) {
        final Consultant finalConsultant = new Consultant();
        finalConsultant.setId(++id);
        finalConsultant.setFullName(consultant.getFullName());
        consultants.add(finalConsultant);

        fileConsultant.write();
    }

    public Consultant findById(final Long id) {
        return consultants.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow();
    }

    public void setConsultant(final Long id, final Consultant consultant) {
        final Consultant c = findById(id);
        c.setId(consultant.getId());
        c.setFullName(consultant.getFullName());

        fileConsultant.write();
    }

    public void deleteConsultant(final Long id) {
        consultants.removeIf(e->e.getId().equals(id));

        fileConsultant.write();
    }
}
