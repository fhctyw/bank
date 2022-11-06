package bank.repository;

import bank.entity.Consultant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsultantRepository {
    final  List<Consultant> consultants = new ArrayList<>();
    int id = 0;

    public ConsultantRepository() {
        add(new Consultant(0, "Adam Whitman"));
        add(new Consultant(0, "Roman Blue"));
    }

    public void add(final Consultant consultant) {
        final Consultant finalConsultant = new Consultant();
        finalConsultant.setId(id++);
        finalConsultant.setFullName(consultant.getFullName());
        consultants.add(finalConsultant);
    }
}
