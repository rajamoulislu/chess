package pieces;

import Main.*;

/**
 * The Knight class represents a knight piece in the chess game.
 * It extends the Piece class and provides specific functionality for the knight
 * piece.
 */
public class Knight extends Piece {

    /**
     * Constructs a Knight object with the given board, column, row, and color.
     * 
     * @param board   the chess board
     * @param col     the column of the knight
     * @param row     the row of the knight
     * @param isWhite true if the knight is white, false if it is black
     */
    public Knight(Board board, int col, int row, boolean isWhite) {
        super(board, "bn", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "bn";
    };

    /**
     * Checks if the movement of the knight to the given column and row is valid.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(int col, int row) {
        return Math.abs(col - this.col) * Math.abs(row - this.row) == 2;
    };
};