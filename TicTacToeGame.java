import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame {
    private JFrame frame;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private String currentPlayer;
    private String[][] board;

    public TicTacToeGame() {
        frame = new JFrame("Tic-Tac-Toe");
        buttons = new JButton[3][3];
        board = new String[3][3];
        currentPlayer = "X";

        // Initialize GUI
        frame.setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        statusLabel = new JLabel("Player X's turn");
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                boardPanel.add(buttons[i][j]);
                board[i][j] = "";
            }
        }

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (board[row][col].isEmpty()) {
                board[row][col] = currentPlayer;
                buttons[row][col].setText(currentPlayer);

                if (checkWinner()) {
                    statusLabel.setText("Player " + currentPlayer + " wins!");
                    disableButtons();
                } else if (isDraw()) {
                    statusLabel.setText("It's a draw!");
                    disableButtons(); // Disable buttons after a draw
                } else {
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                    statusLabel.setText("Player " + currentPlayer + "'s turn");
                }
            }
        }
    }

    private boolean checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(currentPlayer) && board[i][1].equals(currentPlayer) && board[i][2].equals(currentPlayer)) {
                return true;
            }
            if (board[0][i].equals(currentPlayer) && board[1][i].equals(currentPlayer) && board[2][i].equals(currentPlayer)) {
                return true;
            }
        }
        if (board[0][0].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[2][2].equals(currentPlayer)) {
            return true;
        }
        if (board[0][2].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[2][0].equals(currentPlayer)) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        if (checkWinner()) {
            return false; // Ensure no draw is declared if there's a winner
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}