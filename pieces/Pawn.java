package pieces;

import Main.*;

public class Pawn extends Piece {
    public Pawn(Board board, int col, int row, boolean isWhite) {
        super(board, "bp", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.name = "bp";
    };

    public boolean isValidMovement(int col, int row) {
        int colorPiece = iswhite ? 1 : -1;

        if (this.col == col && row == this.row - colorPiece && board.getPiece(col, row) == null)
            return true;

        if (isFirstMove && this.col == col && row == this.row - colorPiece * 2 && board.getPiece(col, row) == null
                && board.getPiece(col, row + colorPiece) == null)
            return true;

        if (this.col == col && row == this.row - colorPiece && board.getPiece(col, row) == null)
            return true;

        if (col == this.col - 1 && row == this.row - colorPiece && board.getPiece(col, row) != null)
            return true;

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