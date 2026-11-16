package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    public MainWindow(CharacterManager manager) {
        this.manager = manager;

        setTitle("Character Manager - TP12 v0.5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // BorderLayout utama
        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(AppTheme.BACKGROUND);

        // Empat wilayah utama
        createHeader();
        createCharacterList();
        createLegend();
        createStatusBar();

        // UKURAN AWAL
        setSize(1000, 700);

        // Ukuran minimum agar komponen tetap terbaca
        setMinimumSize(new Dimension(450, 350));

        setLocationRelativeTo(null);
    }

    // =================================
    // NORTH: HEADER DAN STATISTIK
    // =================================
    private void createHeader() {

        JPanel header = new JPanel(new BorderLayout(10, 15));

        header.setBackground(AppTheme.BACKGROUND);

        header.setBorder(
                new EmptyBorder(20, 20, 10, 20));

        JLabel title = new JLabel(
                "DAFTAR KARAKTER",
                SwingConstants.CENTER);

        title.setFont(AppTheme.TITLE_FONT);
        title.setForeground(AppTheme.TEXT_PRIMARY);

        header.add(title, BorderLayout.NORTH);

        // Panel statistik tambahan GridLayout 2x2
        JPanel statistics = new JPanel(
                new GridLayout(2, 2, 12, 10));

        statistics.setBackground(AppTheme.BACKGROUND);

        List<Character> characters = manager.getAllCharacters();

        int highest = 0;

        for (Character c : characters) {
            if (c.getLevel() > highest) {
                highest = c.getLevel();
            }
        }

        String[] stats = {
                "Total Karakter: " + manager.getTotalCharacters(),
                String.format(
                        "Rata-rata Level: %.2f",
                        manager.getAverageLevel()),
                "Level Tertinggi: " + highest,
                "Jumlah Role: " + CharacterType.values().length
        };

        for (String stat : stats) {

            JLabel label = new JLabel(
                    stat,
                    SwingConstants.CENTER);

            label.setFont(AppTheme.BOLD_FONT);
            label.setForeground(AppTheme.TEXT_PRIMARY);

            label.setBorder(
                    BorderFactory.createEtchedBorder());

            statistics.add(label);
        }

        header.add(statistics, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
    }

    // =================================
    // CENTER: DAFTAR KARAKTER
    // =================================
    private void createCharacterList() {

        JPanel characterPanel = new JPanel();

        characterPanel.setLayout(
                new BoxLayout(
                        characterPanel,
                        BoxLayout.Y_AXIS));

        characterPanel.setBackground(AppTheme.BACKGROUND);

        characterPanel.setBorder(
                new EmptyBorder(10, 15, 10, 15));

        List<Character> characters = manager.getAllCharacters();

        for (Character character : characters) {

            JPanel card = new JPanel(
                    new BorderLayout());

            // Warna kartu berdasarkan role
            card.setBackground(
                    AppTheme.colorFor(character.getType()));

            card.setOpaque(true);

            card.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    AppTheme.TEXT_SECONDARY),
                            new EmptyBorder(15, 18, 15, 18)));

            // Nama karakter
            JLabel name = new JLabel(
                    character.getName());

            name.setFont(AppTheme.BOLD_FONT);
            name.setForeground(AppTheme.TEXT_PRIMARY);

            // Detail karakter
            JLabel detail = new JLabel(
                    character.getType()
                            + " | Level "
                            + character.getLevel());

            // Mini Challenge: Level >= 5 emas dan tebal
            if (character.getLevel() >= AppTheme.GOLD_LEVEL_LIMIT) {

                detail.setForeground(AppTheme.GOLD);
                detail.setFont(AppTheme.GOLD_FONT);

            } else {

                detail.setForeground(
                        AppTheme.TEXT_SECONDARY);

                detail.setFont(AppTheme.NORMAL_FONT);
            }

            JPanel content = new JPanel(
                    new FlowLayout(FlowLayout.LEFT, 10, 0));

            content.setOpaque(false);

            content.add(name);
            content.add(detail);

            // Tooltip
            card.setToolTipText(
                    "Nama: " + character.getName()
                            + " | Role: " + character.getType()
                            + " | Level: " + character.getLevel());

            card.add(content, BorderLayout.CENTER);

            card.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 70));

            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            characterPanel.add(card);

            characterPanel.add(
                    Box.createVerticalStrut(10));
        }

        // ScrollPane membungkus panel daftar karakter
        JScrollPane scroll = new JScrollPane(
                characterPanel);

        scroll.setPreferredSize(
                new Dimension(620, 400));

        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);
    }

    // =================================
    // EAST: LEGENDA ROLE
    // =================================
    private void createLegend() {

        JPanel legend = new JPanel();

        legend.setLayout(
                new BoxLayout(
                        legend,
                        BoxLayout.Y_AXIS));

        legend.setBackground(AppTheme.BACKGROUND);

        legend.setBorder(
                new EmptyBorder(20, 15, 20, 20));

        JLabel title = new JLabel("LEGENDA ROLE");

        title.setFont(AppTheme.BOLD_FONT);
        title.setForeground(AppTheme.TEXT_PRIMARY);

        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        legend.add(title);

        legend.add(
                Box.createVerticalStrut(20));

        // Legenda dibuat otomatis dari enum
        for (CharacterType type : CharacterType.values()) {

            JPanel item = new JPanel(
                    new FlowLayout(FlowLayout.LEFT, 8, 5));

            item.setBackground(AppTheme.BACKGROUND);

            JPanel colorBox = new JPanel();

            colorBox.setPreferredSize(
                    new Dimension(24, 24));

            colorBox.setBackground(
                    AppTheme.colorFor(type));

            colorBox.setOpaque(true);

            JLabel label = new JLabel(type.toString());

            label.setFont(AppTheme.BOLD_FONT);
            label.setForeground(AppTheme.TEXT_PRIMARY);

            item.add(colorBox);
            item.add(label);

            item.setMaximumSize(
                    new Dimension(200, 40));

            item.setAlignmentX(Component.LEFT_ALIGNMENT);

            legend.add(item);

            legend.add(
                    Box.createVerticalStrut(10));
        }

        // Mengisi ruang kosong
        legend.add(Box.createVerticalGlue());

        add(legend, BorderLayout.EAST);
    }

    // =================================
    // SOUTH: STATUS BAR
    // =================================
    private void createStatusBar() {

        JPanel status = new JPanel(
                new BorderLayout());

        status.setBackground(AppTheme.BACKGROUND);

        status.setBorder(
                new EmptyBorder(12, 20, 15, 20));

        JLabel statusText = new JLabel(
                "Total Karakter: "
                        + manager.getTotalCharacters());

        JLabel version = new JLabel("v0.5");

        statusText.setFont(AppTheme.BOLD_FONT);
        version.setFont(AppTheme.BOLD_FONT);

        statusText.setForeground(AppTheme.TEXT_PRIMARY);
        version.setForeground(AppTheme.TEXT_PRIMARY);

        status.add(
                statusText,
                BorderLayout.WEST);

        status.add(
                version,
                BorderLayout.EAST);

        add(status, BorderLayout.SOUTH);
    }
}