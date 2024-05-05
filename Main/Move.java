package Main;

import pieces.Piece;

/**
 * The Move class represents a move in the chess game.
 * It contains information about the old and new positions of a piece, the piece
 * being moved, and any captured piece.
 */
public class Move {
    int oldCol;
    int oldRow;
    int newCol;
    int newRow;

    Piece piece;
    Piece capture;

    /**
     * Constructs a Move object with the given board, piece, and new position.
     * 
     * @param board  the chess board
     * @param piece  the piece being moved
     * @param newCol the new column of the piece
     * @param newRow the new row of the piece
     */
    public Move(Board board, Piece piece, int newCol, int newRow) {
        this.oldCol = piece.col;
        this.oldRow = piece.row;
        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        this.capture = board.getPiece(newCol, newRow);
    };
};
