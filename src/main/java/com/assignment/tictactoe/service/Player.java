package com.assignment.tictactoe.service;

import javafx.scene.control.Button;

import java.awt.*;

public abstract class Player {
    protected Board board;
    //protected BoardImpl boardImpl;

    //public Player() {}
    public Player(Board board) {
        this.board = board;
    }

    public abstract void move(int row, int col);
}
