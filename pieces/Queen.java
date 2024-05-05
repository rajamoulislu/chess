package pieces;

import Main.*;

/**
 * The Queen class represents a queen piece in the chess game.
 * It extends the Piece class and provides specific functionality for the queen
 * piece.
 */
public class Queen extends Piece {
    /**
     * Constructs a Queen object with the given board, column, row, and color.
     * 
     * @param board   the chess board
     * @param col     the column of the queen
     * @param row     the row of the queen
     * @param isWhite true if the queen is white, false if it is black
     */
    public Queen(Board board, int col, int row, boolean isWhite) {
        super(board, "bq", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "bq";
    };

    /**
     * Checks if the movement of the queen to the given column and row is valid.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(int col, int row) {
        return this.col == col || this.row == row || Math.abs(this.col - col) == Math.abs(this.row - row);
    };

    /**
     * Checks if the movement of the queen to the given column and row collides with
     * another piece.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement collides with another piece, false otherwise
     */
    public boolean moveCollidesWithPiece(int col, int row) {
        if (this.col == col || this.row == row) {
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
        } else {
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
        };
        
        return false;
    };
};