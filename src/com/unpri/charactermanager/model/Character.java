
package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {
    private String name;
    private CharacterType type;
    private int level;

    public Character(String name, CharacterType type, int level) {

        // Validasi nama
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nama karakter wajib diisi!");
        }

        // Validasi panjang nama
        if (name.trim().length() > 20) {
            throw new IllegalArgumentException(
                    "Nama maksimal 20 karakter!");
        }

        // Validasi role
        if (type == null) {
            throw new IllegalArgumentException(
                    "Role karakter wajib dipilih!");
        }

        // Validasi level
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException(
                    "Level harus berada antara 1 sampai 99!");
        }

        // Mini-Challenge: MAGE maksimal level 60
        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                    "Karakter MAGE tidak boleh memiliki level di atas 60!");
        }

        this.name = name.trim();
        this.type = type;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException(
                    "Level harus berada antara 1 sampai 99!");
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                    "Karakter MAGE tidak boleh memiliki level di atas 60!");
        }

        this.level = level;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Character))
            return false;

        Character other = (Character) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
