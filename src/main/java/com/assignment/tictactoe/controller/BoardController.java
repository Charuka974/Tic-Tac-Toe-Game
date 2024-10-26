package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BoardController implements Initializable, BoardUI {

    @FXML
    private GridPane boardGridPane;

    @FXML
    private Button replayButton;

    @FXML
    private Label winnerLbl;

    @FXML
    private Button button0_0, button0_1, button0_2;
    @FXML
    private Button button1_0, button1_1, button1_2;
    @FXML
    private Button button2_0, button2_1, button2_2;

    @FXML
    private AnchorPane gameUiAnchorPane;

    @FXML
    private Button backBtn;

    @FXML
    private Pane gameUiPane;


    private final Board board = new BoardImpl(this);
    public static final Button[][] buttons = new Button[3][3];

    /* Initialize AI and Human players with the current controller instance */
    private final AiPlayer aiPlayer = new AiPlayer(board, this);  /* Pass this to AiPlayer */
    private final HumanPlayer humanPlayer = new HumanPlayer(board);

    public static Button[][] getButtons() {
        return buttons;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons[0][0] = button0_0;
        buttons[0][1] = button0_1;
        buttons[0][2] = button0_2;
        buttons[1][0] = button1_0;
        buttons[1][1] = button1_1;
        buttons[1][2] = button1_2;
        buttons[2][0] = button2_0;
        buttons[2][1] = button2_1;
        buttons[2][2] = button2_2;

        winnerLbl.setVisible(false);
    }

    @FXML
    void makeMove(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int row = -1, col = -1;

        // Identify the clicked button's position
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clickedButton) {
                    row = i;
                    col = j;
                }
            }
        }

        if (row == -1 || col == -1 || !clickedButton.getText().isEmpty()) {
            System.out.println("Invalid button click.");
            return;
        }

        // Human makes a move
        humanMove(row, col);
    }

    public void humanMove(int row, int col) {
        // Human player makes a move
        humanPlayer.move(row, col);
        update(row, col, true);  // Update UI for human move (X)

        /* Check if the human won */
        Winner winner = board.checkWinner();
        if (winner == null) {
            /* ai ekata move eka set karanna call karanawa */
            aiMove();
        }

    }


    int countMove = WelcomePanelController.mode;

    public void aiMove() {

        if (countMove == 0) {
            aiPlayer.moveRandom(-1, -2); /* palaweni sareta withrak random move ekak select karanawa */
            ++countMove;
        } else {
            aiPlayer.move(-1, -2);  /* palaweni sare indalama minimax eken analyse wechcha move ekak select karanawa */
        }


        /* Check if the AI won */
        Winner aiWinner = board.checkWinner();
//        if (aiWinner != null) {
//            /* ai dinala nam winner show karanawa */
//            //board.getBoardUI().notifyWinner();
//        }

    }


    @Override
    public void update(int row, int col, boolean isHuman) {
        if (isHuman) {
            buttons[row][col].setText("X");
            buttons[row][col].setStyle("-fx-text-fill: red;");
        } else {
            buttons[row][col].setText("O");
            buttons[row][col].setStyle("-fx-text-fill: blue;");
        }
        buttons[row][col].setDisable(true);
    }

    @Override
    public void notifyWinner() {

        Winner winner = board.checkWinner();
        String win;
        if (winner != null) {
            win = String.valueOf(winner.winningPiece);
            if (Objects.equals(win, "O")) {
                System.out.println("Ai won");
                disableButtons();
                winnerLbl.setVisible(true);
                winnerLbl.setText("Ai won , You lost..");

            } else if (Objects.equals(win, "X")) {
                System.out.println("Human won");
                disableButtons();
                winnerLbl.setVisible(true);
                winnerLbl.setText("You won , Ai lost..  ");
            } else {
                System.out.println("Tied");
                disableButtons();
                winnerLbl.setVisible(true);
                winnerLbl.setText("Game is tied..");
            }
        }


    }

    private void disableButtons() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setDisable(true);
            }
        }
    }


    @FXML
    void goBack(ActionEvent event) {
        gameUiAnchorPane.setVisible(false);
    }

}


