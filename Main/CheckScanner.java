package Main;

import pieces.*;

import javax.swing.JOptionPane;

import Main.*;

/**
 * The CheckScanner class is responsible for scanning the chess board and
 * determining if the king is in check.
 * It takes a Board object as a parameter in its constructor.
 */
public class CheckScanner {
    Board board;

    /**
     * Constructs a CheckScanner object with the given Board.
     * 
     * @param board the chess board to be scanned
     */
    public CheckScanner(Board board) {
        this.board = board;
    };

    /**
     * Determines if the king is in check after a given move.
     * 
     * @param move the move to be checked
     * @return true if the king is in check, false otherwise
     */
    public boolean isKingChecked(Move move) {
        if (move == null || move.piece == null) {
            return false;
        };

        Piece king = board.findKing(move.piece.isWhite);
        // assert king != null;
        if (king == null) {
            return false;
        };

        int kingCol = king.col;
        int kingRow = king.row;

        if (board.selectedPiece != null
                && (board.selectedPiece.name.equals("bk") || board.selectedPiece.name.equals("wk"))) {
            kingCol = move.newCol;
            kingRow = move.newRow;
        };

        boolean kingcheck = hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, 0, 1) || // up
                hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, 1, 0) || // right
                hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, 0, -1) || // down
                hitByRook(move.newCol, move.newRow, king, kingCol, kingRow, -1, 0) || // left

                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, -1, -1) || // up-left
                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, 1, -1) || // up-right
                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, 1, 1) || // down-right
                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow, -1, 1) || // down-left

                hitByKnight(move.newCol, move.newRow, king, kingCol, kingRow) || 
                hitByPawn(move.newCol, move.newRow, king, kingCol, kingRow) || 
                hitByKing(king, kingCol, kingRow);

        if (kingcheck) {
            String captureInfo = "";
            if (move.piece.isWhite) {
                captureInfo = "check mate by black";
            } else {
                captureInfo = "check mate by white.";
            }


            board.getInfoPanel().addInfo(captureInfo);
            JOptionPane.showMessageDialog(null, captureInfo, "Checkmate", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0); // Close the application
        };

        return kingcheck;
    };

    /**
     * Checks if the king is hit by a rook or queen in the given direction.
     * 
     * @param col     the column of the piece being moved
     * @param row     the row of the piece being moved
     * @param king    the king piece
     * @param kingCol the column of the king
     * @param kingRow the row of the king
     * @param colVal  the column direction to check
     * @param rowVal  the row direction to check
     * @return true if the king is hit by a rook or queen, false otherwise
     */
    private boolean hitByRook(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row) {
                break;
            };
            Piece piece = board.getPiece(kingCol + (i * colVal), kingRow + (i * rowVal));
            if (piece != null && piece != board.selectedPiece) {
                if (!board.sameTeam(piece, king) && ((piece.name.equals("br") || piece.name.equals("wr"))
                        || (piece.name.equals("bq") || piece.name.equals("wq")))) {
                    return true;
                };
                break;
            };
        };
        return false;
    };

    /**
     * Checks if the king is hit by a bishop or queen in the given direction.
     * 
     * @param col     the column of the piece being moved
     * @param row     the row of the piece being moved
     * @param king    the king piece
     * @param kingCol the column of the king
     * @param kingRow the row of the king
     * @param colVal  the column direction to check
     * @param rowVal  the row direction to check
     * @return true if the king is hit by a bishop or queen, false otherwise
     */
    private boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row) {

                break;
            };

            Piece piece = board.getPiece(kingCol - (i * colVal), kingRow - (i * rowVal));
            if (piece != null && piece != board.selectedPiece) {
                if (!board.sameTeam(piece, king) && ((piece.name.equals("bb") || piece.name.equals("wb"))
                        || (piece.name.equals("bq") || piece.name.equals("wq")))) {

                    return true;
                }
                ;

                break;

            };
        };
        return false;
    };

    /**
     * Checks if the king is hit by a knight.
     * 
     * @param col     the column of the piece being moved
     * @param row     the row of the piece being moved
     * @param king    the king piece
     * @param kingCol the column of the king
     * @param kingRow the row of the king
     * @return true if the king is hit by a knight, false otherwise
     */
    private boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow) {
        return  checkKnight(board.getPiece(kingCol - 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow - 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 1, kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2, kingRow - 1), king, col, row);
    };

    /**
     * Checks if a given piece is a knight and threatens the king.
     * 
     * @param p   the piece to check
     * @param k   the king piece
     * @param col the column of the piece being moved
     * @param row the row of the piece being moved
     * @return true if the piece is a threatening knight, false otherwise
     */
    private boolean checkKnight(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && (p.name.equals("bn") || p.name.equals("wn"))
                && !(p.col == col && p.row == row);
    };

    /**
     * Checks if the king is hit by an opposing king.
     * 
     * @param king    the king piece
     * @param kingCol the column of the king
     * @param kingRow the row of the king
     * @return true if the king is hit by an opposing king, false otherwise
     */
    private boolean hitByKing(Piece king, int kingCol, int kingRow) {
        return  checkKing(board.getPiece(kingCol - 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingCol, kingRow + 1), king);
    };

    /**
     * Checks if a given piece is an opposing king.
     * 
     * @param p the piece to check
     * @param k the king piece
     * @return true if the piece is an opposing king, false otherwise
     */
    private boolean checkKing(Piece p, Piece k) {
        return p != null && !board.sameTeam(p, k) && (p.name.equals("bk") || p.name.equals("wk"));
    };

    /**
     * Checks if the king is hit by a pawn.
     * 
     * @param col     the column of the piece being moved
     * @param row     the row of the piece being moved
     * @param king    the king piece
     * @param kingCol the column of the king
     * @param kingRow the row of the king
     * @return true if the king is hit by a pawn, false otherwise
     */
    private boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow) {
        int colorVal = king.isWhite ? -1 : 1;

        return checkPawn(board.getPiece(kingCol + 1, kingRow + colorVal), king, col, row)
                || checkPawn(board.getPiece(kingCol - 1, kingRow + colorVal), king, col, row);
    };

    /**
     * Checks if a given piece is a pawn and threatens the king.
     * 
     * @param p   the piece to check
     * @param k   the king piece
     * @param col the column of the piece being moved
     * @param row the row of the piece being moved
     * @return true if the piece is a threatening pawn, false otherwise
     */
    private boolean checkPawn(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && (p.name.equals("bp") || p.name.equals("wp"));
    };
};