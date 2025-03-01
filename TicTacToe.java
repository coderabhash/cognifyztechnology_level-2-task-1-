import java.util.*;

public class TicTacToe {


    public static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
    }

    
    public static boolean checkWin(char[][] board, char player) {
        
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || 
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) { 
                return true;
            }
        }
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) || 
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) { 
            return true;
        }
        return false;
    }

    
    public static boolean checkDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; 
                }
            }
        }
        return true; 
    }

    
    public static int getMove(char player) {
        Scanner Sc = new Scanner(System.in);
        int move = -1;

        while (move < 1 || move > 9) {
            System.out.print("Player " + player + ", enter your move (1-9): ");
            try {
                move = Integer.parseInt(Sc.nextLine());
                if (move < 1 || move > 9) {
                    System.out.println("Invalid move. Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid! . Please enter a valid number.");
            }
        }
        return move;
    }

    
    public static boolean updateBoard(char[][] board, int move, char player) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;

        if (board[row][col] == ' ') {
            board[row][col] = player;
            return true;
        } else {
            System.out.println("Cell already occupied. try again.");
            return false;
        }
    }

    public static void playGame() {
        Scanner Sc = new Scanner(System.in);

        while (true) {
            char[][] board = new char[3][3];
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                }
            }

            char currentPlayer = 'X'; 

            while (true) {
                printBoard(board);

                int move = getMove(currentPlayer);
                if (!updateBoard(board, move, currentPlayer)) {
                    continue; 
                }

                if (checkWin(board, currentPlayer)) {
                    printBoard(board);
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                if (checkDraw(board)) {
                    printBoard(board);
                    System.out.println("It's a draw!");
                    break;
                }

                
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }

            
            System.out.print("Do you want to play another round? (y/n): ");
            char playAgain = Sc.next().charAt(0);
            if (playAgain != 'y' && playAgain != 'Y') {
                System.out.println("Thank you for playing!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}
