package me.ethan.tictactoe;

import javax.swing.*;

public class TicTacToeTile extends JButton {
    private int row;
    private int col;
    private String state;

    public TicTacToeTile(int row, int col, String state) {
        super();
        this.row = row;
        this.col = col;
        this.state = state;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
