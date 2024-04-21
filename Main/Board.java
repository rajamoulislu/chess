package Main;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import pieces.*;
import Enums.*;

public class Board extends JPanel {
    public int tileSize = 85;
    int cols = 8;
    int rows = 8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece selectedPiece;

    Input input = new Input(this);
    public CheckScanner checkScanner = new CheckScanner(this);

    public int enPassantTile = -1;

    public Board() {
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
    };

    public Piece getPiece(int col, int row) {
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row) {
                return piece;
            };
        };

        return null;
    };

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

        capture(move.capture);
    };

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
    };

    private void promotePawn(Move move) {
        pieceList.add(new Queen(this, move.newCol, move.newRow, move.piece.isWhite));
        capture(move.piece);
    };

    public boolean isValidMove(Move move) {
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

    public void capture(Piece piece) {
        pieceList.remove(piece);
    };

    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            return false;
        };

        return p1.isWhite == p2.isWhite;
    };

    Piece findKing(boolean isWhite) {
        for (Piece piece : pieceList) {
            if (isWhite == piece.isWhite && (piece.name.equals("bk") || piece.name.equals("wk"))) {
                return piece;
            };
        };

        return null;
    };

    public int getTileNum(int col, int row) {
        return row * rows + col;
    };

    public void addPieces() {
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));

        pieceList.add(new Pawn(this, 0, 1, false));
        pieceList.add(new Pawn(this, 1, 1, false));
        pieceList.add(new Pawn(this, 2, 1, false));
        pieceList.add(new Pawn(this, 3, 1, false));
        pieceList.add(new Pawn(this, 4, 1, false));
        pieceList.add(new Pawn(this, 5, 1, false));
        pieceList.add(new Pawn(this, 6, 1, false));
        pieceList.add(new Pawn(this, 7, 1, false));

        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Queen(this, 3, 7, true));
        pieceList.add(new King(this, 4, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));

        pieceList.add(new Pawn(this, 0, 6, true));
        pieceList.add(new Pawn(this, 1, 6, true));
        pieceList.add(new Pawn(this, 2, 6, true));
        pieceList.add(new Pawn(this, 3, 6, true));
        pieceList.add(new Pawn(this, 4, 6, true));
        pieceList.add(new Pawn(this, 5, 6, true));
        pieceList.add(new Pawn(this, 6, 6, true));
        pieceList.add(new Pawn(this, 7, 6, true));
    };

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Paint board
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? ColorEnum.ATHS_SPECIAL.getColor() : ColorEnum.ASPARAGUS.getColor());
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            };

        // Paint selected piece highlight
        if (selectedPiece != null) {
            Color highlightColor;
            if ((selectedPiece.col + selectedPiece.row) % 2 == 0) {
                highlightColor = new Color(0xB9CA70);
            } else {
                highlightColor = new Color(0xF5F682);
            }
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