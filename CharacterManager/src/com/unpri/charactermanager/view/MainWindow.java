package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private static final String APP_VERSION = "v0.5";
    private final ArrayList<Character> characterList;

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager " + APP_VERSION);
        this.characterList = characterList;

        initComponents();

        pack();
        setMinimumSize(new Dimension(560, 380));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        getContentPane().setBackground(AppTheme.BG_MAIN);
        setLayout(new BorderLayout());

        add(createNorthPanel(), BorderLayout.NORTH);
        add(createListArea(), BorderLayout.CENTER);
        add(createLegendPanel(), BorderLayout.EAST);
        add(createStatusPanel(), BorderLayout.SOUTH);
    }

    private JPanel createNorthPanel() {
        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(AppTheme.BG_MAIN);
        north.add(createHeaderPanel(), BorderLayout.NORTH);
        north.add(createSummaryPanel(), BorderLayout.CENTER);
        return north;
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AppTheme.BG_MAIN);
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));

        JLabel title = new JLabel("Character Manager");
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.TEXT_MAIN);

        JLabel count = new JLabel(characterList.size() + " karakter");
        count.setFont(AppTheme.FONT_BODY);
        count.setForeground(AppTheme.GOLD);

        header.add(title, BorderLayout.WEST);
        header.add(count, BorderLayout.EAST);
        return header;
    }

    private JPanel createSummaryPanel() {
        JPanel summary = new JPanel(new GridLayout(2, 2, 10, 8));
        summary.setBackground(AppTheme.BG_MAIN);
        summary.setBorder(BorderFactory.createEmptyBorder(0, 16, 10, 16));

        summary.add(createMetricPanel("Total", String.valueOf(characterList.size())));
        summary.add(createMetricPanel("Rata-rata", String.format("%.1f", hitungRataRataLevel())));
        summary.add(createMetricPanel("Role utama", hitungRoleUtama().name()));
        summary.add(createMetricPanel("Status", "Siap"));
        return summary;
    }

    private JPanel createMetricPanel(String title, String value) {
        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(AppTheme.BG_MAIN);
        box.setBorder(AppTheme.CARD_BORDER);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(AppTheme.FONT_MONO);
        titleLabel.setForeground(AppTheme.TEXT_MUTED);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(AppTheme.FONT_BODY.deriveFont(Font.BOLD));
        valueLabel.setForeground(AppTheme.TEXT_MAIN);

        box.add(titleLabel, BorderLayout.NORTH);
        box.add(valueLabel, BorderLayout.CENTER);
        return box;
    }

    private CharacterType hitungRoleUtama() {
        int warrior = 0;
        int mage = 0;
        int archer = 0;
        int healer = 0;

        for (Character c : characterList) {
            switch (c.getType()) {
                case WARRIOR:
                    warrior++;
                    break;
                case MAGE:
                    mage++;
                    break;
                case ARCHER:
                    archer++;
                    break;
                case HEALER:
                    healer++;
                    break;
                default:
                    break;
            }
        }

        int max = Math.max(Math.max(warrior, mage), Math.max(archer, healer));
        if (max == warrior) return CharacterType.WARRIOR;
        if (max == mage) return CharacterType.MAGE;
        if (max == archer) return CharacterType.ARCHER;
        return CharacterType.HEALER;
    }

    private double hitungRataRataLevel() {
        if (characterList.isEmpty()) {
            return 0;
        }

        int total = 0;
        for (Character c : characterList) {
            total += c.getLevel();
        }
        return (double) total / characterList.size();
    }

    private JScrollPane createListArea() {
        JPanel cards = new JPanel(new GridLayout(0, 1, 0, 8));
        cards.setBackground(AppTheme.BG_MAIN);
        for (Character c : characterList) {
            cards.add(createCharacterLabel(c));
        }

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(AppTheme.BG_MAIN);
        holder.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 8));
        holder.add(cards, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(AppTheme.BG_MAIN);
        scroll.setPreferredSize(new Dimension(460, 280));
        return scroll;
    }

    private JLabel createCharacterLabel(Character c) {
        String info = c.getName() + " | " + c.getType()
                + " | Lv." + c.getLevel()
                + " | HP:" + c.getHitPoints()
                + " | ATK:" + c.getAttackPower();

        JLabel lbl = new JLabel(info);
        lbl.setFont(AppTheme.FONT_BODY);
        lbl.setForeground(AppTheme.TEXT_LIGHT);
        lbl.setBackground(AppTheme.colorFor(c.getType()));
        lbl.setOpaque(true);
        lbl.setBorder(AppTheme.CARD_BORDER);
        lbl.setToolTipText(AppTheme.describe(c.getType()));
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return lbl;
    }

    private JPanel createLegendPanel() {
        JPanel legend = new JPanel();
        legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));
        legend.setBackground(AppTheme.BG_MAIN);
        legend.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 16));

        JLabel head = new JLabel("Legenda Role");
        head.setFont(AppTheme.FONT_BODY.deriveFont(Font.BOLD));
        head.setForeground(AppTheme.TEXT_MAIN);
        head.setAlignmentX(Component.LEFT_ALIGNMENT);
        legend.add(head);
        legend.add(Box.createVerticalStrut(8));

        for (CharacterType type : CharacterType.values()) {
            JLabel chip = new JLabel(type.name());
            chip.setOpaque(true);
            chip.setBackground(AppTheme.colorFor(type));
            chip.setForeground(AppTheme.TEXT_LIGHT);
            chip.setFont(AppTheme.FONT_MONO);
            chip.setBorder(AppTheme.CARD_BORDER);
            chip.setToolTipText(AppTheme.describe(type));
            chip.setAlignmentX(Component.LEFT_ALIGNMENT);
            legend.add(chip);
            legend.add(Box.createVerticalStrut(6));
        }

        legend.add(Box.createVerticalGlue());
        return legend;
    }

    private JPanel createStatusPanel() {
        JPanel status = new JPanel(new BorderLayout());
        status.setBackground(AppTheme.BG_STATUS);
        status.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        JLabel info = new JLabel("Total: " + characterList.size() + " karakter  |  Siap");
        info.setFont(AppTheme.FONT_MONO);
        info.setForeground(AppTheme.TEXT_MUTED);
        info.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel version = new JLabel(APP_VERSION);
        version.setFont(AppTheme.FONT_MONO);
        version.setForeground(AppTheme.TEXT_MUTED);
        version.setHorizontalAlignment(SwingConstants.RIGHT);

        status.add(info, BorderLayout.WEST);
        status.add(version, BorderLayout.EAST);
        return status;
    }
}
