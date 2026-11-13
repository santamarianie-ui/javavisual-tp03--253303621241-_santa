
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(CharacterManager manager) {
        setTitle("Character Manager - TP8");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("DAFTAR KARAKTER");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(title);

        panel.add(Box.createVerticalStrut(15));

        for (Character character : manager.getAllCharacters()) {
            JLabel label = new JLabel(
                    character.getName()
                            + " | " + character.getType()
                            + " | Level " + character.getLevel());

            label.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(label);
            panel.add(Box.createVerticalStrut(8));
        }

        panel.add(new JSeparator());

        JLabel averageLabel = new JLabel(
                String.format("Rata-rata Level: %.2f", manager.getAverageLevel()));

        averageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(averageLabel);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }
}
