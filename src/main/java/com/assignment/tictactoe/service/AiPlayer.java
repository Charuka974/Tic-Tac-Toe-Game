package com.assignment.tictactoe.service;

import com.assignment.tictactoe.controller.BoardController;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Random;

public class AiPlayer extends Player {

    private final BoardController boardController;

    public AiPlayer(Board board, BoardController boardController) {
        super(board);
        this.boardController = boardController;
    }

    @Override
    public void move(int row, int col) {
        System.out.println("Minimax AI Move");

        Piece piece = Piece.O;

        // Get the best move using AI logic
        int[] bestMove = chooseBestMove();
        int aiRow = bestMove[0];
        int aiCol = bestMove[1];

        // Update board state and UI
        board.updateMove(aiRow, aiCol, piece);  // Update logic
        boardController.update(aiRow, aiCol, false);  // Update UI
    }


    public int[] chooseBestMove() {
        int topScore = Integer.MIN_VALUE;
        int[] bestMove = null;/* we use this to save the best  move row and col*/

        /*  find how much the board is full and which places are empty (present state of the board) */
        Piece[][] currentBoard = BoardImpl.pieces;

        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                /* */
                if (currentBoard[i][j] == Piece.EMPTY) {
                    currentBoard[i][j] = Piece.O;

//                    System.out.println("Ai is thinking");
//                    boardImpl.printBoard();

                    // Use minimax to evaluate this move
                    int score = minimax(currentBoard, 0, false);

                    // Undo the move to restore the original state
                    currentBoard[i][j] = Piece.EMPTY;

                    // Update the best move if a higher score is found
                    if (score > topScore) {
                        topScore = score;
                        bestMove = new int[]{i, j}; /* we can only put two values at one to an array if we create a new one here */
                    }
                }
            }
        }
        return bestMove;
    }



    public int minimax(Piece[][] currentBoard, int depth, boolean isMaximizing) {
        Winner result = board.checkWinner();  // Use simulation mode

        if (result != null) {
            if (result.winningPiece == Piece.O) return 10 - depth;  // AI wins
            if (result.winningPiece == Piece.X) return depth - 10;  // Opponent wins
            if (result.winningPiece == Piece.EMPTY) return 0;  // Draw
        }

        if (isMaximizing) {  // AI's turn
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentBoard[i][j] == Piece.EMPTY) {
                        currentBoard[i][j] = Piece.O;
                        int score = minimax(currentBoard, depth + 1, false);
                        currentBoard[i][j] = Piece.EMPTY;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {  // Opponent's turn
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentBoard[i][j] == Piece.EMPTY) {
                        currentBoard[i][j] = Piece.X;
                        int score = minimax(currentBoard, depth + 1, true);
                        currentBoard[i][j] = Piece.EMPTY;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

        /* pahalin Thiyenne First move eka random set karanna easy mode theruwoth random position ekak set wena kotasa */
    //--------------------------------------//

    public void moveRandom(int row, int col) {
        System.out.println("Random Ai Move");

        Piece piece = Piece.O;
        Button[][] aiButtons = BoardController.buttons;

        int[] randomMove = generateRandomMove(aiButtons);

        if (randomMove != null) {
            int aiRow = randomMove[0];
            int aiCol = randomMove[1];

            board.updateMove(aiRow, aiCol, piece);
            boardController.update(aiRow, aiCol, false);  // Update UI
        } else {
            System.out.println("No valid moves available.");
        }

    }


    private int[] generateRandomMove(Button[][] aiButtons) {
        ArrayList<int[]> availableMoves = new ArrayList<>();


        for (int i = 0; i < aiButtons.length; i++) {
            for (int j = 0; j < aiButtons[i].length; j++) {
                if (aiButtons[i][j].getText().isEmpty()) {
                    availableMoves.add(new int[]{i, j});
                }
            }
        }


        if (availableMoves.isEmpty()) {
            return null;
        }


        Random random = new Random();
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }

    //--------------------------------------------------------------------------------------------
//    public void move(int row, int col) {
//        Piece piece = Piece.O; // AI's piece
//        Button[][] aiButtons = BoardController.getButtons();
//
//        int[] bestMove = chooseBestMove();
//
//        if (bestMove != null) {
//            int aiRow = bestMove[0];
//            int aiCol = bestMove[1];
//
//             boardImpl.updateMove(aiRow, aiCol, piece);
//
//        }
//    }

//--------------------------------------------------------------------------------------------

    // Minimax algorithm to evaluate the best move
//    public int minimax(Piece[][] currentBoard, int depth, boolean isMaximizing) {
//        Winner result = boardImpl.checkWinner(); // Check the winner state
//
//        if (result != null) {
//            if (result.winningPiece == Piece.O) return 10 - depth; // AI wins
//            if (result.winningPiece == Piece.X) return depth - 10; // Opponent wins
//            if (result.winningPiece == Piece.EMPTY) return 0; // Draw
//        }
//
//        if (isMaximizing) { // Maximizing for AI
//            int bestScore = Integer.MIN_VALUE;
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    if (currentBoard[i][j] == Piece.EMPTY) {
//                        currentBoard[i][j] = Piece.O; // AI's move
//                        int score = minimax(currentBoard, depth + 1, false);
//                        currentBoard[i][j] = Piece.EMPTY; // Undo move
//                        bestScore = Math.max(score, bestScore);
//                    }
//                }
//            }
//            return bestScore;
//        } else { // Minimizing for opponent
//            int bestScore = Integer.MAX_VALUE;
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    if (currentBoard[i][j] == Piece.EMPTY) {
//                        currentBoard[i][j] = Piece.X; // Opponent's move
//                        int score = minimax(currentBoard, depth + 1, true);
//                        currentBoard[i][j] = Piece.EMPTY; // Undo move
//                        bestScore = Math.min(score, bestScore);
//                    }
//                }
//            }
//            return bestScore;
//        }
//    }






}
