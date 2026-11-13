
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {

    private CharacterManager manager;

    public MainWindow(CharacterManager manager) {
        this.manager = manager;

        setTitle("Character Manager - TP10");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel utama
        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10));

        mainPanel.setBackground(AppTheme.BACKGROUND);
        mainPanel.setBorder(AppTheme.CARD_BORDER);

        // Judul
        JLabel title = new JLabel(
                "DAFTAR KARAKTER",
                SwingConstants.CENTER);

        title.setFont(AppTheme.TITLE_FONT);
        title.setForeground(AppTheme.TEXT_PRIMARY);

        mainPanel.add(title, BorderLayout.NORTH);

        // Panel daftar karakter
        JPanel listPanel = new JPanel();

        listPanel.setLayout(
                new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        listPanel.setBackground(AppTheme.BACKGROUND);

        // Mengambil data karakter
        List<Character> characters = manager.getAllCharacters();

        for (Character character : characters) {

            JPanel card = new JPanel(
                    new FlowLayout(FlowLayout.LEFT, 10, 8));

            // Warna kartu sesuai role
            card.setBackground(
                    AppTheme.colorFor(character.getType()));

            card.setOpaque(true);
            card.setBorder(AppTheme.CARD_BORDER);

            // Nama karakter
            JLabel nameLabel = new JLabel(
                    character.getName());

            nameLabel.setFont(AppTheme.BOLD_FONT);
            nameLabel.setForeground(
                    AppTheme.TEXT_PRIMARY);

            // Tipe karakter
            JLabel typeLabel = new JLabel(
                    character.getType().toString());

            typeLabel.setFont(AppTheme.NORMAL_FONT);
            typeLabel.setForeground(
                    AppTheme.TEXT_PRIMARY);

            // Level karakter
            JLabel levelLabel = new JLabel(
                    "Level " + character.getLevel());

            // Mini Challenge: level >= 5 emas
            if (character.getLevel() >= AppTheme.GOLD_LEVEL_LIMIT) {

                levelLabel.setForeground(AppTheme.GOLD);
                levelLabel.setFont(AppTheme.GOLD_FONT);

            } else {

                levelLabel.setForeground(
                        AppTheme.TEXT_SECONDARY);

                levelLabel.setFont(AppTheme.NORMAL_FONT);
            }

            // Properti tambahan 1: Tooltip
            card.setToolTipText(
                    "Nama: " + character.getName()
                            + " | Role: " + character.getType()
                            + " | Level: " + character.getLevel());

            // Properti tambahan 2: Level 1 disabled
            boolean active = character.getLevel() != 1;

            card.setEnabled(active);
            nameLabel.setEnabled(active);
            typeLabel.setEnabled(active);
            levelLabel.setEnabled(active);

            // Masukkan komponen
            card.add(nameLabel);
            card.add(new JLabel("|"));
            card.add(typeLabel);
            card.add(new JLabel("|"));
            card.add(levelLabel);

            listPanel.add(card);
            listPanel.add(Box.createVerticalStrut(8));
        }

        // Statistik rata-rata
        JLabel averageLabel = new JLabel(
                String.format(
                        "Rata-rata Level: %.2f",
                        manager.getAverageLevel()));

        averageLabel.setFont(AppTheme.BOLD_FONT);
        averageLabel.setForeground(
                AppTheme.TEXT_PRIMARY);

        // Scroll
        JScrollPane scrollPane = new JScrollPane(listPanel);

        scrollPane.setBorder(null);

        scrollPane.getViewport().setBackground(
                AppTheme.BACKGROUND);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER);

        mainPanel.add(
                averageLabel,
                BorderLayout.SOUTH);

        setContentPane(mainPanel);

        // Ukuran otomatis
        pack();

        setMinimumSize(new Dimension(500, 350));
        setLocationRelativeTo(null);
    }
}