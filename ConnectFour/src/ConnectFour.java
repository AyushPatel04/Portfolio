import java.util.Scanner;

public class ConnectFour {
    private static final Board board = new Board();
    private static final Scanner scanner = new Scanner(System.in);
    private static Player player1;
    private static Player player2;
    private static Player currentPlayer;

    public static void main(String[] args) {
        player1 = new Player("Player 1", 'X');
        player2 = new Player("Player 2", 'O');
        currentPlayer = player1;

        boolean gameWon = false;

        while (!gameWon && !board.isFull()) {
            board.displayBoard();
            boolean validMove = false;
            int column = -1;
            
            while (!validMove) {
                System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getToken() + "): Enter a column (0-6):");
                if (scanner.hasNextInt()) {
                    column = scanner.nextInt();
                    // Check if the column is within the valid range and not full
                    if (column >= 0 && column < board.getColumns()) {
                        validMove = board.dropToken(column, currentPlayer.getToken());
                        if (!validMove) {
                            System.out.println("Column full or invalid, try again.");
                        }
                    } else {
                        System.out.println("Invalid column. Please select a column between 0 and " + (board.getColumns() - 1) + ".");
                        scanner.nextLine(); // Consume the newline
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume the invalid input
                }
            }

            gameWon = board.isWin(currentPlayer.getToken());
            if (gameWon) {
                board.displayBoard();
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }
            if (board.isFull()) {
                board.displayBoard();
                System.out.println("The game is a draw.");
                break;
            }
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }
}
