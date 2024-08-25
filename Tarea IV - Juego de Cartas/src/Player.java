import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private String name;           // Nombre del jugador
    private double initialAmount;  // Monto inicial del jugador
    private double currentAmount;  // Monto actual del jugador
    private double currentBet;     // Apuesta actual del jugador
    private int points;            // Puntos acumulados
    private List<Card> hand;       // Mano de cartas del jugador

    // Constructor
    public Player(String name, double initialAmount) {
        this.name = name;
        this.initialAmount = initialAmount;
        this.currentAmount = initialAmount;
        this.points = 0;
        this.hand = new ArrayList<>();
    }

    // Getter del nombre
    public String getName() {
        return name;
    }

    // Getter del monto actual
    public double getCurrentAmount() {
        return currentAmount;
    }

    // Getter de la apuesta actual
    public double getCurrentBet() {
        return currentBet;
    }

    // Getter de los puntos
    public int getPoints() {
        return points;
    }

    // Obtener el monto final después de jugar
    public double getFinalAmount() {
        return currentAmount;
    }

    // Añadir una carta a la mano
    public void addCard(Card card) {
        hand.add(card);
    }

    // Colocar una apuesta
    public void placeBet(double bet) {
        if (bet > currentAmount) {
            throw new IllegalArgumentException("Bet amount cannot exceed current amount.");
        }
        currentBet = bet;
        currentAmount -= bet;
    }

    // Colocar una apuesta automática
    public void placeAutomaticBet() {
        Random rand = new Random();
        double maxBet = currentAmount; // Apuesta máxima dentro del monto actual
        double bet = rand.nextInt((int) maxBet / 200) * 200 + 200; // Apuesta aleatoria entre $200 y monto máximo
        if (bet > maxBet) bet = maxBet;
        placeBet(bet);
    }

    // Jugar una carta (elegir aleatoriamente de la mano)
    public Card playCard() {
        Random rand = new Random();
        int index = rand.nextInt(hand.size());
        return hand.remove(index);
    }

    // Añadir puntos al jugador
    public void addPoint() {
        points++;
    }

    // Añadir monto ganado
    public void winBet(double amount) {
        currentAmount += amount;
    }
}