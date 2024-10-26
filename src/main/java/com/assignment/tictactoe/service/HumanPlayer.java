package com.assignment.tictactoe.service;

import com.assignment.tictactoe.controller.BoardController;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class HumanPlayer extends Player {

    //BoardController boardController;

    public HumanPlayer(Board board) {
        super(board);

    }

    @Override
    public void move(int row, int col) {
        Piece piece = Piece.X;

        //int[] humanMove = {row, col};
        board.updateMove(row, col, piece);

//        try {
//            boardController.update(row, col, true);
//        }catch (Exception e){}
        //return humanMove;
    }

}
