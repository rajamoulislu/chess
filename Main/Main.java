package Main;

import javax.swing.*;
import java.awt.*;
import Enums.*;

/**
 * The Main class represents the entry point of the chess game application.
 * It creates the main window frame and sets up the chess board.
 */
public class Main {
    
    /**
     * The main method is the entry point of the application.
     * It creates the main window frame, sets its properties, and adds the chess
     * board to it.
     * 
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.getContentPane().setBackground(ColorEnum.DUNE.getColor());
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);

        Board board = new Board();
        frame.add(board);

        frame.setVisible(true);
    };
};