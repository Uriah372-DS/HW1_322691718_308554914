public class Card
{
    private final int val;
    private final Shape shape;
    private final boolean isRoyalOrAce;

    public Card(int val, Shape shape)
    {
        this.val = val;
        this.shape = shape;
        this.isRoyalOrAce = (val >= 10 || val == 1);
    }

    public int compare(Card other)
    {
        return Integer.signum(this.val - other.val);
    }

    public String toString()
    {
        String str;
        if (isRoyalOrAce)
            str = switch (val) {
                case 1 -> "Ace of " + shape.getSymbol();
                case 10 -> "Jack of " + shape.getSymbol();
                case 11 -> "Queen of " + shape.getSymbol();
                case 12 -> "King of " + shape.getSymbol();
                default -> "illegal value";
            };
        else
            str = val + " of " + shape.getSymbol();
        return str;
    }
}
