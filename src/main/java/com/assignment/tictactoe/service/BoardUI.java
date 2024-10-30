package com.assignment.tictactoe.service;

public interface BoardUI {
    void update(int col, int row, boolean isHuman); /* (button walata X , O set karala board eka update karanawa ) */
    void notifyWinner(); /* game eka win karapu kena hari game eka draw hari UI ekata pass karanawa */
    /* BoardUI interface eken thamai UI ekai game logic ekai connect karanne */

}
