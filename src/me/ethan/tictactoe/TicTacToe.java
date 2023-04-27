package me.ethan.tictactoe;



import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TicTacToe {
    public static final int ROW = 3;
    public static Scanner scanner = new Scanner(System.in);
    public static Scanner ynCheck = new Scanner(System.in);
    public static final int COL = 3;
    public static TicTacToeTile board[][] = new TicTacToeTile[ROW][COL];
    public static String playerTurn;
    public static int turn;
    public static boolean debug = false;
    public static void main(String[] args) {
        for ( int i = 0; i < 3; i++) {
            for ( int k = 0; k < 3; k++) {
                board[i][k] = new TicTacToeTile(i, k, "Em");
                board[i][k].setText(" ");
                if (debug) {
                    board[i][k].setText(i + ", " + k);
                }
                board[i][k].setCol(k);
                board[i][k].setRow(i);
                setTrueSize(board[i][k], 125, 125);
            }
        }
        clearBoard();
        JFrame frame = new TicTacToeFrame();
        boolean playAgain = true;
        playerTurn = "X";
        turn = 0;
        int rowPlay = 0;
        int colPlay = 0;

    }
    private static void clearBoard() {
        for ( int i = 0; i < 3; i++) {
            for ( int k = 0; k < 3; k++) {
                board[i][k].setState("Em");
                board[i][k].setCol(k);
                board[i][k].setRow(i);
                board[i][k].setText(" ");
                if (debug) {
                    board[i][k].setText(board[i][k].getRow() + ", " + board[i][k].getCol());
                }

            }
        }
    }





    public static boolean isWin(String player ) {
        if (isRowWin(player) || isColWin(player) || isDiagWin(player)) {
            return true;
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for ( int i = 0; i < 3; i++) {
            if (board[i][0].getState().equalsIgnoreCase(board[i][1].getState()) && board[i][0].getState().equalsIgnoreCase(board[i][2].getState())) {
                if (board[i][0].getState().equalsIgnoreCase(player)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for ( int i = 0; i < 3; i++) {
            if (board[0][i].getState().equalsIgnoreCase(board[1][i].getState()) && board[0][i].getState().equalsIgnoreCase(board[2][i].getState())) {
                if (player.equalsIgnoreCase(board[0][i].getState())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isDiagWin(String player) {
        if (board[0][0].getState().equalsIgnoreCase(board[1][1].getState()) && board[0][0].getState().equalsIgnoreCase(board[2][2].getState())) {
            if (player.equalsIgnoreCase( board[0][0].getState())) {
                return true;
            }
        } else if (board[2][0].getState().equalsIgnoreCase(board[1][1].getState()) && board[2][0].getState().equalsIgnoreCase(board[0][2].getState())) {
            if (player.equalsIgnoreCase(board[2][0].getState())) {
                return true;
            }
        }
        return false;
    }

    public static void setTrueSize(TicTacToeTile frame, int width, int height) {
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
    }
    public static void turnAction (TicTacToeTile tile) {
        tile.setText(playerTurn);
        tile.setState(playerTurn);
        TicTacToe.setTrueSize(tile, 125, 125);
        if (TicTacToe.isWin(playerTurn)) {
            endWindow();
        } else if (TicTacToe.tieState()) {
            tieWindow();
        } else {
            if (playerTurn.equalsIgnoreCase("O")) {
                playerTurn = "X";
            } else if (playerTurn.equalsIgnoreCase("X")) {
                playerTurn = "O";
            }
        }
        turn++;
        if (debug) {
            System.out.println(turn);
        }


    }
    public static void endWindow() {
        int r = JOptionPane.showConfirmDialog(null, "Player " + playerTurn + " wins!\nWould you like to play again?\n", "Play Again?", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            for ( int i = 0; i < 3; i++) {
                for ( int k = 0; k < 3; k++) {
                    board[i][k].setState(" ");
                    board[i][k].setText(" ");
                    setTrueSize(board[i][k], 125, 125);
                }
            }
            clearBoard();
            playerTurn = "X";
            turn = 0;
        } else if (r == JOptionPane.NO_OPTION) {
            System.exit(1);
        }
    }
    public static void tieWindow() {
        int r = JOptionPane.showConfirmDialog(null, "It's a tie!\nWould you like to play again?\n", "Play Again?", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            for ( int i = 0; i < 3; i++) {
                for ( int k = 0; k < 3; k++) {
                    board[i][k].setState(" ");
                    board[i][k].setText(" ");
                    setTrueSize(board[i][k], 125, 125);
                }
            }
            clearBoard();
            playerTurn = "X";
            turn = 0;
        } else if (r == JOptionPane.NO_OPTION) {
            System.exit(1);
        }
    }

    public static boolean tieState() {
        if (turn >= 7) {
            String[][] tempBoard = new String[ROW][COL];
            int tests = 0;
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    tempBoard[i][k] = board[i][k].getState();
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    if (!tempBoard[i][k].equalsIgnoreCase("X") && !tempBoard[i][k].equalsIgnoreCase("O")) {
                        tempBoard[i][k] = ("X");
                        if (isWin("X")) {
                            return false;
                        } else {
                            tempBoard[i][k] = ("O");
                            if (isWin("O")) {
                                return false;
                            } else {
                                if (turn == 7 && tests == 2) {
                                    return true;
                                } else if (turn == 8) {
                                    return true;
                                }

                            }
                        }
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

}
