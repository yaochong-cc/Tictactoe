import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GraphicTicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char currentPlayer;
    private char[][] board;
    private Random random;

    public GraphicTicTacToe() {
        currentPlayer = 'X'; // 用户是 X
        board = new char[3][3];
        buttons = new JButton[3][3];
        random = new Random();

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
                computerMove();  // 计算机移动
                if (checkWinner()) {
                    JOptionPane.showMessageDialog(this, "计算机 " + currentPlayer + " 胜利！");
                    resetGame();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(this, "平局！");
                    resetGame();
                } else {
                    switchPlayer();
                }
            }
        }
    }

    private void computerMove() {
        // 随机选择一个空位
        while (true) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                buttons[row][col].setText(String.valueOf(currentPlayer));
                break;
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // 切换玩家
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
        currentPlayer = 'X'; // 重置为用户 X 开始
    }

    public static void main(String[] args) {
        new GraphicTicTacToe();
    }
}
