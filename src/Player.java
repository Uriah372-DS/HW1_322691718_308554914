public class Player
{
    private final String name;
    private Deck gameDeck;
    private Deck winDeck;

    public Player(String name)
    {
        this.name = name;
        gameDeck = new Deck();
        winDeck = new Deck();
    }

    public String getName()
    {
        return name;
    }

    public void swapGameAndWinDecks()
    {
        Deck temp = gameDeck;
        gameDeck = winDeck;
        winDeck = temp;
    }

    public void addToDeck(Card card, boolean addToGameDeck)
    {
        if(addToGameDeck)
            gameDeck.addCard(card);
        else
            winDeck.addCard(card);
    }

    public Card drawCard()
    {
        if(gameDeck.isEmpty())
        {
            if(winDeck.isEmpty())
                return null;
            swapGameAndWinDecks();
            gameDeck.shuffle();
        }
        return gameDeck.removeTopCard();
    }

    boolean outOfCards()
    {
        return (gameDeck.isEmpty() && winDeck.isEmpty());
    }

    @Override
    public String toString()
    {
        return name;
    }
}
