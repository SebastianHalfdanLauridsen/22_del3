package card;

import java.util.LinkedList;

//TODO make class
// - Create LinkedList with Card as the object named 'cards'
// - Create cardCount integer
// - Create constructor
// - Create createCard method that takes 'name', 'description' String and the card type object as parameters.
//      The method should then set the name and the description of the card class.
//      The method should then add the card object to the LinkedList and count 'cardCount' + 1
// - Create a public method drawCard that returns the first card in the LinkedList and then uses moveCardToBottom.
// - Create a private method moveCardToBottom that takes the first card in the LinkedList and moves it to the end
//      https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
// - Create a public method shuffle that uses Collections.shuffle to shuffle the LinkedList.
public class Deck {
    int cardCount;
    LinkedList<AbstractCard> cards;

    public Deck(){
        cards = new LinkedList<>();
        cardCount = 0;
    }

    public void addCard(String name, String description, AbstractCard card){
        card.setName(name);
        card.setDescription(description);
        cards.add(card);
        cardCount++;
    }

    public AbstractCard getCards(int index) {
        return cards.get(index);
    }
}

