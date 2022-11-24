package bank.repository;


import bank.dto.MakeCardDTO;
import bank.entity.Card;
import bank.exception.ServiceException;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class CardRepository {
    private final String source = "cards.txt";
    private List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(final List<Card> cards) {
        this.cards = cards;
    }

    @Getter
    @Setter
    private Long id = 0L;

    private Long cardNumber = 0L;

    @PostConstruct
    public void postConstructorId() {
        final Path file = Paths.get(source);
        try {
            cards = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16),
                    new TypeReference<>() {
                    });
            if (cards == null) {
                cards = new ArrayList<>();
                return;
            }

            final long maxId = cards.stream().mapToLong(Card::getId).max().orElse(1);
            id = maxId + 1;
        } catch (final IOException e) {
            System.out.println("file " + source + " doesn't exist");
        }
    }
//
//    @PostConstruct
//    public void postConstructorCardNumber() {
//        final Path file = Paths.get(source);
//        try {
//            cards = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16),
//                    new TypeReference<List<Card>>() {
//                    });
//            if (cards == null) {
//                cards = new ArrayList<>();
//                return;
//            }
//
//            final long maxId = cards.stream().mapToLong(Card::getCardNumber).max().orElse(1);
//            cardNumber = maxId + 1;
//        } catch (final IOException e) {
//            System.out.println("file " + source + " doesn't exist");
//        }
//    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(cards)), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }


    public void add(final Card card) {
        final Card cardFinal = new Card();
        cardFinal.setId(++id);
        cardFinal.setCardNumber(card.getCardNumber());
        cardFinal.setIdClient(card.getIdClient());
        cardFinal.setAmount(card.getAmount());
        cardFinal.setIdAccount(card.getIdAccount());
        cards.add(cardFinal);
    }

    public Card findByNumber(final Long number) {
        return cards.stream().filter(e -> e.getCardNumber().equals(number)).findFirst()
                .orElseThrow(() -> new ServiceException("No such card number when finding"));
    }

    public Card findById(final Long id) {
        return cards.stream().filter(e -> e.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ServiceException("No such id when finding"));
    }

    public void setCard(final Long id, final Card card) {
        final Card c = findById(id);
        c.setCardNumber(card.getCardNumber());
        c.setIdClient(card.getIdClient());
        c.setIdAccount(card.getIdAccount());
        c.setAmount(card.getAmount());
    }

    public void deleteCard(final Long id) {
        setCards(cards.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList()));
    }
}
