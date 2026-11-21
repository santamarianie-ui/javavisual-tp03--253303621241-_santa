
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CharacterFormPanel extends JPanel {

    private final CharacterManager manager;

    private final JTextField nameField;
    private final JComboBox<CharacterType> roleComboBox;
    private final JSpinner levelSpinner;

    private final JLabel errorLabel;
    private final JButton addButton;

    private final Border originalNameBorder;
    private final Border originalLevelBorder;

    public CharacterFormPanel(CharacterManager manager) {

        this.manager = manager;

        setLayout(new GridBagLayout());
        setBackground(AppTheme.BACKGROUND);

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Tambah Karakter"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // INPUT NAMA
        JLabel nameLabel = new JLabel("Nama Karakter:");
        nameField = new JTextField(15);
        originalNameBorder = nameField.getBorder();

        // INPUT ROLE
        JLabel roleLabel = new JLabel("Role:");
        roleComboBox = new JComboBox<>(CharacterType.values());

        // INPUT LEVEL
        JLabel levelLabel = new JLabel("Level:");
        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1));
        originalLevelBorder = levelSpinner.getBorder();

        // PESAN ERROR
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);

        // TOMBOL TAMBAH
        addButton = new JButton("Tambah Karakter");

        // BARIS 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        add(nameField, gbc);

        // BARIS 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(roleLabel, gbc);

        gbc.gridx = 1;
        add(roleComboBox, gbc);

        // BARIS 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(levelLabel, gbc);

        gbc.gridx = 1;
        add(levelSpinner, gbc);

        // BARIS 4
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(errorLabel, gbc);

        // BARIS 5
        gbc.gridy = 4;
        add(addButton, gbc);
    }

    // VALIDASI NAMA
    public String validateName(String name) {

        // 1. Kelengkapan
        if (name == null || name.trim().isEmpty()) {
            return "Nama karakter wajib diisi!";
        }

        String cleanName = name.trim();

        // 2. Panjang nama
        if (cleanName.length() > 20) {
            return "Nama maksimal 20 karakter!";
        }

        // 3. Format nama
        if (!cleanName.matches("[\\p{L} ]+")) {
            return "Nama hanya boleh berisi huruf dan spasi!";
        }

        // Aturan tambahan: tidak boleh dua spasi
        if (cleanName.contains("  ")) {
            return "Nama tidak boleh memiliki dua spasi berurutan!";
        }

        // 4. Keunikan nama
        if (manager.isNameTaken(cleanName)) {
            return "Nama karakter sudah digunakan!";
        }

        return null;
    }

    // MENGAMBIL INPUT NAMA
    public String getNameInput() {
        return nameField.getText().trim();
    }

    // MENGAMBIL ROLE
    public CharacterType getSelectedRole() {
        return (CharacterType) roleComboBox.getSelectedItem();
    }

    // MENGAMBIL LEVEL
    public int getSelectedLevel()
            throws java.text.ParseException {

        levelSpinner.commitEdit();

        return ((Number) levelSpinner.getValue()).intValue();
    }

    // MENAMPILKAN ERROR
    public void showError(String message, JComponent component) {

        resetError();

        errorLabel.setText(message);

        component.setBorder(
                BorderFactory.createLineBorder(Color.RED, 2));

        component.requestFocusInWindow();
    }

    // MENGEMBALIKAN BORDER SEPERTI SEMULA
    public void resetError() {

        errorLabel.setText(" ");

        nameField.setBorder(originalNameBorder);
        levelSpinner.setBorder(originalLevelBorder);
    }

    // MEMBERSIHKAN FORM
    public void clearForm() {

        nameField.setText("");

        roleComboBox.setSelectedIndex(0);

        levelSpinner.setValue(1);

        resetError();
    }

    // MENGAMBIL TOMBOL TAMBAH
    public JButton getAddButton() {
        return addButton;
    }

    // GETTER NAME FIELD
    public JTextField getNameField() {
        return nameField;
    }

    // GETTER LEVEL SPINNER
    public JSpinner getLevelSpinner() {
        return levelSpinner;
    }
}
