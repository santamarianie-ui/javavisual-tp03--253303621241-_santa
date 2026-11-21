
package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    private final ArrayList<Character> characters;

    // Batas maksimal karakter
    private static final int MAX_CHARACTERS = 20;

    public CharacterManager() {
        characters = new ArrayList<>();
    }

    // Menambahkan karakter
    public boolean addCharacter(Character character) {

        if (character == null) {
            return false;
        }

        // Validasi maksimal 20 karakter
        if (characters.size() >= MAX_CHARACTERS) {
            throw new IllegalArgumentException(
                    "Daftar karakter sudah mencapai batas maksimal 20 karakter!");
        }

        // Validasi nama duplikat
        if (isNameTaken(character.getName())) {
            return false;
        }

        characters.add(character);
        return true;
    }

    // Memeriksa apakah nama sudah digunakan
    public boolean isNameTaken(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        return findByName(name.trim()) != null;
    }

    // Mencari karakter berdasarkan nama
    public Character findByName(String name) {

        if (name == null) {
            return null;
        }

        for (Character character : characters) {
            if (character.getName().equalsIgnoreCase(name.trim())) {
                return character;
            }
        }

        return null;
    }

    // Menghapus karakter
    public boolean removeCharacter(String name) {
        Character character = findByName(name);

        if (character != null) {
            characters.remove(character);
            return true;
        }

        return false;
    }

    // Menghitung total karakter
    public int getTotalCharacters() {
        return characters.size();
    }

    // Mengambil seluruh karakter
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // Menghitung rata-rata level
    public double getAverageLevel() {

        if (characters.isEmpty()) {
            return 0.0;
        }

        int total = 0;

        for (Character character : characters) {
            total += character.getLevel();
        }

        return (double) total / characters.size();
    }
}
