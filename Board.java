import java.awt.*;
import javax.swing.*;

public class Board extends JPanel {
    public int tileSize = 85;
    int cols = 8;
    int rows = 8;

    public Board() {
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        // this.setBackground(new Color(0x739452));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c+r) % 2 == 0 ? ColorEnum.ATHS_SPECIAL.getColor() : ColorEnum.ASPARAGUS.getColor());
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
    }
}