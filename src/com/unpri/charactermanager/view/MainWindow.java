
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.ParseException;
import java.util.List;

public class MainWindow extends JFrame {

        private final CharacterManager manager;

        private CharacterFormPanel formPanel;
        private JPanel characterPanel;
        private JPanel statisticsPanel;
        private JLabel statusText;

        public MainWindow(CharacterManager manager) {

                this.manager = manager;

                setTitle("Character Manager - TP14 v0.6");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLayout(new BorderLayout(10, 10));

                getContentPane().setBackground(AppTheme.BACKGROUND);

                createHeader();
                createCharacterList();
                createLegend();
                createStatusBar();

                // Form input karakter
                formPanel = new CharacterFormPanel(manager);

                JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
                centerPanel.setBackground(AppTheme.BACKGROUND);
                centerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

                centerPanel.add(formPanel, BorderLayout.NORTH);
                centerPanel.add(new JScrollPane(characterPanel), BorderLayout.CENTER);

                add(centerPanel, BorderLayout.CENTER);

                // Tombol tambah karakter
                formPanel.getAddButton().addActionListener(e -> addCharacter());

                setSize(1000, 750);
                setMinimumSize(new Dimension(600, 500));
                setLocationRelativeTo(null);

                refreshList();
        }

        // HEADER DAN STATISTIK
        private void createHeader() {

                JPanel header = new JPanel(new BorderLayout(10, 15));
                header.setBackground(AppTheme.BACKGROUND);
                header.setBorder(new EmptyBorder(20, 20, 10, 20));

                JLabel title = new JLabel(
                                "DAFTAR KARAKTER",
                                SwingConstants.CENTER);

                title.setFont(AppTheme.TITLE_FONT);
                title.setForeground(AppTheme.TEXT_PRIMARY);

                header.add(title, BorderLayout.NORTH);

                statisticsPanel = new JPanel(
                                new GridLayout(2, 2, 12, 10));

                statisticsPanel.setBackground(AppTheme.BACKGROUND);

                header.add(statisticsPanel, BorderLayout.CENTER);

                add(header, BorderLayout.NORTH);
        }

        // MEMPERBARUI STATISTIK
        private void updateStatistics() {

                statisticsPanel.removeAll();

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
                        label.setBorder(BorderFactory.createEtchedBorder());

                        statisticsPanel.add(label);
                }

                statisticsPanel.revalidate();
                statisticsPanel.repaint();
        }

        // PANEL DAFTAR KARAKTER
        private void createCharacterList() {

                characterPanel = new JPanel();

                characterPanel.setLayout(
                                new BoxLayout(characterPanel, BoxLayout.Y_AXIS));

                characterPanel.setBackground(AppTheme.BACKGROUND);

                characterPanel.setBorder(
                                new EmptyBorder(10, 15, 10, 15));
        }

        // MEMBUAT KARTU KARAKTER
        private JPanel createCharacterCard(Character character) {

                JPanel card = new JPanel(new BorderLayout());

                card.setBackground(
                                AppTheme.colorFor(character.getType()));

                card.setOpaque(true);

                card.setBorder(
                                BorderFactory.createCompoundBorder(
                                                BorderFactory.createLineBorder(
                                                                AppTheme.TEXT_SECONDARY),
                                                new EmptyBorder(15, 18, 15, 18)));

                JLabel name = new JLabel(character.getName());

                name.setFont(AppTheme.BOLD_FONT);
                name.setForeground(AppTheme.TEXT_PRIMARY);

                JLabel detail = new JLabel(
                                character.getType()
                                                + " | Level "
                                                + character.getLevel());

                if (character.getLevel() >= AppTheme.GOLD_LEVEL_LIMIT) {

                        detail.setForeground(AppTheme.GOLD);
                        detail.setFont(AppTheme.GOLD_FONT);

                } else {

                        detail.setForeground(AppTheme.TEXT_SECONDARY);
                        detail.setFont(AppTheme.NORMAL_FONT);
                }

                JPanel content = new JPanel(
                                new FlowLayout(FlowLayout.LEFT, 10, 0));

                content.setOpaque(false);

                content.add(name);
                content.add(detail);

                card.add(content, BorderLayout.CENTER);

                card.setToolTipText(
                                "Nama: " + character.getName()
                                                + " | Role: " + character.getType()
                                                + " | Level: " + character.getLevel());

                card.setMaximumSize(
                                new Dimension(Integer.MAX_VALUE, 70));

                card.setAlignmentX(Component.LEFT_ALIGNMENT);

                return card;
        }

        // REFRESH DAFTAR KARAKTER
        public void refreshList() {

                characterPanel.removeAll();

                List<Character> characters = manager.getAllCharacters();

                if (characters.isEmpty()) {

                        JLabel emptyLabel = new JLabel(
                                        "Belum ada karakter. Silakan tambah karakter!",
                                        SwingConstants.CENTER);

                        emptyLabel.setFont(AppTheme.NORMAL_FONT);
                        emptyLabel.setForeground(AppTheme.TEXT_SECONDARY);
                        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                        characterPanel.add(emptyLabel);

                } else {

                        for (Character character : characters) {

                                characterPanel.add(
                                                createCharacterCard(character));

                                characterPanel.add(
                                                Box.createVerticalStrut(10));
                        }
                }

                updateStatistics();

                statusText.setText(
                                "Total Karakter: " + manager.getTotalCharacters());

                characterPanel.revalidate();
                characterPanel.repaint();
        }

        // PROSES TAMBAH KARAKTER
        private void addCharacter() {

                String name = formPanel.getNameInput();

                // Validasi nama
                String error = formPanel.validateName(name);

                if (error != null) {

                        formPanel.showError(
                                        error,
                                        formPanel.getNameField());

                        return;
                }

                try {

                        // Mengambil input form
                        CharacterType role = formPanel.getSelectedRole();

                        int level = formPanel.getSelectedLevel();

                        // Membuat objek karakter
                        Character newCharacter = new Character(
                                        name,
                                        role,
                                        level);

                        // Menambahkan karakter ke manager
                        boolean success = manager.addCharacter(newCharacter);

                        if (!success) {

                                formPanel.showError(
                                                "Nama karakter sudah digunakan!",
                                                formPanel.getNameField());

                                return;
                        }

                        // Membersihkan form dan memperbarui daftar
                        formPanel.clearForm();

                        refreshList();

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Karakter berhasil ditambahkan!",
                                        "Berhasil",
                                        JOptionPane.INFORMATION_MESSAGE);

                } catch (ParseException ex) {

                        formPanel.showError(
                                        "Level harus berupa angka yang valid!",
                                        formPanel.getLevelSpinner());

                } catch (IllegalArgumentException ex) {

                        formPanel.showError(
                                        ex.getMessage(),
                                        formPanel.getLevelSpinner());
                }
        }

        // LEGENDA ROLE
        private void createLegend() {

                JPanel legend = new JPanel();

                legend.setLayout(
                                new BoxLayout(legend, BoxLayout.Y_AXIS));

                legend.setBackground(AppTheme.BACKGROUND);

                legend.setBorder(
                                new EmptyBorder(20, 15, 20, 20));

                JLabel title = new JLabel("LEGENDA ROLE");

                title.setFont(AppTheme.BOLD_FONT);
                title.setForeground(AppTheme.TEXT_PRIMARY);

                legend.add(title);

                legend.add(Box.createVerticalStrut(20));

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

                        legend.add(item);
                        legend.add(Box.createVerticalStrut(10));
                }

                legend.add(Box.createVerticalGlue());

                add(legend, BorderLayout.EAST);
        }

        // STATUS BAR
        private void createStatusBar() {

                JPanel status = new JPanel(new BorderLayout());

                status.setBackground(AppTheme.BACKGROUND);

                status.setBorder(
                                new EmptyBorder(12, 20, 15, 20));

                statusText = new JLabel(
                                "Total Karakter: " + manager.getTotalCharacters());

                JLabel version = new JLabel("v0.6");

                statusText.setFont(AppTheme.BOLD_FONT);
                version.setFont(AppTheme.BOLD_FONT);

                statusText.setForeground(AppTheme.TEXT_PRIMARY);
                version.setForeground(AppTheme.TEXT_PRIMARY);

                status.add(statusText, BorderLayout.WEST);
                status.add(version, BorderLayout.EAST);

                add(status, BorderLayout.SOUTH);
        }
}
