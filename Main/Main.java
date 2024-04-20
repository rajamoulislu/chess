package Main;

import javax.swing.*;
import java.awt.*;
import Enums.*;

public class Main {
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