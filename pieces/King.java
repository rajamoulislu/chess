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
};