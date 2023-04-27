package me.ethan.tictactoe;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TicTacToeFrame extends JFrame {
    JPanel mainPnl;
    public static JPanel gamePnl;
    public static JLabel xIcon;
    public static JLabel oIcon;
    public TicTacToeFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        gamePnl();
        mainPnl.add(gamePnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(600,600);
        add(mainPnl);
        setVisible(true);
    }
    private void gamePnl() {
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 10;
        c.ipady = 10;
        for (int i = 0 ; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                TicTacToeTile tile = TicTacToe.board[i][k];
                c.gridx = k;
                c.gridy = i;
                tile.setFont(new Font("SansSerif", Font.BOLD, 50));
                TicTacToe.setTrueSize(tile, 125, 125);
                c.insets = new Insets(10, 10, 10, 10);
                tile.setBorder(new BevelBorder(BevelBorder.RAISED));
                gamePnl.add(tile, c);
            }
        }
        clickListeners();
        gamePnl.setPreferredSize(new Dimension(500,500));

    }

    public void clickListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TicTacToeTile tile = (TicTacToeTile) ae.getSource();
                String state = tile.getState();
                if (state.equalsIgnoreCase("Em")) {
                    if (TicTacToe.playerTurn.equalsIgnoreCase("X")) {
                        TicTacToe.turnAction(tile);
                    } else if (TicTacToe.playerTurn.equalsIgnoreCase("O")) {
                        TicTacToe.turnAction(tile);
                    }
                } else {
                    if (TicTacToe.debug) {
                        System.out.println("Tile R" + tile.getRow() + ", C" + tile.getCol() + " has state " + state);
                    }
                    JOptionPane.showMessageDialog(null, "You cannot perform that move!", "Illegal Move", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        for (int i = 0 ; i < 3 ;i ++) {
            for (int k = 0; k < 3 ; k++) {
                TicTacToeTile tile = TicTacToe.board[i][k];
                tile.addActionListener(buttonListener);
            }
        }

    }


}
