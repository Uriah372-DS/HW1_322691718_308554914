import java.util.ArrayList;


public class Deck
{
    private final ArrayList<Card> cards;

    private void createCardsOfShape(Shape shape)
    {
        int numberOfCardsOfShape = 13, val = 1;
        while (val <= numberOfCardsOfShape)
            cards.add(new Card(val++, shape));
    }

    public Deck(boolean isMainDeck)
    {
        cards = new ArrayList<>();
        if (isMainDeck)
        {
            createCardsOfShape(Shape.SPADES);
            createCardsOfShape(Shape.DIAMONDS);
            createCardsOfShape(Shape.CLUBS);
            createCardsOfShape(Shape.HEARTS);
        }
    }

    public Deck()
    {
        this(false);
    }

    public int deckSize()
    {
        return cards.size();
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

    public Card removeTopCard()
    {
        return cards.remove(cards.size() - 1);
    }

    public boolean isEmpty()
    {
        return cards.size() == 0;
    }

    public void shuffle()
    {
        int totalIterations = 50, rndIndex1, rndIndex2;
        Card temp;
        do {
            rndIndex1 = Main.rnd.nextInt(cards.size() - 1);
            rndIndex2 = Main.rnd.nextInt(cards.size() - 1);
            temp = cards.get(rndIndex1);
            cards.set(rndIndex1, cards.get(rndIndex2));
            cards.set(rndIndex2, temp);
        } while(--totalIterations > 0);
    }

    public Card peekAtIndex(int index)
    {
        return cards.get(index);
    }
}
