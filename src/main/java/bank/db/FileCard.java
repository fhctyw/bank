package bank.db;

import bank.entity.Card;
import bank.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileCard implements BankDB {
    final private String name = "cards.txt";

    @Autowired
    final private CardRepository cardRepository;

    public FileCard(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void write(){
        try{
            final BufferedWriter writer = new BufferedWriter(new FileWriter(name));

            for(final Card card:cardRepository.getC)

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
