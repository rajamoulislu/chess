package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import pieces.Piece;

/**
 * The Input class handles mouse input events for the chess board.
 * It extends the MouseAdapter class to override specific mouse event methods.
 */
public class Input extends MouseAdapter {

    Board board;

    /**
     * Constructs an Input object with the given Board.
     * 
     * @param board the chess board associated with the input events
     */
    public Input(Board board) {
        this.board = board;
    };

    /**
     * Invoked when a mouse button is pressed and dragged on the chess board.
     * 
     * @param e the MouseEvent object containing information about the event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.selectedPiece != null) {
            board.selectedPiece.xPos = e.getX() - board.tileSize / 2;
            board.selectedPiece.yPos = e.getY() - board.tileSize / 2;

            board.repaint();
        };
    };

    /**
     * Invoked when a mouse button is pressed on the chess board.
     * 
     * @param e the MouseEvent object containing information about the event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        Piece pieceXY = board.getPiece(col, row);

        if (pieceXY != null) {
            board.selectedPiece = pieceXY;
        };
    };

    /**
     * Invoked when a mouse button is released on the chess board.
     * 
     * @param e the MouseEvent object containing information about the event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        if (board.selectedPiece != null) {
            Move move = new Move(board, board.selectedPiece, col, row);

            if (board.isValidMove(move)) {
                board.makeMove(move);
            } else {
                board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
            };
        };

        board.selectedPiece = null;
        board.repaint();
    };
};