package bank.repository;


import bank.entity.Card;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardRepository {
    final List<Card> cards = new ArrayList<>();
    private Long id = 0L;
    int cardNumber = 100000000;

    public void add(final Card card){
        final Card finalCard = new Card();
        finalCard.setCardNumber(cardNumber++);
        finalCard.setAmount(card.getAmount());
        finalCard.setIdCard(id++);
        finalCard.setIdClient(card.getIdClient());
        cards.add(finalCard);

    }


    public CardRepository() {
        add(new Card(0,0,0,0));//id client!!!!!!!!!!!
        add(new Card(0,0,0,0));
    }

    public Card get(Long id) {
    }
}
