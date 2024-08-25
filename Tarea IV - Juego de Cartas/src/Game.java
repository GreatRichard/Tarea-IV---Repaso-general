import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players; // Lista de jugadores en el juego
    private Deck deck; // Baraja de cartas
    private int rounds; // Número de rondas a jugar

    // Constructor para inicializar el juego con 2 jugadores y 3 rondas
    public Game(String playerName, double initialAmount) {
        if (playerName == null || playerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }

        if (initialAmount < 200) {
            throw new IllegalArgumentException("Initial amount must be at least $200.");
        }

        players = new ArrayList<>();
        // Agregar el jugador llamado "Pepé" con un monto inicial de 1000
        players.add(new Player("Pepé", 1000));

        // Agregar el jugador cuyo nombre se ingresa con el monto inicial proporcionado
        players.add(new Player(playerName, initialAmount));

        deck = new Deck(); // Crear baraja de cartas
        rounds = 3; // Limitar el número de rondas a 3
    }

    // Método para repartir cartas a cada jugador
    public void dealCards() {
        for (int i = 0; i < 5; i++) { // Repartir 5 cartas por jugador
            for (Player player : players) {
                player.addCard(deck.dealCard());
            }
        }
    }

    // Método para jugar todas las rondas
    public void playRounds() {
        for (int i = 1; i <= rounds; i++) {
            PlayRound round = new PlayRound(players, i); // Crear una instancia de PlayRound
            round.play(); // Jugar la ronda
        }
    }

    // Método para mostrar el ganador final del juego y el monto final de cada jugador
    public void showFinalWinner() {
        Player winner = null;
        for (Player player : players) {
            if (winner == null || player.getPoints() > winner.getPoints()) {
                winner = player;
            }
        }
        System.out.println("Game winner: " + winner.getName() + " with " + winner.getPoints() + " points.");

        // Mostrar el monto final de cada jugador
        System.out.println("Final amounts for each player:");
        for (Player player : players) {
            System.out.println(player.getName() + ": $" + player.getFinalAmount());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mensaje de bienvenida
        System.out.println("Welcome to the game created by Richard Meyer, your Programming II student.");
        System.out.println("The objective of this game is similar to blackjack, but you will play against the machine named 'Pepé'.");
        System.out.println("The cards are shuffled and dealt randomly. The game consists of 3 rounds, and the player who wins 2 rounds wins the game.");
        System.out.println("Additionally, each round has a minimum bet of $200.");

        // Mensaje indicando el nombre del primer jugador y monto inicial
        System.out.println("You will play against Pepé who starts with $1000.");

        // Solicitar nombre y monto inicial del segundo jugador
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        double initialAmount;
        while (true) {
            System.out.print("Enter your initial amount: ");
            initialAmount = scanner.nextDouble();
            if (initialAmount < 200 || initialAmount > 1000) {
                System.out.println("The entered amount must be between $200 and $1000. Please enter a valid amount.");
            } else {
                break;
            }
        }

        Game game = new Game(playerName, initialAmount); // Crear el juego con 2 jugadores y 3 rondas
        game.dealCards(); // Repartir cartas
        game.playRounds(); // Jugar las rondas
        game.showFinalWinner(); // Mostrar el ganador final y los montos finales
    }
}