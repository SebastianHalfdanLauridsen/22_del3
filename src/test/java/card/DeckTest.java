package card;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class DeckTest {

    @Test
    public void addCardTest() {
        Deck deck = new Deck();

        //variables
        String name1 = "card1";
        String desc1 = "description1";

        String name2 = "card2";
        String desc2 = "description2";

        String name3 = "card3";
        String desc3 = "description3";

        //add cards to deck
        deck.addCard(name1, desc1, new MoveCard());
        deck.addCard(name2, desc2, new PayBankCard());
        deck.addCard(name3, desc3, new MoveMaxFiveCard());

        //test
        AbstractCard card = deck.drawCard();
        Assert.assertEquals(name1, card.getName());
        Assert.assertEquals(desc1, card.getDescription());

        card = deck.drawCard();
        Assert.assertEquals(name2, card.getName());
        Assert.assertEquals(desc2, card.getDescription());

        card = deck.drawCard();
        Assert.assertEquals(name3, card.getName());
        Assert.assertEquals(desc3, card.getDescription());
    }

    @Test
    public void drawCardTest() {
        Deck deck = new Deck();

        //add cards to deck
        deck.addCard("card", "description", new MoveCard());
        deck.addCard("card", "description", new PayBankCard());
        deck.addCard("card", "description", new MoveMaxFiveCard());

        for (int i = 0; i < deck.cardCount; i++) {
            AbstractCard card = deck.drawCard();
            Assert.assertEquals(card, deck.getCards().getLast());
            Assert.assertNotEquals(card, deck.getCards().getFirst());
        }
    }

    @Test
    public void cardCountTest() {
        cardCountTestAssert(0, new Deck());
        cardCountTestAssert(1, new Deck());
        cardCountTestAssert(10000000, new Deck());
    }

    private void cardCountTestAssert(int amountOfCards, Deck deck) {
        for (int i = 0; i < amountOfCards; i++) {
            deck.addCard("card", "description", new MoveCard());
        }
        Assert.assertEquals(amountOfCards, deck.cardCount);
    }

    @Test
    public void shuffleCardsTest() {
        int deckSize = 10000000;
        int alike = 0;

        Deck deck = new Deck();

        for(int i = 0; i < deckSize; i++) {
            deck.addCard("","",new MoveCard());
        }
        final ArrayList<AbstractCard> prevCards = new ArrayList<>(deck.getCards());

        deck.shuffleCards();
        final ArrayList<AbstractCard> newCards = new ArrayList<>(deck.getCards());

        for(int i = 0; i < deckSize; i++) {
            if (prevCards.get(i).equals(newCards.get(i))){
                alike++;
            }
        }
        Assert.assertNotEquals(deckSize, alike);
    }
}
