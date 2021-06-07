public enum Shape
{
    SPADES('♠'), DIAMONDS('♦'), CLUBS('♣'), HEARTS('♥');
    private final char symbol;

    Shape(char symbol)
    {
        this.symbol = symbol;
    }

    public char getSymbol()
    {
        return symbol;
    }
}
