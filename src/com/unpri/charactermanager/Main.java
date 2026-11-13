
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;

import javax.swing.SwingUtilities;

public class Main {

        public static void main(String[] args) {

                CharacterManager manager = new CharacterManager();

                manager.addCharacter(
                                new Character("Arjuna", CharacterType.WARRIOR, 5));

                manager.addCharacter(
                                new Character("Merlin", CharacterType.MAGE, 3));

                manager.addCharacter(
                                new Character("Athena", CharacterType.ARCHER, 4));

                manager.addCharacter(
                                new Character("Leon", CharacterType.WARRIOR, 8));

                manager.addCharacter(
                                new Character("Luna", CharacterType.HEALER, 5));

                // Tiga karakter tambahan
                manager.addCharacter(
                                new Character("Raka", CharacterType.MAGE, 5));

                manager.addCharacter(
                                new Character("Freya", CharacterType.HEALER, 6));

                manager.addCharacter(
                                new Character("Orion", CharacterType.ARCHER, 6));

                SwingUtilities.invokeLater(() -> {
                        MainWindow window = new MainWindow(manager);
                        window.setVisible(true);
                });
        }
}
