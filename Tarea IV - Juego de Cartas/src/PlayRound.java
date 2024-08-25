import java.util.List;
import java.util.Scanner;

public class PlayRound {
    private List<Player> players; // Lista de jugadores en la ronda actual
    private int roundNumber; // Número de la ronda

    // Constructor para inicializar la ronda con los jugadores y el número de ronda
    public PlayRound(List<Player> players, int roundNumber) {
        this.players = players;
        this.roundNumber = roundNumber;
    }

    // Método para jugar una ronda
    public void play() {
        System.out.println("Round " + roundNumber + ":");
        Player winner = null; // Jugador ganador de la ronda
        Card winningCard = null; // Carta ganadora de la ronda

        // Cada jugador juega una carta y realiza una apuesta
        for (Player player : players) {
            Scanner scanner = new Scanner(System.in);

            // Si el jugador es Pepé, realizar una apuesta automática
            if (player.getName().equals("Pepé")) {
                player.placeAutomaticBet();
                System.out.println("Pepé bets: $" + player.getCurrentBet());
            } else {
                // Solicitar apuesta al jugador
                double betAmount = 0;
                boolean validBet = false;

                // Validación para asegurar que la apuesta sea mayor o igual a 200
                while (!validBet) {
                    System.out.print(player.getName() + ", enter your bet amount: ");
                    betAmount = scanner.nextDouble();

                    if (betAmount < 200 || betAmount > player.getCurrentAmount()) {
                        System.out.println("Bet amount must be at least $200 and cannot exceed your current amount. Please enter a valid bet.");
                    } else {
                        validBet = true; // Apuesta válida
                    }
                }

                // Colocar la apuesta después de la validación
                player.placeBet(betAmount);
            }

            Card playedCard = player.playCard();
            System.out.println(player.getName() + " plays: " + playedCard);

            // Determinar el ganador de la ronda
            if (winner == null || compareCards(playedCard, winningCard) > 0) {
                winner = player;
                winningCard = playedCard;
            } else if (compareCards(playedCard, winningCard) == 0) {
                winner = null; // Empate
            }
        }

        // Anunciar el ganador de la ronda
        if (winner != null) {
            winner.addPoint();
            winner.winBet(players.stream().mapToDouble(Player::getCurrentBet).sum()); // Asegurarse de que la apuesta máxima sea ganada por el jugador
            System.out.println("Round winner: " + winner.getName());
        } else {
            System.out.println("Tie in this round.");
        }
        System.out.println();
    }

    // Método para comparar dos cartas y determinar cuál es mayor
    private int compareCards(Card c1, Card c2) {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int rank1 = java.util.Arrays.asList(ranks).indexOf(c1.getRank());
        int rank2 = java.util.Arrays.asList(ranks).indexOf(c2.getRank());
        return Integer.compare(rank1, rank2);
    }
}