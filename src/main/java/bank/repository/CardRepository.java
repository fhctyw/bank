package bank.repository;


import bank.entity.Card;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardRepository {
    private final String source = "card.txt";
    private  List<Card> cards = new ArrayList<>();
    private Long id;
    private Long cardNumber;

    public void add(final Card card){
        final Card cardFinal = new Card();
        cardFinal.setId(++id);
        cardFinal.setCardNumber(++cardNumber);
        cardFinal.setIdClient(card.getIdClient());
        cardFinal.setAmount(card.getAmount());
        cards.add(cardFinal);
    }

    public Card findById(final Long id){
        return cards.stream().filter(e->e.getId().equals(id)).findFirst().orElseThrow();
    }

    public void setCard(final Long id, final Card card){
        final Card c = findById(id);
        c.setCardNumber(card.getCardNumber());
        c.setIdClient(card.getCardNumber());
    }

    public void deleteCard(final Long id){
        cards.removeIf(e->e.getId().equals(id));
    }




}
