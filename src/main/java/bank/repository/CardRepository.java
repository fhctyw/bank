package bank.repository;


import bank.entity.Card;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardRepository {
    private final String source = "cards.txt";
    private List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    private Long id;
    private Long cardNumber = 0L;

    @PostConstruct
    public void postConstructorId() {
        final Path file = Paths.get(source);
        try {
            cards = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16),
                    new TypeReference<List<Card>>() {
                    });
            if (cards == null) {
                cards = new ArrayList<>();
                return;
            }

            final long maxId = cards.stream().mapToLong(Card::getId).max().orElse(1);
            id = maxId + 1;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    public void postConstructorCardNumber() {
        final Path file = Paths.get(source);
        try {
            cards = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16),
                    new TypeReference<List<Card>>() {
                    });
            if (cards == null) {
                cards = new ArrayList<>();
                return;
            }

            final long maxId = cards.stream().mapToLong(Card::getCardNumber).max().orElse(1);
            cardNumber = maxId + 1;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get(source);

        try{
            Files.writeString(file,JacksonUtil.serialize(cards),StandardCharsets.UTF_16);
        }catch (final IOException e){
            e.printStackTrace();
        }
    }


    public void add(final Card card) {
        final Card cardFinal = new Card();
        cardFinal.setId(++id);
        cardFinal.setCardNumber(++cardNumber);
        cardFinal.setIdClient(card.getIdClient());
        cardFinal.setAmount(card.getAmount());
        cards.add(cardFinal);
    }

    public Card findById(final Long id) {
        return cards.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow();
    }

    public void setCard(final Long id, final Card card) {
        final Card c = findById(id);
        c.setCardNumber(card.getCardNumber());
        c.setIdClient(card.getCardNumber());
    }

    public void deleteCard(final Long id) {
        cards.removeIf(e -> e.getId().equals(id));
    }


}
