package pieces;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Main.*;

public class Piece {
    public int col, row;
    public int xPos, yPos;
    public boolean iswhite;
    public String name;
    public int value;
    BufferedImage image;
    Board board;

    public Piece(Board board, String pieceName, boolean isWhite) {
        this.board = board;
        this.name = pieceName;
        this.iswhite = isWhite;
        loadImage();
    };

    private void loadImage() {
        try {
            String folderPath = "pieces_image";
            File folder = new File(folderPath);

            if (folder.exists() && folder.isDirectory()) {
                String imageFileName = (iswhite ? "w" : "b") + name.charAt(1) + ".png";
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
        }
        ;
    };

    public void paint(Graphics2D g2d) {
        if (image != null) {
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(image, xPos, yPos, board.tileSize, board.tileSize, null);
        }
        ;
    };
};