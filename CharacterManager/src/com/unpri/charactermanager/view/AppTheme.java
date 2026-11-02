package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.CharacterType;

public final class AppTheme {
    private AppTheme() {
        // Utility class
    }

    public static final Color BG_MAIN = Color.decode("#121826");
    public static final Color BG_STATUS = Color.decode("#0d0d1a");
    public static final Color TEXT_MAIN = Color.decode("#f4f4ff");
    public static final Color TEXT_LIGHT = Color.decode("#edf2ff");
    public static final Color TEXT_MUTED = Color.decode("#9d9dbf");
    public static final Color GOLD = Color.decode("#d4af37");

    public static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 20);
    public static final Font FONT_BODY = new Font("SansSerif", Font.PLAIN, 13);
    public static final Font FONT_MONO = new Font("Monospaced", Font.PLAIN, 12);

    public static final Border CARD_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 90), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
    );

    public static Color colorFor(CharacterType type) {
        switch (type) {
            case WARRIOR:
                return Color.decode("#d46a5e");
            case MAGE:
                return Color.decode("#7d85ff");
            case ARCHER:
                return Color.decode("#46b7a6");
            case HEALER:
                return Color.decode("#7dcf75");
            default:
                return Color.decode("#b0b7c9");
        }
    }

    public static String describe(CharacterType type) {
        switch (type) {
            case WARRIOR:
                return "Pejuang dengan HP dan serangan tinggi";
            case MAGE:
                return "Penyihir dengan kekuatan sihir yang besar";
            case ARCHER:
                return "Pemanah yang cepat dan presisi";
            case HEALER:
                return "Penyembuh yang menjaga tim tetap bertahan";
            default:
                return "Jenis karakter tidak dikenal";
        }
    }
}
