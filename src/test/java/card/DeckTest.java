package card;

import game.Board;
import game.Fields;
import game.Main;
import gui_main.GUI;
import org.junit.Test;
import org.junit.Assert;
import player.Bank;
import player.PlayerManager;

import java.util.ArrayList;

public class DeckTest {


    @Test
    public void runTest() {
        addCardTest();
        drawCardTest();
        cardCountTest();
        shuffleCardsTest();
    }

    @Test
    public void addCardTest() {
        Main.setLanguage();
        Deck deck = new Deck();

        //variables
        String name1 = "card1";
        String desc1 = "description1";

        String name2 = "card2";
        String desc2 = "description2";

        String name3 = "card3";
        String desc3 = "description3";

        //add cards to deck
        GUI gui = new GUI();
        Bank bank = new Bank(new PlayerManager(), new Board(gui), new Fields());

        deck.addCard(name1, desc1, new MoveCard(23));
        deck.addCard(name2, desc2, new PayBankCard(2, bank));
        deck.addCard(name3, desc3, new MoveMaxFiveCard(gui));

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
        Main.setLanguage();
        Deck deck = new Deck();

        GUI gui = new GUI();
        Bank bank = new Bank(new PlayerManager(), new Board(gui), new Fields());

        //add cards to deck
        deck.addCard("card", "description", new MoveCard(23));
        deck.addCard("card", "description", new PayBankCard(2, bank));
        deck.addCard("card", "description", new MoveMaxFiveCard(gui));

        for (int i = 0; i < deck.cardCount; i++) {
            AbstractCard card = deck.drawCard();
            Assert.assertEquals(card, deck.getCards().getLast());
            Assert.assertNotEquals(card, deck.getCards().getFirst());
        }
    }

    @Test
    public void cardCountTest() {
        Main.setLanguage();

        cardCountTestAssert(0, new Deck());
        cardCountTestAssert(1, new Deck());
        cardCountTestAssert(10000000, new Deck());
    }

    private void cardCountTestAssert(int amountOfCards, Deck deck) {
        for (int i = 0; i < amountOfCards; i++) {
            deck.addCard("card", "description", new MoveCard(23));
        }
        Assert.assertEquals(amountOfCards, deck.cardCount);
    }

    @Test
    public void shuffleCardsTest() {
        Main.setLanguage();

        int deckSize = 10000000;
        int alike = 0;

        Deck deck = new Deck();

        for(int i = 0; i < deckSize; i++) {
            deck.addCard("","",new MoveCard(23));
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
