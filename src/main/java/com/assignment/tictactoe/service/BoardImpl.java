package com.assignment.tictactoe.service;


import com.assignment.tictactoe.controller.WelcomePanelController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BoardImpl implements Board {
    public static Piece[][] pieces;

    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces = new Piece[3][3];
        initializeBoard();
    }


    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public void initializeBoard() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {

        if (isLegalMove(row, col)) {
            pieces[row][col] = piece;
            if (Piece.X == piece) {
                boardUI.update(row, col, true);
                System.out.println("Your move");
            } else if (Piece.O == piece) {
                boardUI.update(row, col, false);
                System.out.println("Ai move");
            }
        }


        printBoard();
        checkWinner();
        boardUI.notifyWinner();

    }

    @Override
    public Winner checkWinner() {
        // Check rows for winners
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i][0] != Piece.EMPTY && pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2]) {

                return new Winner(pieces[i][0]);  // Return the result to minimax or game logic
            }
        }

        // Check columns for winners
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[0][i] != Piece.EMPTY && pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i]) {

                return new Winner(pieces[0][i]);    // Return the result to minimax or game logic
            }
        }

        // Check diagonals
        if (pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]) {

            return new Winner(pieces[0][0]);    // Return the result to minimax or game logic
        }

        if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]) {

            return new Winner(pieces[0][2]);    // Return the result to minimax or game logic
        }

        // Check for a draw
        boolean boardFull = true;
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    boardFull = false;
                    break;
                }
            }
        }

        if (boardFull) {

            return new Winner(Piece.EMPTY);// Simulator draw announcement
        }

        return null;  // No winner or draw yet
    }

    @Override
    public void printBoard() {

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                switch (pieces[j][i]) {
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    case EMPTY:
                        System.out.print(" - ");
                        break;
                }
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("---+---+---");
        }
        System.out.println();
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        boolean isLegal = false;
        isLegal = (pieces[row][col] == Piece.EMPTY);
        return isLegal;
    }


//--------------------------------------------------------------

//    public Winner checkWinner(boolean realmove) {
//        // Check rows for winners
//        for (int i = 0; i < pieces.length; i++) {
//            if (pieces[i][0] != Piece.EMPTY && pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2]) {
//
//                if (realmove) {
//                    boardUI.notifyWinner(new Winner(pieces[i][0])); // Real winner announcement
//                }
//
//                return new Winner(pieces[i][0]);  // Return the result to minimax or game logic
//            }
//        }
//
//        // Check columns for winners
//        for (int i = 0; i < pieces.length; i++) {
//            if (pieces[0][i] != Piece.EMPTY && pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i]) {
//
//                    if (realmove) {
//                        boardUI.notifyWinner(new Winner(pieces[0][i]));  // Real winner announcement
//                    }
//
//                return new Winner(pieces[0][i]);
//            }
//        }
//
//        // Check diagonals
//        if (pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]) {
//
//            if (realmove) {
//                boardUI.notifyWinner(new Winner(pieces[0][0]));
//            }
//
//            return new Winner(pieces[0][0]);
//        }
//
//        if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]) {
//
//            if (realmove) {
//                boardUI.notifyWinner(new Winner(pieces[0][2]));
//            }
//
//            return new Winner(pieces[0][2]);
//        }
//
//        // Check for a draw
//        boolean boardFull = true;
//        for (int i = 0; i < pieces.length; i++) {
//            for (int j = 0; j < pieces[i].length; j++) {
//                if (pieces[i][j] == Piece.EMPTY) {
//                    boardFull = false;
//                    break;
//                }
//            }
//        }
//
//        if (boardFull) {
//
//            if (realmove) {
//                boardUI.notifyWinner(new Winner(Piece.EMPTY));  // Real draw announcement
//            }
//
//            return new Winner(Piece.EMPTY);// Simulator draw announcement
//        }
//
//        return null;  // No winner or draw yet
//    }

//--------------------------------------------------------------

//    public void printBoard(String player) {
//        if (player.equals("O")) {
//            System.out.println("Ai move");
//        } else {
//            System.out.println("Your move");
//        }
//
//        for (int i = 0; i < pieces.length; i++) {
//            for (int j = 0; j < pieces[i].length; j++) {
//                switch (pieces[j][i]) {
//                    case X:
//                        System.out.print(" X ");
//                        break;
//                    case O:
//                        System.out.print(" O ");
//                        break;
//                    case EMPTY:
//                        System.out.print(" - ");
//                        break;
//                }
//                if (j < 2) System.out.print("|");
//            }
//            System.out.println();
//            if (i < 2) System.out.println("---+---+---");
//        }
//        System.out.println();
//    }
//




}
