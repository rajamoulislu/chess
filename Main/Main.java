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

        // chess panel
        frame.getContentPane().setBackground(ColorEnum.DUNE.getColor());
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        Board board = new Board();
        GridBagConstraints boardConstraints = new GridBagConstraints();
        boardConstraints.gridx = 0;
        boardConstraints.gridy = 0;
        frame.add(board, boardConstraints);

        InfoPanel infoPanel = board.getInfoPanel();

        GridBagConstraints infoPanelConstraints = new GridBagConstraints();
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 0;
        infoPanelConstraints.fill = GridBagConstraints.VERTICAL;
        frame.add(infoPanel, infoPanelConstraints);

        frame.pack();
        frame.setVisible(true);
    };
};