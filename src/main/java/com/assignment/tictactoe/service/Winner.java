package com.assignment.tictactoe.service;

import com.assignment.tictactoe.controller.BoardController;
import com.assignment.tictactoe.controller.WelcomePanelController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Optional;

public class Winner {
    public Piece winningPiece;
    public int col1, col2, col3;
    public int row1, row2, row3;
    BoardUI boardUI;


    public Winner(Piece winningPiece) {
        this.winningPiece = winningPiece;

    }

    public Winner(Piece winningPiece, int col1, int row1, int col2, int row2, int col3, int row3) {
        this.winningPiece = winningPiece;
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
        this.col3 = col3;
        this.row3 = row3;


    }

//    public String toString() {
//        if (winningPiece == Piece.O) {
//            return "Ai Won";
//        } else if (winningPiece == Piece.X) {
//            return "You Won";
//        } else {
//            return "It's a draw!";
//        }
//    }



}
