package pieces;

import Main.*;

/**
 * The King class represents a king piece in the chess game.
 * It extends the Piece class and provides specific functionality for the king
 * piece.
 */
public class King extends Piece {

    /**
     * Constructs a King object with the given board, column, row, and color.
     * 
     * @param board   the chess board
     * @param col     the column of the king
     * @param row     the row of the king
     * @param isWhite true if the king is white, false if it is black
     */
    public King(Board board, int col, int row, boolean isWhite) {
        super(board, "bk", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "bk";
    };

    /**
     * Checks if the movement of the king to the given column and row is valid.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(int col, int row) {
        return Math.abs((col - this.col) * (row - this.row)) == 1
                || Math.abs(col - this.col) + Math.abs(row - this.row) == 1 || canCastle(col, row);
    };

    /**
     * Checks if the king can perform a castling move to the given column and row.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the king can castle, false otherwise
     */
    private boolean canCastle(int col, int row) {
        if(this.row == row) {
            if(col == 6) {
                Piece rook  = board.getPiece(7, row);
                if (rook != null && rook.isFirstMove && isFirstMove) {
                    return board.getPiece(5, row) == null && board.getPiece(6, row) == null && !board.checkScanner.isKingChecked(new Move(board, this, 5, row));
                };
            } else if(col == 2) {
                Piece rook  = board.getPiece(0, row);
                if (rook != null && rook.isFirstMove && isFirstMove) {
                    return  board.getPiece(3, row) == null && 
                            board.getPiece(2, row) == null && 
                            board.getPiece(1, row) == null && 
                            !board.checkScanner.isKingChecked(new Move(board, this, 3, row));
                };
            };
        };

        return false;
    };
};