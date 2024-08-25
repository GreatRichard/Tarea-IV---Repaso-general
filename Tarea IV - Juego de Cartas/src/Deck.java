import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;

    // Constructor
    public Deck() {
        cards = new LinkedList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] colors = {"Red", "Red", "Black", "Black"};

        // Crear baraja de cartas
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(suit, ranks[i], colors[i % 2]));
            }
        }

        shuffle(); // Barajar las cartas al crear la baraja
    }

    // Barajar las cartas
    public void shuffle() {
        Collections.shuffle(cards, new Random());
    }

    // Repartir una carta
    public Card dealCard() {
        return cards.remove(0);
    }
}