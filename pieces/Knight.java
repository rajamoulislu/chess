package pieces;

import Main.*;

public class Knight extends Piece {
    public Knight(Board board, int col, int row, boolean isWhite) {
        super(board, "bn", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.name = "bn";
    };
};