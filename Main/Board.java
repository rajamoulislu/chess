package Main;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import pieces.*;
import Enums.*;

/**
 * The Board class represents the chess board and manages the game logic.
 * It extends JPanel to handle the graphical representation of the board.
 */
public class Board extends JPanel {
    /** The size of each tile on the board. */
    public int tileSize = 85;
    /** Number of columns on the board. */
    int cols = 8;
    /** Number of rows on the board. */
    int rows = 8;
    /** Indicates whether it's white player's turn. */
    public static boolean iswhite = true;

    /** List of pieces currently on the board. */
    ArrayList<Piece> pieceList = new ArrayList<>();
    /** The currently selected piece on the board. */
    public Piece selectedPiece;

    /** Handles user input events for the board. */
    Input input = new Input(this);
    /** Manages the scanning for checks on the board. */
    public CheckScanner checkScanner = new CheckScanner(this);

    /** Represents the tile where an en passant capture can occur. */
    public int enPassantTile = -1;

    /**
     * Constructs a new Board object.
     */
    public Board() {
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
    };

    /**
     * Retrieves the piece at the specified column and row.
     *
     * @param col The column index.
     * @param row The row index.
     * @return The Piece object at the specified location, or null if no piece
     *         found.
     */
    public Piece getPiece(int col, int row) {
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row) {
                return piece;
            };
        };
        return null;
    };

    /**
     * Moves a piece on the board according to the provided move.
     *
     * @param move The Move object representing the move to be executed.
     */
    public void makeMove(Move move) {
        if (move.piece.name.equals("bp") || move.piece.name.equals("wp")) {
            movePawn(move);
        } else if ((move.piece.name.equals("bk") || move.piece.name.equals("wk"))) {
            moveKing((move));
        };

        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        move.piece.isFirstMove = false;
        iswhite = !iswhite;
        capture(move.capture);
    };

    /**
     * Moves a king piece, handling special cases such as castling.
     *
     * @param move The Move object representing the move to be executed.
     */
    private void moveKing(Move move) {
        if (Math.abs(move.piece.col - move.newCol) == 2) {
            Piece rook;
            if (move.piece.col < move.newCol) {
                rook = getPiece(7, move.piece.row);
                rook.col = 5;
            } else {
                rook = getPiece(0, move.piece.row);
                rook.col = 3;
            };
            rook.xPos = rook.col * tileSize;
        };
    };

    /**
     * Moves a pawn piece, handling special cases such as en passant and promotion.
     *
     * @param move The Move object representing the move to be executed.
     */
    private void movePawn(Move move) {
        // en passant
        int colorPiece = move.piece.isWhite ? 1 : -1;

        if (getTileNum(move.newCol, move.newRow) == enPassantTile) {
            move.capture = getPiece(move.newCol, move.newRow + colorPiece);
        };

        if (Math.abs(move.piece.row - move.newRow) == 2) {
            enPassantTile = getTileNum(move.newCol, move.newRow + colorPiece);
        } else {
            enPassantTile = -1;
        };

        // promotions
        colorPiece = move.piece.isWhite ? 0 : 7;
        if (move.newRow == colorPiece) {
            promotePawn(move);
        };
    }

    /**
     * Promotes a pawn piece to a queen upon reaching the end of the board.
     *
     * @param move The Move object representing the move to be executed.
     */
    private void promotePawn(Move move) {
        pieceList.add(new Queen(this, move.newCol, move.newRow, move.piece.isWhite));
        capture(move.piece);
    };

    /**
     * Checks if a given move is valid.
     *
     * @param move The Move object representing the move to be checked.
     * @return True if the move is valid, otherwise false.
     */
    public boolean isValidMove(Move move) {
        if (move.piece.isWhite != iswhite) {
            return false;
        };
        if (move.newCol < 0 || move.newCol >= cols || move.newRow < 0 || move.newRow >= rows) {
            return false;
        };
        if (sameTeam(move.piece, move.capture)) {
            return false;
        };
        if (!move.piece.isValidMovement(move.newCol, move.newRow)) {
            return false;
        };
        if (move.piece.moveCollidesWithPiece(move.newCol, move.newRow)) {
            return false;
        };
        if (checkScanner.isKingChecked(move)) {
            return false;
        };
        return true;
    };

    /**
     * Captures a piece from the board.
     *
     * @param piece The Piece object to be captured.
     */
    public void capture(Piece piece) {
        pieceList.remove(piece);
    };

    /**
     * Checks if two pieces belong to the same team.
     *
     * @param p1 The first Piece object.
     * @param p2 The second Piece object.
     * @return True if both pieces belong to the same team, otherwise false.
     */
    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            return false;
        };
        return p1.isWhite == p2.isWhite;
    };

    /**
     * Finds the king piece of the specified color on the board.
     *
     * @param isWhite True for white king, false for black king.
     * @return The Piece object representing the king.
     */
    Piece findKing(boolean isWhite) {
        for (Piece piece : pieceList) {
            if (isWhite == piece.isWhite && (piece.name.equals("bk") || piece.name.equals("wk"))) {
                return piece;
            };
        };
        return null;
    };

    /**
     * Gets the tile number for the given column and row.
     *
     * @param col The column index.
     * @param row The row index.
     * @return The tile number.
     */
    public int getTileNum(int col, int row) {
        return row * rows + col;
    };

    /**
     * Adds all the chess pieces to the board at their initial positions.
     */
    public void addPieces() {
        // Add black pieces
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));
        for (int i = 0; i < cols; i++) {
            pieceList.add(new Pawn(this, i, 1, false));
        };

        // Add white pieces
        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Queen(this, 3, 7, true));
        pieceList.add(new King(this, 4, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));
        for (int i = 0; i < cols; i++) {
            pieceList.add(new Pawn(this, i, 6, true));
        };
    };

    /**
     * Paints the graphical representation of the board and pieces.
     *
     * @param g The Graphics object to paint on.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Paint board
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? ColorEnum.ATHS_SPECIAL.getColor() : ColorEnum.ASPARAGUS.getColor());
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            };
        };

        // Paint selected piece highlight
        if (selectedPiece != null) {
            Color highlightColor = ((selectedPiece.col + selectedPiece.row) % 2 == 0) ? ColorEnum.MINDARO.getColor() : ColorEnum.WILD_WILLOW.getColor();
            g2d.setColor(highlightColor);
            g2d.fillRect(selectedPiece.col * tileSize, selectedPiece.row * tileSize, tileSize, tileSize);
        };

        // Paint Highlights
        if (selectedPiece != null) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(Color.WHITE);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.drawRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    };
                };
            };
        };

        // Paint pieces
        for (Piece piece : pieceList) {
            piece.paint(g2d);
        };
    };
};
