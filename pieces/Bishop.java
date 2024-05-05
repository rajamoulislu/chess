package pieces;

import Main.*;

/**
 * The Bishop class represents a bishop piece in the chess game.
 * It extends the Piece class and provides specific functionality for the bishop
 * piece.
 */
public class Bishop extends Piece {

    /**
     * Constructs a Bishop object with the given board, column, row, and color.
     * 
     * @param board   the chess board
     * @param col     the column of the bishop
     * @param row     the row of the bishop
     * @param isWhite true if the bishop is white, false if it is black
     */
    public Bishop(Board board, int col, int row, boolean isWhite) {
        super(board, "bb", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "bb";
    };

    /**
     * Checks if the movement of the bishop to the given column and row is valid.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(int col, int row) {
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    };

    /**
     * Checks if the movement of the bishop to the given column and row collides
     * with any other piece.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement collides with another piece, false otherwise
     */
    public boolean moveCollidesWithPiece(int col, int row) {
        // up-left
        if (this.col > col && this.row > row)
            for (int i = 1; i < Math.abs(this.col - col); i++)
                if (board.getPiece(this.col - i, this.row - i) != null)
                    return true;
        // up-right
        if (this.col < col && this.row > row)
            for (int i = 1; i < Math.abs(this.col - col); i++)
                if (board.getPiece(this.col + i, this.row - i) != null)
                    return true;

        // down-left
        if (this.col > col && this.row < row)
            for (int i = 1; i < Math.abs(this.col - col); i++)
                if (board.getPiece(this.col - i, this.row + i) != null)
                    return true;

        // down-right
        if (this.col < col && this.row < row)
            for (int i = 1; i < Math.abs(this.col - col); i++)
                if (board.getPiece(this.col + i, this.row + i) != null)
                    return true;

        return false;
    };
};