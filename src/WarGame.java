public class WarGame
{
    private final Player player1;
    private final Player player2;
    private final Deck mainDeck;

    public WarGame(String nameOfPlayer1, String nameOfPlayer2)
    {
        boolean isPlayer1first = nameOfPlayer1.toLowerCase().compareTo(nameOfPlayer2.toLowerCase()) <= 0;
        player1 = new Player(isPlayer1first ? nameOfPlayer1 : nameOfPlayer2);
        player2 = new Player(isPlayer1first ? nameOfPlayer2 : nameOfPlayer1);
        mainDeck = new Deck(true);
    }

    public void initializeGame()
    {
        System.out.println("Initializing the game...");
        mainDeck.shuffle();
        while (!mainDeck.isEmpty())
        {
            player1.addToDeck(mainDeck.removeTopCard(), true);
            player2.addToDeck(mainDeck.removeTopCard(), true);
        }
    }

    private int warRounds()
    {
        System.out.println("Starting a war...");
        int winner = 0;
        boolean isWarDone = false;

        Card player1DrawnCard, player2DrawnCard;

        while (!isWarDone) {
            player1DrawnCard = player1.drawCard();
            player2DrawnCard = player2.drawCard();

            if (player1DrawnCard != null && player2DrawnCard != null) {
                if (!player1.outOfCards()) {
                    System.out.println(player1.getName() + " drew a war card");
                    mainDeck.addCard(player1DrawnCard);
                }


                if (!player2.outOfCards()) {
                    System.out.println(player2.getName() + " drew a war card");
                    mainDeck.addCard(player2DrawnCard);
                }
            }

            if (player1.outOfCards()) {
                winner = -1;
            }
            else if (player2.outOfCards()) {
                winner = 1;
            }
            else {
                winner = mainDeck.peekAtIndex(mainDeck.deckSize() - 2).compare(
                        mainDeck.peekAtIndex(mainDeck.deckSize() - 1));
                if (winner == 1)
                    System.out.println(player1.getName() + " won the war");
                else if (winner == -1)
                    System.out.println(player2.getName() + " won the war");
            }
            isWarDone = (winner != 0);
        }
        return winner;
    }

    private void spoilsGoTo(int winner)
    {
        if (winner == 1) {
            System.out.println(player1.getName() + " won");
            while (!mainDeck.isEmpty())
                player1.addToDeck(mainDeck.removeTopCard(), false);
        }
        else {
            System.out.println(player2.getName() + " won");
            while (!mainDeck.isEmpty())
                player2.addToDeck(mainDeck.removeTopCard(), false);
        }
    }

    public String start()
    {
        initializeGame();
        int roundCounter = 1;
        String nameOfWinner;
        Card player1Card, player2Card;
        while (!player1.outOfCards() && !player2.outOfCards())
        {
            System.out.println("------------------------- Round number " + (roundCounter++) +
                    " -------------------------");
            player1Card = player1.drawCard();
            System.out.println(player1.getName() + " drew " + player1Card.toString());
            player2Card = player2.drawCard();
            System.out.println(player2.getName() + " drew " + player2Card.toString());
            mainDeck.addCard(player1Card);
            mainDeck.addCard(player2Card);
            int winner = mainDeck.peekAtIndex(mainDeck.deckSize() - 2).compare(
                    mainDeck.peekAtIndex(mainDeck.deckSize() - 1));
            if (winner == 0)
                winner = warRounds();
            spoilsGoTo(winner);
        }
        nameOfWinner = (player2.outOfCards()) ? player1.getName() : player2.getName();
        return nameOfWinner;
    }
}
