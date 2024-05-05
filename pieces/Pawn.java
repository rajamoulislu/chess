package pieces;

import Main.*;

/**
 * The Pawn class represents a pawn piece in the chess game.
 * It extends the Piece class and provides specific functionality for the pawn
 * piece.
 */
public class Pawn extends Piece {
    /**
     * Constructs a Pawn object with the given board, column, row, and color.
     * 
     * @param board   the chess board
     * @param col     the column of the pawn
     * @param row     the row of the pawn
     * @param isWhite true if the pawn is white, false if it is black
     */
    public Pawn(Board board, int col, int row, boolean isWhite) {
        super(board, "bp", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "bp";
    };

    /**
     * Checks if the movement of the pawn to the given column and row is valid.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(int col, int row) {
        int colorPiece = isWhite ? 1 : -1;


        // push pawn 1
        if (this.col == col && row == this.row - colorPiece && board.getPiece(col, row) == null)
            return true;

        // push pawn 2
        if (isFirstMove && this.col == col && row == this.row - colorPiece * 2 && board.getPiece(col, row) == null
                && board.getPiece(col, row + colorPiece) == null)
            return true;

        // capture left
        if (col == this.col - 1 && row == this.row - colorPiece && board.getPiece(col, row) != null)
            return true;

        // capture right
        if (col == this.col + 1 && row == this.row - colorPiece && board.getPiece(col, row) != null)
            return true;
            
        // en passant left
        if (board.getTileNum(col, row) == board.enPassantTile && col == this.col - 1 && row == this.row - colorPiece
                && board.getPiece(col, row + colorPiece) != null) {
            return true;
        };

        if (board.getTileNum(col, row) == board.enPassantTile && col == this.col + 1 && row == this.row - colorPiece
                && board.getPiece(col, row + colorPiece) != null) {
            return true;
        };

        return false;
    };
};