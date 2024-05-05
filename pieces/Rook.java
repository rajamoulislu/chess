package pieces;

import Main.*;

/**
 * The Rook class represents a rook piece in the chess game.
 * It extends the Piece class and provides specific functionality for the rook
 * piece.
 */
public class Rook extends Piece {
    /**
     * Constructs a Rook object with the given board, column, row, and color.
     * 
     * @param board   the chess board
     * @param col     the column of the rook
     * @param row     the row of the rook
     * @param isWhite true if the rook is white, false if it is black
     */
    public Rook(Board board, int col, int row, boolean isWhite) {
        super(board, "br", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "br";
    };

    /**
     * Checks if the movement of the rook to the given column and row is valid.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(int col, int row) {
        return this.col == col || this.row == row;
    };

    /**
     * Checks if the movement of the rook to the given column and row collides with
     * another piece.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement collides with another piece, false otherwise
     */
    public boolean moveCollidesWithPiece(int col, int row) {
        // left
        if (this.col > col)
            for (int c = this.col - 1; c > col; c--)
                if (board.getPiece(c, this.row) != null)
                    return true;

        // right
        if (this.col < col)
            for (int c = this.col + 1; c < col; c++)
                if (board.getPiece(c, this.row) != null)
                    return true;

        // up
        if (this.row > row)
            for (int r = this.row - 1; r > row; r--)
                if (board.getPiece(this.col, r) != null)
                    return true;

        // down
        if (this.row < row)
            for (int r = this.row + 1; r < row; r++)
                if (board.getPiece(this.col, r) != null)
                    return true;

        return false;
    };
};