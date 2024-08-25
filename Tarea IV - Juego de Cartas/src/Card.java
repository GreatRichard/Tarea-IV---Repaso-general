public class Card {
    private String suit;    // Palo de la carta
    private String rank;    // Valor de la carta (2, 3, ..., K, A)
    private String color;   // Color de la carta (Rojo, Negro)

    // Constructor
    public Card(String suit, String rank, String color) {
        this.suit = suit;
        this.rank = rank;
        this.color = color;
    }

    // Getters
    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return rank + " of " + suit + " (" + color + ")";
    }
}