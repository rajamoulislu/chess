package pieces;

import Main.*;

public class Rook extends Piece {
    public Rook(Board board, int col, int row, boolean isWhite) {
        super(board, "br", isWhite);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.name = "br";
    };
};