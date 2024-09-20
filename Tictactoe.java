import java.util.Scanner;

public class Tictactoe {
    private static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        while (true) {
            printBoard();
            playerMove();
            if (checkWinner()) {
                printBoard();
                System.out.println("玩家 " + currentPlayer + " 胜利！");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("平局！");
                break;
            }
            switchPlayer();
        }
    }

    private static void printBoard() {
        System.out.println("当前棋盘：");
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2] + " ");
            System.out.println();
            if (i < 2) {
                System.out.println("---|---|---");
            }
        }
    }

    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        while (true) {
            System.out.println("当前玩家: " + currentPlayer);
            System.out.print("请输入行（0, 1, 2）: ");
            row = scanner.nextInt();
            System.out.print("请输入列（0, 1, 2）: ");
            col = scanner.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("无效的输入，请选择有效的行和列。");
            }
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean checkWinner() {
        // 检查行、列、对角线
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
