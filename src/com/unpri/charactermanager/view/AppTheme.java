package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterType;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class AppTheme {

    public static final Color BACKGROUND = new Color(245, 247, 250);

    public static final Color TEXT_PRIMARY = new Color(45, 55, 72);

    public static final Color TEXT_SECONDARY = new Color(100, 116, 139);

    public static final Color WARRIOR_COLOR = new Color(255, 220, 220);

    public static final Color MAGE_COLOR = new Color(220, 225, 255);

    public static final Color ARCHER_COLOR = new Color(220, 245, 225);

    public static final Color HEALER_COLOR = new Color(255, 245, 205);

    public static final Color GOLD = new Color(184, 134, 11);

    public static final int GOLD_LEVEL_LIMIT = 5;

    public static final Font TITLE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);

    public static final Font NORMAL_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 15);

    public static final Font BOLD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 15);

    public static final Font GOLD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 15);

    public static final Border CARD_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                    new Color(210, 215, 225), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12));

    public static Color colorFor(CharacterType type) {
        if (type == null)
            return BACKGROUND;

        switch (type) {
            case WARRIOR:
                return WARRIOR_COLOR;
            case MAGE:
                return MAGE_COLOR;
            case ARCHER:
                return ARCHER_COLOR;
            case HEALER:
                return HEALER_COLOR;
            default:
                return BACKGROUND;
        }
    }

    private AppTheme() {
    }
}