package pieces;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Main.*;

/**
 * The Piece class represents a general chess piece in the game.
 * It serves as a base class for specific piece types (e.g., Pawn, Knight,
 * Bishop, etc.).
 */
public class Piece {
    public int col, row;
    public int xPos, yPos;
    public boolean isWhite;
    public String name;
    public int value;
    BufferedImage image;
    public boolean isFirstMove = true;

    Board board;

    /**
     * Constructs a Piece object with the given board, piece name, and color.
     * 
     * @param board     the chess board
     * @param pieceName the name of the piece
     * @param isWhite   true if the piece is white, false if it is black
     */
    public Piece(Board board, String pieceName, boolean isWhite) {
        this.board = board;
        this.name = pieceName;
        this.isWhite = isWhite;
        loadImage();
    };

    /**
     * Checks if the movement of the piece to the given column and row is valid.
     * This method should be overridden by specific piece types.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement is valid, false otherwise
     */
    public boolean isValidMovement(int col, int row) {
        return true;
    };

    /**
     * Checks if the movement of the piece to the given column and row collides with
     * another piece.
     * This method should be overridden by specific piece types.
     * 
     * @param col the destination column
     * @param row the destination row
     * @return true if the movement collides with another piece, false otherwise
     */
    public boolean moveCollidesWithPiece(int col, int row) {
        return false;
    };

    /**
     * Loads the image of the piece based on its color and name.
     * The image files should be located in the "pieces_image" folder.
     */
    private void loadImage() {
        try {
            String folderPath = "pieces_image";
            File folder = new File(folderPath);

            if (folder.exists() && folder.isDirectory()) {
                String imageFileName = (isWhite ? "w" : "b") + name.charAt(1) + ".png";
                File imageFile = new File(folder, imageFileName);

                if (imageFile.exists()) {
                    try {
                        image = ImageIO.read(imageFile);
                        System.out.println("Loaded image: " + imageFile.getName());
                    } catch (IOException e) {
                        System.out.println("Error reading image: " + imageFile.getName());
                        e.printStackTrace();
                    };
                } else {
                    System.out.println("Image file not found: " + imageFileName);
                };
            } else {
                System.out.println("Invalid folder path: " + folderPath);
            };
        } catch (Exception e) {
            System.err.println(e);
        };
    };

    /**
     * Paints the piece on the given Graphics2D context.
     * 
     * @param g2d the Graphics2D context
     */
    public void paint(Graphics2D g2d) {
        if (image != null) {
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(image, xPos, yPos, board.tileSize, board.tileSize, null);
        };
    };
};