import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicTicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char currentPlayer;
    private char[][] board;

    public GraphicTicTacToe() {
        currentPlayer = 'X';
        board = new char[3][3];
        buttons = new JButton[3][3];
        
        // 设置窗口属性
        this.setTitle("井字棋");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 3));

        // 初始化按钮
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                this.add(buttons[i][j]);
                board[i][j] = ' ';
            }
        }

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        int row = -1, col = -1;

        // 查找按钮的位置
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == buttonClicked) {
                    row = i;
                    col = j;
                }
            }
        }

        // 如果按钮为空，则更新棋盘和按钮
        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            buttonClicked.setText(String.valueOf(currentPlayer));

            if (checkWinner()) {
                JOptionPane.showMessageDialog(this, "玩家 " + currentPlayer + " 胜利！");
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "平局！");
                resetGame();
            } else {
                switchPlayer();
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkWinner() {
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

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j].setText(" ");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        new GraphicTicTacToe();
    }
}
