package pieces;

import Main.*;

public class Queen extends Piece {
    public Queen(Board board, int col, int row, boolean isWhite) {
        super(board, "bq", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.name = "bq";
    };
};