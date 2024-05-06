package Main;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JTextArea infoTextArea;

    public InfoPanel() {
        setPreferredSize(new Dimension(200, 680)); // Adjust the size as needed
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        add(scrollPane, BorderLayout.CENTER);
    };

    public void addInfo(String info) {
        infoTextArea.append(info + "\n");
    };

    public void clear() {
        infoTextArea.setText("");
    };
};