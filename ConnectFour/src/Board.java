public class Board {
    private final char[][] board;
    private final int rows = 6;
    private final int columns = 7;
    private final char emptySlot = '.';

    public Board() {
        board = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = emptySlot;
            }
        }
    }

    public int getColumns() {
        return columns;
    }

    public boolean dropToken(int column, char token) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == emptySlot) {
                board[i][column] = token;
                return true;
            }
        }
        return false; // Column is full
    }

    public void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isWin(char token) {
        // Horizontal check
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == token && board[row][col + 1] == token && board[row][col + 2] == token && board[row][col + 3] == token) {
                    return true;
                }
            }
        }

        // Vertical check
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < columns; col++) {
                if (board[row][col] == token && board[row + 1][col] == token && board[row + 2][col] == token && board[row + 3][col] == token) {
                    return true;
                }
            }
        }

        // Diagonal (down-right) check
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == token && board[row + 1][col + 1] == token && board[row + 2][col + 2] == token && board[row + 3][col + 3] == token) {
                    return true;
                }
            }
        }

        // Diagonal (up-right) check
        for (int row = 3; row < rows; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (board[row][col] == token && board[row - 1][col + 1] == token && board[row - 2][col + 2] == token && board[row - 3][col + 3] == token) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isFull() {
        for (int col = 0; col < columns; col++) {
            if (board[0][col] == emptySlot) {
                return false;
            }
        }
        return true;
    }
}
