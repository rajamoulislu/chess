package pieces;

import Main.*;

public class King extends Piece {
    public King(Board board, int col, int row, boolean isWhite) {
        super(board, "bk", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.name = "bk";
    };

    public boolean isValidMovement(int col, int row) {
        int dx = Math.abs(col - this.col);
        int dy = Math.abs(row - this.row);

        return (dx == 1 && dy == 1) || (dx == 1 && dy == 0) || (dx == 0 && dy == 1);

        // return Math.abs((col - this.col) * (row - this.row)) == 1 || Math.abs(col - this.col) + Math.abs(row - this.row) == 1;
    };
};